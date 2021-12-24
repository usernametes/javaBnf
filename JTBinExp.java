
public class JTBinExp extends JTCode {
	private int op;
	private JTCode code1;
	private JTCode code2;
	private int TokenCode1;
	private int TokenCode2;

	public JTBinExp(int operator, JTCode c1, JTCode c2, int Tcode1, int Tcode2) {
		op = operator;
		code1 = c1;
		code2 = c2;
		TokenCode1 = Tcode1;
		TokenCode2 = Tcode2;
		
		
	}

	public JTCode run() throws Exception {
		JTCode c1 = code1.run(); // code1 を計算\E3?\99る。
		JTCode c2 = code2.run(); // code2 を計算\E3?\99る。
		JTCode result = null;
		// op\E3?\8C+\E3?\AAらc1\E3?\A8c2を足\E3?\97，\E3??\E3?\AE\E7\B5?果をJTIntクラス\E3?\AB\E3?\97\E3?\A6result\E3?\AB代入
		if (op == '+') {
			result = PulsAP(c1, c2);	
		}

		// op\E3?\8C-\E3?\AAらc1\E3?\8Bらc2を引\E3??，\E3??\E3?\AE\E7\B5?果をJTIntクラス\E3?\AB\E3?\97\E3?\A6result\E3?\AB代入
		if (op == '-') {
			result = MinusAP(c1,c2);
		}

		// op\E3?\8C*\E3?\AAらc1\E3?\A8c2を掛\E3?\91，\E3??\E3?\AE\E7\B5?果をJTIntクラス\E3?\AB\E3?\97\E3?\A6result\E3?\AB代入
		if (op == '*') {
			result = TAP(c1,c2);
		}

		// op\E3?\8C/\E3?\AAらc1をc2\E3?\A7割り，\E3??\E3?\AE\E7\B5?果をJTIntクラス\E3?\AB\E3?\97\E3?\A6result\E3?\AB代入
		if (op == '/') {
			result = DiviAP(c1, c2);
		}

		if (op == '<') {
			if (((JTInt) c1).getValue() < (((JTInt) c2)).getValue()) {
				result = JTBool.True;
			} else {
				result = JTBool.False;
			}
		}

		if (op == '>') {
			if (((JTInt) c1).getValue() > (((JTInt) c2)).getValue()) {
				result = JTBool.True;
			} else {
				result = JTBool.False;
			}
		}

		if (op == TokenType.EQ) {
			if (((JTInt) c1).getValue() == (((JTInt) c2)).getValue()) {
				result = JTBool.True;
			} else {
				result = JTBool.False;
			}
		}

		if (op == TokenType.NE) {
			if (((JTInt) c1).getValue() != (((JTInt) c2)).getValue()) {
				result = JTBool.True;
			} else {
				result = JTBool.False;
			}
		}

		if (op == TokenType.LE) {
			if (((JTInt) c1).getValue() <= (((JTInt) c2)).getValue()) {
				result = JTBool.True;
			} else {
				result = JTBool.False;
			}
		}

		if (op == TokenType.GE) {
			if (((JTInt) c1).getValue() >= (((JTInt) c2)).getValue()) {
				result = JTBool.True;
			} else {
				result = JTBool.False;
			}
		}

		if (op == TokenType.AND) {
			if (((JTBool) c1) == JTBool.True && ((JTBool) c2) == JTBool.True) {
				result = JTBool.True;
			} else {
				result = JTBool.False;
			}
		}

		if (op == TokenType.OR) {
			if (((JTBool) c1) == JTBool.True || ((JTBool) c2) == JTBool.True) {
				result = JTBool.True;
			} else {
				result = JTBool.False;
			}
		}

		return result;
	}
	
	private JTCode PulsAP(JTCode c1, JTCode c2) throws Exception {
		if(TokenCode1 == TokenType.DOUBLE) {
			if(TokenCode2 == TokenType.DOUBLE) {
				return new JTDouble(((JTDouble) c1).getValue() + ((JTDouble) c2).getValue());
			}else {
				return new JTDouble(((JTDouble) c1).getValue() + ((JTInt) c2).getValue());
			}
		}else {
			if(TokenCode2 == TokenType.DOUBLE) {
				return new JTInt((int) (((JTInt) c1).getValue() + ((JTDouble) c2).getValue()));
			}else {
				return new JTInt(((JTInt) c1).getValue() + ((JTInt) c2).getValue());
			}
		}
	}
	private JTCode MinusAP(JTCode c1, JTCode c2) throws Exception{
		if(TokenCode1 == TokenType.DOUBLE) {
			if(TokenCode2 == TokenType.DOUBLE) {
				return new JTDouble(((JTDouble) c1).getValue() - ((JTDouble) c2).getValue());
			}else {
				return new JTDouble(((JTDouble) c1).getValue() - ((JTInt) c2).getValue());
			}
		}else {
			if(TokenCode2 != TokenType.DOUBLE) {
				return new JTInt(((JTInt) c1).getValue() - ((JTInt) c2).getValue());
			}else {
				return new JTInt((int) (((JTInt) c1).getValue() - ((JTDouble) c2).getValue()));
			}
		}
	}
	private JTCode TAP(JTCode c1, JTCode c2)throws Exception{
		if(TokenCode1 == TokenType.DOUBLE) {
			if(TokenCode2 == TokenType.DOUBLE) {
				return new JTDouble(((JTDouble) c1).getValue() * ((JTDouble) c2).getValue());
			}else {
				return new JTDouble(((JTDouble) c1).getValue() * ((JTInt) c2).getValue());
			}
		}else {
			if(TokenCode2 != TokenType.DOUBLE) {
				return new JTInt(((JTInt) c1).getValue() * ((JTInt) c2).getValue());
			}else {
				return new JTInt((int) (((JTInt) c1).getValue() * ((JTDouble) c2).getValue()));
			}
		}
	}
	private JTCode DiviAP(JTCode c1, JTCode c2)throws Exception{
		if(TokenCode1 == TokenType.DOUBLE) {
			if(TokenCode2 == TokenType.DOUBLE) {
				return new JTDouble(((JTDouble) c1).getValue() / ((JTDouble) c2).getValue());
			}else {
				return new JTDouble(((JTDouble) c1).getValue() / ((JTInt) c2).getValue());
			}
		}else {
			if(TokenCode2 != TokenType.DOUBLE) {
				return new JTInt(((JTInt) c1).getValue() / ((JTInt) c2).getValue());
			}else {
				return new JTInt((int) (((JTInt) c1).getValue() / ((JTDouble) c2).getValue()));
			}
		}
	}
	

}