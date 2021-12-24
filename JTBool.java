
public class JTBool extends JTCode{
	private boolean p;
	public static JTBool True;
	public static JTBool False;
	
	static {
		True = new JTBool(true);
		False = new JTBool(false);
	}
	private JTBool(boolean b) {
		p = b;
	}
	public String toString() {
		return Boolean.toString(p);
	}
	public boolean isTrue() {
		return p;
	}
}