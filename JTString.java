public class JTString extends JTCode{
	private String value;
	
	public JTString(String string) {
		value = string.toString();
	}
	
	public String getValue() {
		return value;
	}
	
	public String toString() {
		return value;
	}
}