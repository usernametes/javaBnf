import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class Lexer {
	private int tok; // advance()ã�®å‡¦ç�†ä¸­ã�«ã‚»ãƒƒãƒˆã�•ã‚Œã‚‹
	private Object val; // advance()ã�®å‡¦ç�†ä¸­ã�«ã‚»ãƒƒãƒˆã�•ã‚Œã‚‹
	private LexerReader reader; // readerã�®read()ã‚’å‘¼ã�³å‡ºã�—ã�¦æ–‡å­—ã‚’èª­ã‚“ã�§ã�„ã��

	private static Map<String, Integer> reserved = new HashMap<String, Integer>();
	static {
		reserved.put("true", TokenType.TRUE);
		reserved.put("false", TokenType.FALSE);
		reserved.put("if", TokenType.IF);
		reserved.put("else", TokenType.ELSE);
		reserved.put("while", TokenType.WHILE);
		reserved.put("var", TokenType.VAR);
		reserved.put("def", TokenType.DEF);
		reserved.put("switch", TokenType.SWITCH);
		reserved.put("case", TokenType.CASE);
		reserved.put("default", TokenType.DEFAULT);
		reserved.put("break", TokenType.BREAK);
	}

	public Lexer(Reader r) {
		reader = new LexerReader(r);
	}

	/** ç�¾åœ¨ã�®ãƒˆãƒ¼ã‚¯ãƒ³ã�®ç¨®é¡žã‚’è¿\E2\80?ã�™ */
	public int token() {
		return tok;
	}

	/* ç�¾åœ¨ã�®ãƒˆãƒ¼ã‚¯ãƒ³ã�®å€¤ã‚’è¿\E2\80?ã�™ */
	public Object value() {
		return val;
	}

	public int backToken() throws Exception {
		reader.unread();
		int c = reader.read();
		tok = c;
		return tok;
	}

	/**
	 * æ¬¡ã�®ãƒˆãƒ¼ã‚¯ãƒ³ã�«é€²ã‚€ï¼Žæ¬¡ã�®ãƒˆãƒ¼ã‚¯ãƒ³ã�Œã�‚ã‚Œã�°trueï¼Œã�ªã�‘ã‚Œã�°false
	 */
	public boolean advance() {
		try {
			skipWhiteSpace();
			int c = reader.read();
			if (c < 0) {
				return false;
			}
			switch (c) {
			case '+':
			case '-':
			case '*':
			case '/':
			case '(':
			case ')':
			case '{':
			case '}':
			case ';':
			case ':':
				tok = c;
				break;

			case '=':
				c = reader.read();
				if (c == '=') {
					tok = TokenType.EQ; // '=='
				} else {
					reader.unread();
					tok = '='; // '='
				}
				break;

			case '!':
				c = reader.read();
				if (c == '=') {
					tok = TokenType.NE; // '!='
				} else {
					reader.unread();
					tok = '!'; // '='
				}
				break;

			case '<':
				c = reader.read();
				if (c == '=') {
					tok = TokenType.LE; // '<='
				} else {
					reader.unread();
					tok = '<'; // '='
				}
				break;

			case '>':
				c = reader.read();
				if (c == '=') {
					tok = TokenType.GE; // '>='
				} else {
					reader.unread();
					tok = '>'; // '='
				}
				break;

			case '&':
				c = reader.read();
				if (c == '&') {
					tok = TokenType.AND; // '&&'
				} else {
					throw new Exception("don't use this token");
				}
				break;

			case '|':
				c = reader.read();
				if (c == '|') {
					tok = TokenType.OR; // '&&'
				} else {
					throw new Exception("don't use this token");
				}
				break;

			default:
				if (Character.isDigit((char) c) || ((char) c) == '.') {
					reader.unread();
					tok = TokenType.INT;
					lexDigit();
				} else if (Character.isJavaIdentifierStart(c)) {
					reader.unread();
					tok = TokenType.SYMBOL;
					lexSymbol();
				} else if (34 == ((char) c)) {
					tok = TokenType.STRING;
					lexString();

				} else {
					throw new Exception("Lexer Error");
				}
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/** æ•°å­—ã�®è§£æž� */
	private void lexDigit() throws Exception {
		int num = 0;
		StringBuffer sum = new StringBuffer();
		while (true) {
			int c = reader.read();
			if (c < 0) {
				break;
			}
			if (c == '.') {
				tok = TokenType.DOUBLE;
				sum.append(Integer.valueOf(num));
				sum.append('.');
				while (true) {
					c = reader.read();
					if (!(Character.isDigit((char) c))) {
						reader.unread();
						break;
					}
					sum.append((char) c);
				}
				val = Double.valueOf(sum.toString());
				return;
			}
			if (!Character.isDigit((char) c)) {
				reader.unread();
				break;
			}

			num = (num * 10) + Character.getNumericValue(c);
		}
		val = Integer.valueOf(num);

	}

	private void lexSymbol() throws Exception {
		StringBuffer sum = new StringBuffer();
		while (true) {
			int c = reader.read();
			if (c < 0) {
				break;
			}
			if (!Character.isJavaIdentifierPart((char) c)) {
				// System.out.println((char) c);// check
				reader.unread();
				break;
			}
			sum.append((char) c);
		}
		val = sum.toString();

		if (reserved.containsKey(val)) {
			tok = (Integer) reserved.get(val);
		}

	}

	private void lexString() throws Exception {
		String sum = null;
		sum = "";
		while (true) {
			int c = reader.read();
			if (c < 0) {
				break;
			}
			if (c == 34) {
				break;
			}
			sum = sum + ((char) c);
		}
		val = sum;

	}

	/** ç©ºç™½æ–‡å­—ã‚’ç„¡è¦–ã�™ã‚‹ */
	private void skipWhiteSpace() throws Exception {
		int c = reader.read();
		while ((c != -1) && Character.isWhitespace((char) c)) {
			c = reader.read();
		}
		reader.unread();
	}

}