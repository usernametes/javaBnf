public class JTDefVar extends JTCode {
	private String symbol;
	private JTCode code;

	public JTDefVar(String sym, JTCode c) {
		symbol = sym;
		code = c;
	}

	public JTCode run() throws Exception {
		if (SymbolTable.hasSymbol(symbol))
			throw new Exception("変数" + symbol + "は定義済み");
		JTCode val = null;
		if (code != null)
			val = code.run();
		SymbolTable.def(symbol, val);
		return val;
	}
}