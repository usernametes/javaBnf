public class JTIf extends JTCode {
	private JTCode cond; // 条件式
	private JTCode then_body; // 条件式が真のとき実⾏する⽂
	private JTCode else_body; // 条件式が偽のとき実⾏する⽂

	public JTIf(JTCode code, JTCode code2, JTCode code3) {
		cond = code;
		then_body = code2;
		else_body = code3;
	}

	public JTCode run() throws Exception {
		JTCode c;
		JTCode p = cond.run();
		if (p == JTBool.True)
			c = then_body;
		else
			c = else_body;
		if (c != null)
			c = c.run();
		return c;
	}
}