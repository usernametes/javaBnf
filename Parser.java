import java.util.ArrayList;

class Parser {
	private Lexer lex;
	private int token;
	private int Tcode;
	private int Tcode1;
	private int Tcode2;
	private int defaultChecker = 0;

	public JTCode parse(Lexer lexer) {
		JTCode code = null;
		lex = lexer;
		getToken();
		try {
			code = program();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return code;
	}

	private JTCode program() throws Exception {
		ArrayList<JTCode> list = new ArrayList<JTCode>();
		JTCode code = stmt();
		if (code != null)
			list.add(code);
		if (token == ':')
			return new JTBlock(list);
		while (token == ';') {
			getToken();
			code = stmt();
			if (code != null)
				list.add(code);
		}
		if (token != TokenType.EOS)
			throw new Exception("⽂法エラー︓ EOS");
		if (list.size() == 0)
			return null;
		else
			return new JTBlock(list);
	}

	private JTCode stmt() throws Exception {
		JTCode code = null;
		switch (token) {
		case TokenType.IF:
			code = if_stmt();
			break;
		case TokenType.WHILE:
			code = while_stmt();
			break;
		case '{':
			code = block();
			break;
		case TokenType.DEF:
			code = function();
			break;
		case TokenType.VAR:
			code = variable();
			break;
		case TokenType.SWITCH:
			code = switch_stmt();
			break;
		case TokenType.CASE:// XXX
			code = case_stmt();
			break;
		case TokenType.DEFAULT:
			code = default_stmt();
			break;
		case TokenType.BREAK:
			breakStmt();
			break;

		default:
			code = exp();
		}
		return code;
	}

	private JTCode function() throws Exception {
		getToken(); // skip 'def'
		if (token != TokenType.SYMBOL)
			throw new Exception("文法エラー: 関数名");
		String sym = (String) lex.value();
		getToken(); // skip Symbol
		if (token != '(')
			throw new Exception("文法エラー: 関数定義の（");
		getToken(); // skip '('
		ArrayList list = arguments();
		if (token != ')')
			throw new Exception("文法エラー: 関数定義の )");
		getToken(); // skip ')'
		JTBlock blk = (JTBlock) block();
		return new JTUserFun(sym, list, blk);
	}

	private ArrayList arguments() throws Exception {
		ArrayList list = new ArrayList();
		if (token != ')') {
			if (token != TokenType.SYMBOL)
				throw new Exception("文法エラー: 引数の名前");
			list.add(lex.value());
			getToken();
			while (token != ')') {
				if (token != ',')
					throw new Exception("文法エラー: 引数の,");
				getToken(); // skip ','
				if (token != TokenType.SYMBOL)
					throw new Exception("文法エラー: 引数の名前");
				list.add(lex.value());
				getToken();
			}
		}
		return list;
	}

	private JTCode variable() throws Exception {
		getToken(); // skip 'var'
		if (token != TokenType.SYMBOL)
			throw new Exception("文法エラー: 変数宣言");
		String sym = (String) lex.value();
		getToken();
		JTCode code = null;
		if (token == '=') {
			getToken(); // skip '='
			code = exp();
		}
		return new JTDefVar(sym, code);
	}

	private JTCode block() throws Exception {
		ArrayList<JTCode> list = null;
		getToken(); // skip '{'
		while (token != '}') {
			JTCode c = stmt();
			if (token != ';') {
				System.out.println(token);
				throw new Exception("⽂法エラー︓セミコロンがありません L127");
			}
			getToken(); // skip ';'
			if (list == null)
				list = new ArrayList<JTCode>();
			list.add(c);
		}
		getToken(); // skip '}'
		return new JTBlock(list);
	}

	private JTCode switchBlock(JTCode b) throws Exception {
		ArrayList<JTCode> caseList = new ArrayList<JTCode>();
		ArrayList<JTCode> caseBodyList = new ArrayList<JTCode>();

		System.out.println(token);
		if (token != '{')
			throw new Exception("switch '{' , please");
		getToken(); // skip '{'

		// Switchの全部wo Blockとして読み取ろうかな
		while (true) {
			JTCode caseBody = stmt();// caseStmt()へ行きcaseの条件文が返却される予定

			if (defaultChecker == 1) {
				caseBodyList.add(caseBody);// デフォルト文があると代入
				break;
			}
			caseList.add(caseBody);

			JTCode caseBodyBody = exp();// caseの実行部分
			if (caseBodyBody == null)
				throw new Exception("casebodybody is null");
			caseBodyList.add(caseBodyBody);
			getToken();
		}
		if (token == '}')
			throw new Exception("switch文に終わりが見えません");
		getToken(); // skip ';'
		getToken();
		return new JTSwitch(b, caseList, caseBodyList);
	}

	private JTCode default_stmt() throws Exception {
		getToken();// skip'default'
		if (token != ':')
			throw new Exception("文法エラー: コロンない");
		getToken();// skip':'
		JTCode defaultBody = stmt();
		if (defaultBody.equals(null))
			throw new Exception("defaultBody is null");
		defaultChecker = 1;
		return defaultBody;
	}

	private JTCode case_stmt() throws Exception {
		getToken();

		JTCode caseCond = exp();// case後の文':'までの文
		if (caseCond == null) {
			throw new Exception("caseの条件を書いてください");
		}
		if (token != ':') {
			throw new Exception("文法エラー:　case コロンなし");
		}
		getToken();

		System.out.println("pass caseStmt()");
		return caseCond;
	}

	private JTCode switch_stmt() throws Exception {
		getToken();
		if (token != '(')
			throw new Exception("文法エラー:swith (");

		getToken();
		JTCode cond = stmt();
		if (token != ')')
			throw new Exception("文法エラー:switch )");
		getToken();

		JTCode switchResult = switchBlock(cond);
		if (switchResult == null) {
			throw new Exception("caseCond is null");
		}

		return switchResult;
	}

	private JTCode if_stmt() throws Exception {
		getToken(); // skip 'if'
		if (token != '(')
			throw new Exception("⽂法エラー:ifの(");
		getToken(); // skip '('
		JTCode cond = exp();
		if (token != ')')
			throw new Exception("⽂法エラー:ifの)");
		getToken(); // skip ')'
		JTCode st1 = stmt();
		JTCode st2 = null;
		if (token == TokenType.ELSE) {
			getToken(); // skip 'else'
			st2 = stmt();
		}
		return new JTIf(cond, st1, st2);
	}

	private JTCode while_stmt() throws Exception {
		getToken(); // skip 'while'
		if (token != '(')
			throw new Exception("⽂法エラーです:whileの(");
		getToken(); // skip '('
		JTCode cond = exp();
		if (token != ')')
			throw new Exception("⽂法エラーです:whileの)");
		getToken(); // skip ')'
		JTCode st = stmt();
		return new JTWhile(cond, st);
	}

	private void breakStmt() throws Exception {
		System.out.println("Hello breakStmt()");

		getToken();
		while (true) {
			getToken();

			if (token == '}')
				break;
			if (token == TokenType.DEFAULT)
				break;
			if (token == TokenType.CASE)
				break;
		}
//		backToken();
//		token = ';';

		System.out.println(token);

	}

	private JTCode exp() throws Exception {
		JTCode code = logexp();
		switch (token) {
		case TokenType.AND:
		case TokenType.OR:
			code = exp2(code);
			break;
		}
		return code;
	}

	private JTBinExp exp2(JTCode code) throws Exception {
		JTBinExp result = null;
		Tcode1 = Tcode;
		while ((token == TokenType.AND) || (token == TokenType.OR)) {
			int op = token;
			getToken();
			JTCode code2 = logexp();
			Tcode2 = Tcode;
			if (code2 == null)
				throw new Exception("exp2 Error code2 null");
			if (result == null) {
				result = new JTBinExp(op, code, code2, Tcode1, Tcode2);
			} else {
				result = new JTBinExp(op, result, code2, Tcode1, Tcode2);
			}
		}
		return result;
	}

	private JTCode logexp() throws Exception {
		JTCode code = simexp();
		switch (token) {
		case TokenType.EQ:
		case TokenType.NE:
		case TokenType.LE:
		case TokenType.GE:
		case '<':
		case '>':
			code = logexp2(code);
			break;
		}
		return code;
	}

	private JTBinExp logexp2(JTCode code) throws Exception {
		JTBinExp result = null;
		Tcode1 = Tcode;
		while ((token == TokenType.EQ) || (token == TokenType.NE)
				|| (token == TokenType.LE || (token == '<') || (token == '>')) || (token == TokenType.GE)) {
			int op = token;
			getToken();
			JTCode code2 = simexp();
			Tcode2 = Tcode;
			if (code2 == null)
				throw new Exception("logexp() Error code2 NULL");
			if (result == null) {
				result = new JTBinExp(op, code, code2, Tcode1, Tcode2);
			} else {
				result = new JTBinExp(op, result, code2, Tcode1, Tcode2);
			}
		}
		return result;
	}

	private JTCode simexp() throws Exception {
		JTCode code = term();
		switch (token) {
		case '+':
		case '-':
			code = simexp2(code);
			break;
		}
		return code;
	}

	private JTBinExp simexp2(JTCode code) throws Exception {
		JTBinExp result = null;
		Tcode1 = Tcode;
		while ((token == '+') || (token == '-')) {
			int op = token;
			getToken();
			JTCode code2 = term();
			Tcode2 = Tcode;
			if (code2 == null)
				throw new Exception(
						"Ã¦â€“â€¡Ã¦Â³â€¢Ã£â€šÂ¨Ã£Æ’Â©Ã£Æ’Â¼Ã¯Â¸â€œÃ¥ï¿½Â³Ã£ï¿½Â®Ã©Â â€¦Ã£ï¿½Å’Ã£ï¿½â€šÃ£â€šÅ Ã£ï¿½Â¾Ã£ï¿½â€ºÃ£â€šâ€œ");
			if (result == null) {
				result = new JTBinExp(op, code, code2, Tcode1, Tcode2);
			} else {
				result = new JTBinExp(op, result, code2, Tcode1, Tcode2);
			}
		}
		return result;
	}

	private JTCode term() throws Exception {
		JTCode code = factor();
		switch (token) {
		case '*':
		case '/':
			code = term2(code);
			break;
		}
		return code;
	}

	private JTBinExp term2(JTCode code) throws Exception {
		JTBinExp result = null;
		Tcode1 = Tcode;
		while ((token == '*') || (token == '/')) {
			int op = token;
			getToken();
			JTCode code2 = factor();
			Tcode2 = Tcode;
			if (code2 == null)
				throw new Exception(
						"Ã¦â€“â€¡Ã¦Â³â€¢Ã£â€šÂ¨Ã£Æ’Â©Ã£Æ’Â¼Ã¯Â¸â€œÃ¥ï¿½Â³Ã£ï¿½Â®Ã©Â â€¦Ã£ï¿½Å’Ã£ï¿½â€šÃ£â€šÅ Ã£ï¿½Â¾Ã£ï¿½â€ºÃ£â€šâ€œ");
			if (result == null) {
				result = new JTBinExp(op, code, code2, Tcode1, Tcode2);
			} else {
				result = new JTBinExp(op, result, code2, Tcode1, Tcode2);
			}
		}
		return result;
	}

	private JTCode funcCall(JTSymbol sym) throws Exception {
		ArrayList<JTCode> list = args();
		if (token != ')')
			throw new Exception("文法エラー: )がない");
		getToken(); // skip ')'
		return new JTFuncCall(sym, list);
	}

	private ArrayList<JTCode> args() throws Exception {
		ArrayList<JTCode> list = new ArrayList<JTCode>();
		if (token != ')') {
			list.add(exp());
			while (token != ')') {
				if (token != ',')
					throw new Exception("文法エラー: ,がない");
				getToken(); // skip ','
				list.add(exp());
			}
		}
		return list;
	}

	private void getToken() {
		if (lex.advance()) {
			token = lex.token();
		} else {
			token = TokenType.EOS;
		}
	}

	private void backToken() throws Exception {
		if (lex.advance()) {
			token = lex.backToken();
		} else {
			token = TokenType.EOS;
		}
	}

	private JTCode factor() throws Exception {
		JTCode code = null;
		switch (token) {
		case TokenType.EOS: // Ã§Â©ÂºÃ£ï¿½Â®Ã£Æ’â€”Ã£Æ’Â­Ã£â€šÂ°Ã£Æ’Â©Ã£Æ’Â 
			break;
		case TokenType.INT:
			code = new JTInt((Integer) lex.value());
			Tcode = token;
			getToken();
			break;
		case TokenType.DOUBLE:
			code = new JTDouble((Double) lex.value());
			Tcode = token;
			getToken();
			break;
		case '-':
			getToken();
			code = new JTMinus(factor());
			break;
		case '(':
			getToken();
			code = exp();
			if (token != ')') {
				throw new Exception(
						"Ã¦â€“â€¡Ã¦Â³â€¢Ã£â€šÂ¨Ã£Æ’Â©Ã£Æ’Â¼Ã¯Â¸â€œÃ¥Â¯Â¾Ã¥Â¿Å“Ã£ï¿½â„¢Ã£â€šâ€¹Ã¦â€¹Â¬Ã¥Â¼Â§Ã£ï¿½Å’Ã¦Å“â€°Ã£â€šÅ Ã£ï¿½Â¾Ã£ï¿½â€ºÃ£â€šâ€œ");
			}
			getToken();
			break;

		case '!':
			getToken();
			code = new JTNot(factor());
		case TokenType.SYMBOL:
			JTSymbol sym = new JTSymbol((String) lex.value());
			getToken();
			if (token == '=') {
				getToken();
				code = new JTAssign(sym, exp());
			} else if (token == '(') {
				getToken();
				code = funcCall(sym);
			} else {
				code = sym;
			}
			break;
		case TokenType.STRING:
			code = new JTString((String) lex.value());
			getToken();
			break;

		case TokenType.TRUE:
			code = JTBool.True;
			getToken();
			break;

		case TokenType.FALSE:
			code = JTBool.False;
			getToken();
			break;

		default:
			throw new Exception("facter Error" + token);
		}
		return code;
	}

}