
public class JTDouble extends JTCode{
	private double value;
	
	public JTDouble(Double Double) {
		value = Double.doubleValue();
	}
	
	public double getValue() {
		return value;
	}
	
	public String toString() {
		return String.valueOf(value);
	}
}