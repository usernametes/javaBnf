import java.util.ArrayList;

public class JTUserFun extends JTFun {
	String symbol = null;
	private ArrayList params;
	private JTCode body;

	public JTUserFun(String sym, ArrayList l, JTCode code) {
		symbol = sym;
		params = l;
		body = code;
		arg_count = params.size();
	}

	public String toString() {
		return "<fun>";
	}

	public JTCode run() throws Exception {
		if (symbol != null) {
			SymbolTable.set(symbol, this);
			UserdefTable.set(symbol);
		}
		return this;
	}

	public JTCode exec(ArrayList params) throws Exception {
		JTCode c = null;
		setArgs(params); // 引数とフレームの確保や引数に値をセットする
		if (body != null) {
			c = body.run();
		}
		removeArgs(); // 引数領域の解放
		return c;
	}

	private void setArgs(ArrayList args) throws Exception {
		SymbolTable.pushLocals();
		for (int i = 0; i < args.size(); i++) {
			String sym = (String) params.get(i);
			JTCode c = (JTCode) args.get(i);
			SymbolTable.def(sym, c);
		}
	}

	private void removeArgs() {
		SymbolTable.popLocals(); // フレームを削除
	}
	
	public ArrayList getArgs() {
		return params;
	}
	
	public JTCode getBody() {
		return body;
	}
}