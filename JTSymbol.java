public class JTSymbol extends JTCode {
	private String name; // シンボルの名前を保存

	public JTSymbol(String s) {
		name = s;
	}

	public JTCode run() throws Exception {
		JTCode c = SymbolTable.get(name); // 変数表から値を取得
		if (c == null) {
			throw new Exception(name + "は未定義です");
		}
		return c;
	}

	public String getName() {
		return name;
	}
}