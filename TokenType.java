/** Lexerã�®tokã�«ã‚»ãƒƒãƒˆã�™ã‚‹å€¤ */
class TokenType {
	public static final int EOS = -1; // end of sentence
	public static final int INT = 257;
	public static final int SYMBOL = 258;
	public static final int STRING = 259;
	public static final int TRUE = 260;
	public static final int FALSE = 261;
	public static final int EQ = 262; // '=='
	public static final int NE = 263; // '!='
	public static final int LE = 264; // '<='
	public static final int GE = 265; // '>='
	public static final int AND = 266; // '&&'
	public static final int OR = 267; // '||'
	public static final int IF = 268; // 'IF'
	public static final int ELSE = 269; // 'ELSE'
	public static final int WHILE = 270; // 'While'
	public static final int DOUBLE = 271;
	public static final int VAR = 272;
	public static final int  DEF = 273;
	public static final int SWITCH = 274;
	public static final int CASE = 275;
	public static final int DEFAULT = 276;
	public static final int BREAK = 278;
}