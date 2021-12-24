import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringReader;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GUI extends Application {

	private TextArea text;
	private TextArea inputarea;
	private TextArea result;
	private Button runButton;
	private ByteArrayOutputStream bytes;
	private Thread parseThread = null;
	private static PipedInputStream inPipe;
	private static PrintWriter inWriter;

	public GUI() {

		// 出力用（print系関数）の前処理
		bytes = new ByteArrayOutputStream() {
			@Override
			public synchronized void flush() throws IOException {
				result.setText(toString());
			}
		};

		// 入力用（input系関数）の前処理
		try {
			inPipe = new PipedInputStream();
			inWriter = new PrintWriter(new PipedOutputStream(inPipe), true);
		} catch (IOException e) {
			System.out.println("Error: " + e);
			e.printStackTrace();
		}

		// System.out と System.in が上で準備したstreamになるようにセットしておく
		System.setOut(new PrintStream(bytes, true));
		System.setIn(inPipe);
	}

	private void interpret() {
		bytes.reset();
		result.setText("");
		inputarea.setText("");

		parseThread = new Thread(() -> {
			StringReader in = new StringReader(text.getText());
			Lexer lex = new Lexer(in);
			Parser parser = new Parser();
			try {
				JTCode code = parser.parse(lex);
				if (code != null)
					code.run();
			} catch (Exception e) {
				e.printStackTrace();
				result.setText("Error" + e);
			}
			in.close();
		});
		parseThread.start();
	}

	public void inputaction(KeyEvent event) {
		// System.err.println(event.getCharacter());
		inWriter.print(event.getCharacter());
		inWriter.flush();
	}

	public void start(Stage stage) {
		text = new TextArea();
		inputarea = new TextArea();
		inputarea.setOnKeyTyped(e -> inputaction(e));
		result = new TextArea();
		result.setEditable(false);
		runButton = new Button("Run");
		runButton.setOnAction(e -> interpret());

		BorderPane pane = new BorderPane();
		pane.setTop(runButton);
		pane.setCenter(text);
		pane.setRight(inputarea);
		pane.setBottom(result);

		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.setTitle("インタプリタ");
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}