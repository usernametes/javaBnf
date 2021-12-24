
public class JTNot extends JTCode{
	private JTCode code;
	public JTNot(JTCode c) {
		code = c;
	}
	public JTCode run() throws Exception{
		JTCode c = code.run();
		if(c.getClass() != JTBool.class) {
			throw new Exception("inadaptable [!]");
		}
		JTBool p = (JTBool)c;
		if(p.isTrue()){
			return JTBool.False;
		}else {
			return JTBool.True;
		}
	}
}
