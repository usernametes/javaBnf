import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Scanner;

public class JTPrimInput extends JTPrim {
	public JTPrimInput() {
		super(0); // 引数の個数は１個
	}

	public JTCode exec(ArrayList params) throws Exception {
		//JTCode code;
		/*@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		String Buf = scan.nextLine();
		code = new JTString(Buf);*/
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		
		Reader reader = new StringReader(s);
		Lexer lex = new Lexer(reader);
		Parser parser = new Parser();
		JTCode code = parser.parse(lex);
		
			
		
		return code;
		
	}
}