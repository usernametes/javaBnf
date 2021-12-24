import java.util.ArrayList;

public class JTFuncCall extends JTCode {
	private JTSymbol symbol;
	private ArrayList list;

	public JTFuncCall(JTSymbol sym, ArrayList l) {
		symbol = sym;
		list = l;
	}

	public JTCode run() throws Exception {
		JTCode c = SymbolTable.get(symbol.getName());
		if (c == null)
			throw new Exception("関数" + symbol.toString() + "は存在しません");
		if (!(c instanceof JTFun))
			throw new Exception("変数" + symbol.toString() + "は関数でないです");
// 関数を呼ぶ前に引数を評価しておく。
		ArrayList list2 = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			JTCode c2 = ((JTCode) list.get(i)).run();
			list2.add(c2);
		}
		JTFun fun = (JTFun) c;
		return fun.call(list2);
	}
}