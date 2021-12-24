import java.util.ArrayList;

public class JTSwitch extends JTCode {
	private JTCode cond;
	private ArrayList<JTCode> caseCond;
	private ArrayList<JTCode> body;
	
	public JTSwitch(JTCode code1, ArrayList<JTCode> code2, ArrayList<JTCode> code3) {
		cond = code1;
		caseCond = code2;
		body = code3;
	}
	
	

	public JTCode run() throws Exception{
		int cnt = 0;
		
		
		JTCode returnBody = JTBool.True;
		for(JTCode c :caseCond) {
			if(c.run().toString().equals(cond.run().toString())) {
				returnBody = body.get(cnt).run();
				break;
			}
			cnt += 1;
		}
		
		if(cnt == caseCond.size()) 
			returnBody = body.get(cnt).run();

		return returnBody;
	}

}
