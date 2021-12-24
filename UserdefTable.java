import java.util.ArrayList;

public class UserdefTable {
	public static ArrayList<String> funcs = new ArrayList<>();
	
	public static void set(String sym) {
		funcs.add(sym);
	}
	
	public static String get(int i) {
		return funcs.get(i);
	}
	
	public static int size() {
		return funcs.size();
	}
	
}
