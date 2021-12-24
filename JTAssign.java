public class JTAssign extends JTCode {
	private JTCode symbol; // 代⼊⽂の左側（変数名）
	private JTCode code; // 代⼊⽂の右側（式）

	public JTAssign(JTCode symbol, JTCode code) {
		this.symbol = symbol;
		this.code = code;
	}

	public JTCode run() throws Exception {
		JTSymbol sym = (JTSymbol) symbol;
		JTCode c = code.run();
		SymbolTable.set(sym.getName(), c); // 変数表に値をセット
		return c;
	}
}