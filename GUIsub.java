
import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.ArrayList;


import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GUIsub extends Application {

	private TextArea text;
	private TextArea inputarea;
	private TextArea result;
	private Button runButton;
	private ByteArrayOutputStream bytes;
	private Thread parseThread = null;
	private static PipedInputStream inPipe;
	private static PrintWriter inWriter;
	int i = 0;
	
	ListView<String> list = new ListView<>();
	ArrayList<String> s = new ArrayList<String>();
	
		
	
	public GUIsub() {

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
	
	private void Clear() {
		text.setText("");
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
			
			if (UserdefTable.size() != 0) {
			String funcname = UserdefTable.get(i);
			JTUserFun f = (JTUserFun) SymbolTable.get(funcname);
			ArrayList params = f.getArgs();
			s.add(funcname);
			i++;
			ObservableList<String> items = FXCollections.observableArrayList(s);
			list.setItems(items);
		}
			
			
			
		});
		parseThread.start();
		
		
		
		
		
	}

	public void inputaction(KeyEvent event) {
		// System.err.println(event.getCharacter());
		inWriter.print(event.getCharacter());
		inWriter.flush();
	}
	
	public void listclicked() {
		
	}
	
	
		
	
	public void start(Stage stage) {
			
		text = new TextArea();
		text.setFont(Font.font("Verdana", 20));
		inputarea = new TextArea();
		inputarea.setFont(Font.font("Verdana", 20));
		inputarea.setOnKeyTyped(e -> inputaction(e));
		result = new TextArea();
		result.setFont(Font.font("Verdana", 20));
		result.setEditable(false);
		runButton = new Button("Run");
		runButton.setOnAction(e -> interpret());
		Button clearButton = new Button("Clear");
		clearButton.setOnAction(e -> Clear());
		
		list.setOnMouseClicked((MouseEvent event) -> {listClicked(event);});
		/*VBox box = new VBox();
		VBox.setVgrow(list, Priority.SOMETIMES);
		*/
		
		runButton.setPrefWidth(500);
		runButton.setPrefHeight(100);
		clearButton.setPrefWidth(500);
		clearButton.setPrefHeight(100);
		text.setPrefWidth(500);
		text.setPrefHeight(600);
		inputarea.setPrefWidth(500);
		inputarea.setPrefHeight(600);
		result.setPrefWidth(1000);
		result.setPrefHeight(400);
		list.setPrefWidth(300);
		list.setPrefHeight(600);
		
		GridPane listroot = new GridPane();
		GridPane.setConstraints(list, 0, 0);
		listroot.getChildren().addAll(list);
		
		GridPane button = new GridPane();
		GridPane.setConstraints(runButton, 0, 0);
		GridPane.setConstraints(clearButton, 1, 0);
		button.getChildren().addAll(runButton, clearButton);
		
		GridPane  subPane = new GridPane();
		GridPane.setConstraints(text, 0, 0);
		GridPane.setConstraints(inputarea, 1, 0);
		subPane.getChildren().addAll(text, inputarea);
			
		GridPane grid = new GridPane();
		GridPane.setConstraints(button, 0, 0);
		GridPane.setConstraints(subPane, 0, 1);
		GridPane.setConstraints(result, 0, 2);
		GridPane.setConstraints(listroot, 2, 1);
		grid.getChildren().addAll(result, subPane, button, listroot);
		
		
		
		Scene scene = new Scene(grid);
		stage.setWidth(1300);
		stage.setHeight(1100);
		stage.setScene(scene);
		stage.setTitle("インタプリタ");
		stage.show();
	}
	
	public void listClicked(MouseEvent e) {
		ListView<String> l = (ListView)e.getSource();
		String w = (String)l.getSelectionModel().getSelectedItem();
		if (w == null) {
			return;
		}
		JTUserFun f = (JTUserFun) SymbolTable.get(w);
		text.setText(w);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
