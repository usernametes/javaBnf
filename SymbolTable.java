import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class SymbolTable {
	public static Map<String, JTCode> Globals = new HashMap<String, JTCode>();

	static {
		Globals.put("print", new JTPrimPrint());
		Globals.put("input", new JTPrimInput());
	}// 変数表そのもの

	public static Stack Locals = new Stack();

	public static void pushLocals() {
		Locals.push(new HashMap());
	}

	public static void popLocals() {
		Locals.pop();
	}

	public static boolean hasSymbol(String sym) {
		if (Locals.size() != 0) {
			HashMap table = (HashMap) Locals.peek();
			return table.containsKey(sym);
		}

		return Globals.containsKey(sym);
	}

	public static JTCode get(String sym) {
		if (Locals.size() != 0) {
			HashMap table = (HashMap) Locals.peek();
			if (table.containsKey(sym)) {
				return (JTCode) table.get(sym);
			}
		}

		return (JTCode) Globals.get(sym);
	}

	public static void set(String sym, JTCode code) {
		if (Locals.size() != 0) {
			HashMap<String, JTCode> table = (HashMap<String, JTCode>) Locals.peek();
			if (table.containsKey(sym) != true) {
				table.put(sym, code);
			}
		}

		Globals.put(sym, code);
	}

	public static void def(String sym, JTCode code) {
		if (Locals.size() != 0) {
			HashMap table = (HashMap) Locals.peek();
			table.put(sym, code);
			return;
		}
		Globals.put(sym, code);
	}
}