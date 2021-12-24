import java.io.Reader;
import java.io.StringReader;


public class ParserTest {
	
	public void testParser(String  name, String ward) {
		Reader reader = new StringReader(ward);
		Lexer lex = new Lexer(reader);
		Parser parser = new Parser();
		JTCode code = parser.parse(lex);
		
				try {
					code.run();
				System.out.println(" (" + name + "の出力結果)");
					}
			catch (Exception e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			}


	public void testParser2() {
		Reader reader = new StringReader("a");
		Lexer lex = new Lexer(reader);
		Parser parser = new Parser();
		JTCode code = parser.parse(lex);
		if (code == null) {
			System.out.println("testPaser2 NG (code == null) !");
		} else {
			System.out.println("文字なのにNULLでない?");
		}

	}

	public void testParser3() {
		Reader reader = new StringReader("1");
		Lexer lex = new Lexer(reader);
		Parser parser = new Parser();
		JTCode code = parser.parse(lex);
		if (code == null) {
			System.out.println("testPaser3 NG (code == null) !");
		} else {
			if (code.toString().equals("1")) {
				System.out.println("testParser3 OK!");
			} else {
				System.out.println("おかしな値が返却");
			}
		}

	}

	public void testParserPlus() {
		Reader reader = new StringReader(" 12 + 34 ");
		Lexer lex = new Lexer(reader);
		Parser parser = new Parser();
		JTCode code;

		try {
			code = parser.parse(lex);
			if (code == null) {
				System.out.println("testParserPuls NG (code == null)!");
			} else {
				JTCode run = code.run();
				if (run.toString().equals("46")) {
					System.out.println("testParserPuls OK !!");
				} else {
					System.out.println("testParserPlus NG (token error)!" + run);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testParserMinus() {
		Reader reader = new StringReader(" 36 - 19 ");
		Lexer lex = new Lexer(reader);
		Parser parser = new Parser();
		JTCode code;

		try {
			code = parser.parse(lex);
			if (code == null) {
				System.out.println("testParserMinus NG (code == null)!");
			} else {
				JTCode run = code.run();
				if (run.toString().equals("17")) {
					System.out.println("testParserMinus OK !!");
				} else {
					System.out.println("testParserMinus NG (token error)!" + run);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testParser4() {
		Reader reader = new StringReader(" 49 / 7 ");
		Lexer lex = new Lexer(reader);
		Parser parser = new Parser();
		JTCode code;

		try {
			code = parser.parse(lex);
			if (code == null) {
				System.out.println("testParser4 NG (code == null)!");
			} else {
				JTCode run = code.run();
				if (run.toString().equals("7")) {
					System.out.println("testParser4 OK !!");
				} else {
					System.out.println("testParser4 NG (token error)!" + run);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testParser5() {
		Reader reader = new StringReader(" 45 * 2 ");
		Lexer lex = new Lexer(reader);
		Parser parser = new Parser();
		JTCode code;

		try {
			code = parser.parse(lex);
			if (code == null) {
				System.out.println("testParser5 NG (code == null)!");
			} else {
				JTCode run = code.run();
				if (run.toString().equals("90")) {
					System.out.println("testParser5 OK !!");
				} else {
					System.out.println("testParser5 NG (token error)!" + run);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testParser6() {
		Reader reader = new StringReader(" 48 + 12 - 25 ");
		Lexer lex = new Lexer(reader);
		Parser parser = new Parser();
		JTCode code;

		try {
			code = parser.parse(lex);
			if (code == null) {
				System.out.println("testParser6 NG (code == null)!");
			} else {
				JTCode run = code.run();
				if (run.toString().equals("35")) {
					System.out.println("testParser6 OK !!");
				} else {
					System.out.println("testParser6 NG (token error)!" + run);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testParser7() {
		Reader reader = new StringReader(" 50 - 38 + 40  + 46");
		Lexer lex = new Lexer(reader);
		Parser parser = new Parser();
		JTCode code;

		try {
			code = parser.parse(lex);
			if (code == null) {
				System.out.println("testParser7 NG (code == null)!");
			} else {
				JTCode run = code.run();
				if (run.toString().equals("98")) {
					System.out.println("testParser7 OK !!");
				} else {
					System.out.println("testParser7 NG (token error)!" + run);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testParser8() {
		Reader reader = new StringReader(" - 46 - 58 + 49 + 36 ");
		Lexer lex = new Lexer(reader);
		Parser parser = new Parser();
		JTCode code;

		try {
			code = parser.parse(lex);
			if (code == null) {
				System.out.println("testParser8 NG (code == null)!");
			} else {
				JTCode run = code.run();
				if (run.toString().equals("-19")) {
					System.out.println("testParser8 OK !!");
				} else {
					System.out.println("testParser8 NG (token error)!" + run);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testParser9() {
		Reader reader = new StringReader(" 5 * 9 + 45 - 3 ");
		Lexer lex = new Lexer(reader);
		Parser parser = new Parser();
		JTCode code;

		try {
			code = parser.parse(lex);
			if (code == null) {
				System.out.println("testParser9 NG (code == null)!");
			} else {
				JTCode run = code.run();
				if (run.toString().equals("87")) {
					System.out.println("testParser9 OK !!");
				} else {
					System.out.println("testParser9 NG (token error)!" + run);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testParser10() {
		Reader reader = new StringReader(" 5 * ( 8 + 10 - 6 ) ");
		Lexer lex = new Lexer(reader);
		Parser parser = new Parser();
		JTCode code;

		try {
			code = parser.parse(lex);
			if (code == null) {
				System.out.println("testParser10 NG (code == null)!");
			} else {
				JTCode run = code.run();
				if (run.toString().equals("60")) {
					System.out.println("testParser10 OK !!");
				} else {
					System.out.println("testParser10 NG (token error)!" + run);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testParser11() {
		Reader reader = new StringReader(" 40 * 5 + 18 / 6  ");
		Lexer lex = new Lexer(reader);
		Parser parser = new Parser();
		JTCode code;

		try {
			code = parser.parse(lex);
			if (code == null) {
				System.out.println("testParser11 NG (code == null)!");
			} else {
				JTCode run = code.run();
				if (run.toString().equals("203")) {
					System.out.println("testParser11 OK !!");
				} else {
					System.out.println("testParser11 NG (token error)!" + run);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testParser12() {
		Reader reader = new StringReader(" 5 *  aas ");
		Lexer lex = new Lexer(reader);
		Parser parser = new Parser();
		JTCode code;

		try {
			code = parser.parse(lex);
			if (code == null) {
				System.out.println("testParser12 NG (code == null)!");
			} else {

				System.out.println("testParser12 NG:数値以外が含まれているのにNULLではない！");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testPaserSymbol() {
		Reader reader = new StringReader("a = 1 + 2 * 5");
		Lexer lex = new Lexer(reader);
		Parser parser = new Parser();
		JTCode code;
		
	try {
		code = parser.parse(lex);
		if (code == null) {
			System.out.println("testParserSymbol NG (code == null)!");
		} else {
			JTCode run = code.run();
			if (run.toString().contentEquals("11")) {
				System.out.println("testParserSymbol OK !");
			}else {
				System.out.println("testParserSymbol NG !");
			}
		}
	}catch (Exception e) {
		e.printStackTrace();
	}
	}
	
	public void testPaserSymbol2() {
		Reader reader = new StringReader("b = a + a + 4");
		Lexer lex = new Lexer(reader);
		Parser parser = new Parser();
		JTCode code;
		
	try {
		code = parser.parse(lex);
		if (code == null) {
			System.out.println("testParserSymbol2 NG (code == null)!");
		} else {
			JTCode run = code.run();
			if (run.toString().contentEquals("26")) {
				System.out.println("testParserSymbol2 OK !");
			}else {
				System.out.println("testParserSymbol2 NG !");
			}
		}
	}catch (Exception e) {
		e.printStackTrace();
	}
	}
	
	public void testPaserSymbol3() {
		Reader reader = new StringReader("c = a + b - 3");
		Lexer lex = new Lexer(reader);
		Parser parser = new Parser();
		JTCode code;
		
	try {
		code = parser.parse(lex);
		if (code == null) {
			System.out.println("testParserSymbol3 NG (code == null)!");
		} else {
			JTCode run = code.run();
			if (run.toString().contentEquals("34")) {
				System.out.println("testParserSymbol3 OK !");
			}else {
				System.out.println("testParserSymbol3 NG !");
			}
		}
	}catch (Exception e) {
		e.printStackTrace();
	}
	}
	
	public void testPaserSymbol4() {
		Reader reader = new StringReader("\"abc\"");
		Lexer lex = new Lexer(reader);
		Parser parser = new Parser();
		JTCode code;
		
	try {
		code = parser.parse(lex);
		if (code == null) {
			System.out.println("testParserSymbol3 NG (code == null)!");
		} else {
			JTCode run = code.run();
			if (run.toString().contentEquals("abc")) {
				System.out.println("testParserSymbol4 OK !");
			}else {
				System.out.println("testParserSymbol4 NG !");
			}
		}
	}catch (Exception e) {
		e.printStackTrace();
	}
	}
	
	public void testPaserSymbol5() {
		Reader reader = new StringReader("x2 = 3");
		Lexer lex = new Lexer(reader);
		Parser parser = new Parser();
		JTCode code;
		
	try {
		code = parser.parse(lex);
		if (code == null) {
			System.out.println("testParserSymbol5 NG (code == null)!");
		} else {
			JTCode run = code.run();
			if (run.toString().contentEquals("3")) {
				System.out.println("testParserSymbol5 OK !");
			}else {
				System.out.println("testParserSymbol5 NG !");
			}
		}
	}catch (Exception e) {
		e.printStackTrace();
	}
	}
	
	public void testPaserSymbol6() {
		Reader reader = new StringReader("str = \"XYZ\"");
		Lexer lex = new Lexer(reader);
		Parser parser = new Parser();
		JTCode code;
		
	try {
		code = parser.parse(lex);
		if (code == null) {
			System.out.println("testParserSymbol6 NG (code == null)!");
		} else {
			JTCode run = code.run();
			if (run.toString().contentEquals("XYZ")) {
				System.out.println("testParserSymbol6 OK !");
			}else {
				System.out.println("testParserSymbol6 NG !");
			}
		}
	}catch (Exception e) {
		e.printStackTrace();
	}
	}
	
	public void testPaserSymbol7() {
		Reader reader = new StringReader("x = x2 + a + b + c");
		Lexer lex = new Lexer(reader);
		Parser parser = new Parser();
		JTCode code;
		
	try {
		code = parser.parse(lex);
		if (code == null) {
			System.out.println("testParserSymbol7 NG (code == null)!");
		} else {
			JTCode run = code.run();
			if (run.toString().contentEquals("74")) {
				System.out.println("testParserSymbol7 OK !");
			}else {
				System.out.println("testParserSymbol7 NG !");
			}
		}
	}catch (Exception e) {
		e.printStackTrace();
	}
	}
	
	public void testParsertrue() {
		Reader reader = new StringReader("12 > 3");
		Lexer lex = new Lexer(reader);
		Parser parser = new Parser();
		JTCode code;
		
	try {
		code = parser.parse(lex);
		if (code == null) {
			System.out.println("testParsertrue NG (code == null)!");
		} else {
			JTCode run = code.run();
			if (run.toString().contentEquals("true")) {
				System.out.println("testParserture OK !");
			}else {
				System.out.println("testParserture NG !");
			}
		}
	}catch (Exception e) {
		e.printStackTrace();
	}
	}
	
	public void testParsertrue2() {
		Reader reader = new StringReader("(12 < 120) && (40 < 50)");
		Lexer lex = new Lexer(reader);
		Parser parser = new Parser();
		JTCode code;
		
	try {
		code = parser.parse(lex);
		if (code == null) {
			System.out.println("testParsertrue2 NG (code == null)!");
		} else {
			JTCode run = code.run();
			if (run.toString().contentEquals("true")) {
				System.out.println("testParsertrue2 OK !");
			}else {
				System.out.println("testParsertrue2 NG !");
			}
		}
	}catch (Exception e) {
		e.printStackTrace();
	}
	}
	
	
	public void testParsertrue3() {
		Reader reader = new StringReader("(1>3)||(1<3)");
		Lexer lex = new Lexer(reader);
		Parser parser = new Parser();
		JTCode code;
		
	try {
		code = parser.parse(lex);
		if (code == null) {
			System.out.println("testParsertrue3 NG (code == null)!");
		} else {
			JTCode run = code.run();
			if (run.toString().contentEquals("true")) {
				System.out.println("testParsertrue3 OK !");
			}else {
				System.out.println("testParsertrue3 NG !");
			}
		}
	}catch (Exception e) {
		e.printStackTrace();
	}
	}
	
	
	public void testParsertrue4() {
		Reader reader = new StringReader("(12 <= 15) && (150 == 150)");
		Lexer lex = new Lexer(reader);
		Parser parser = new Parser();
		JTCode code;
		
	try {
		code = parser.parse(lex);
		if (code == null) {
			System.out.println("testParsertrue4 NG (code == null)!");
		} else {
			JTCode run = code.run();
			if (run.toString().contentEquals("true")) {
				System.out.println("testParsertrue4 OK !");
			}else {
				System.out.println("testParsertrue4 NG !");
			}
		}
	}catch (Exception e) {
		e.printStackTrace();
	}
	}
	
	
	public void testParserfalse() {
		Reader reader = new StringReader("(84 != 84) && (150 == 150)");
		Lexer lex = new Lexer(reader);
		Parser parser = new Parser();
		JTCode code;
		
	try {
		code = parser.parse(lex);
		if (code == null) {
			System.out.println("testParserfalse NG (code == null)!");
		} else {
			JTCode run = code.run();
			if (run.toString().contentEquals("false")) {
				System.out.println("testParserfalse OK !");
			}else {
				System.out.println("testParserfalse NG !");
			}
		}
	}catch (Exception e) {
		e.printStackTrace();
	}
	}
	
	public void testParserfalse2() {
		Reader reader = new StringReader("(12 >= 150) || (13 != 14)");
		Lexer lex = new Lexer(reader);
		Parser parser = new Parser();
		JTCode code;
		
	try {
		code = parser.parse(lex);
		if (code == null) {
			System.out.println("testParserfalse2 NG (code == null)!");
		} else {
			JTCode run = code.run();
			if (run.toString().contentEquals("true")) {
				System.out.println("testParserfalse2 OK !");
			}else {
				System.out.println("testParserfalse2 NG !");
			}
		}
	}catch (Exception e) {
		e.printStackTrace();
	}
	}
	
		public void testParserif() {
			Reader reader = new StringReader("i = 3;  if (i == 3 && a > 2) b = 100 else b = 200");
			Lexer lex = new Lexer(reader);
			Parser parser = new Parser();
			JTCode code;
			
		try {
			code = parser.parse(lex);
			if (code == null) {
				System.out.println("testParserif NG (code == null)!");
			} else {
				JTCode run = code.run();
				if (run.toString().contentEquals("100")) {
					System.out.println("testParserif OK !");
				}else {
					System.out.println("testParserif NG !");
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		}
		
		public void testParserif2() {
			Reader reader = new StringReader("if (i != 3 && a > 2) b = 100 else b = 200");
			Lexer lex = new Lexer(reader);
			Parser parser = new Parser();
			JTCode code;
			
		try {
			code = parser.parse(lex);
			if (code == null) {
				System.out.println("testParserif2 NG (code == null)!");
			} else {
				JTCode run = code.run();
				if (run.toString().contentEquals("b = 200")) {
					System.out.println("testParserif2 OK !");
				}else {
					System.out.println("testParserif2 NG !");
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		}
		
		public void testParserif3() {
			Reader reader = new StringReader("p = 4; o = 0; c = 5; if (c > 4) { o = p + c; }; o ");
			Lexer lex = new Lexer(reader);
			Parser parser = new Parser();
			JTCode code;
			
		try {
			code = parser.parse(lex);
			if (code == null) {
				System.out.println("testParserif3 NG (code == null)!");
			} else {
				JTCode run = code.run();
				if (run.toString().contentEquals("9")) {
					System.out.println("testParserif3 OK !");
				}else {
					System.out.println("testParserif3 NG !");
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		}
		
		public void testParserif4() {
			Reader reader = new StringReader("p = 4; o = 0; c = 5; n = 7; if (c < 4) { o = p + c; } else { o = p + n; };  ");
			Lexer lex = new Lexer(reader);
			Parser parser = new Parser();
			JTCode code;
			
		try {
			code = parser.parse(lex);
			if (code == null) {
				System.out.println("testParserif4 NG (code == null)!");
			} else {
				JTCode run = code.run();
				if (run.toString().contentEquals("11")) {
					System.out.println("testParserif4 OK !");
				}else {
					System.out.println("testParserif4 NG !");
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		}
		
		// While構文エラー
		public void testParserWhile1() {
			Reader reader = new StringReader(" while)( ");
			Lexer lex = new Lexer(reader);
			Parser parser = new Parser();
			JTCode code;
	 
			try {
				code = parser.parse(lex);
				if (code == null) {
					System.out.println("構文エラー検出OK");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	 
		public void testParserWhile2() {
			Reader reader = new StringReader(" while(; ");
			Lexer lex = new Lexer(reader);
			Parser parser = new Parser();
			JTCode code;
	 
			try {
				code = parser.parse(lex);
				if (code == null) {
					System.out.println("構文エラー検出OK");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	 
		public void testParserWhile3() {
			Reader reader = new StringReader(" while(i>4){i=i+1;}i; ");
			Lexer lex = new Lexer(reader);
			Parser parser = new Parser();
			JTCode code;
	 
			try {
				code = parser.parse(lex);
				if (code == null) {
					System.out.println("構文エラー検出OK3");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	 
		public void testParserWhile4() {
			Reader reader = new StringReader(" while(i>1){i= i-1;};i; ");
			Lexer lex = new Lexer(reader);
			Parser parser = new Parser();
			JTCode code;
	 
			try {
				code = parser.parse(lex);
				if (code == null) {
					System.out.println("testParserwhile4 NG (code == null)!");
				} else {
					JTCode run = code.run();
					if (run.toString().equals("1")) {
						System.out.println("testParserwhile4 OK !!");
					} else {
						System.out.println("testParserwhile4 NG (token error)!" + run);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	 
		public void testParserWhile5() {
			Reader reader = new StringReader("while(i<=2){i=i+1;};i");
			Lexer lex = new Lexer(reader);
			Parser parser = new Parser();
			JTCode code;
	 
			try {
				code = parser.parse(lex);
				if (code == null) {
					System.out.println("testParserWhile5 NG (code == null)!");
				} else {
					JTCode run = code.run();
					if (run.toString().equals("3")) {
						System.out.println("testParserWhile5 OK !!");
					} else {
						System.out.println("testParserWhile5 NG (token error)!" + run);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void TestParser(int No, String ward) {
			Reader reader = new StringReader(ward);
			Lexer lex = new Lexer(reader);
			Parser parser = new Parser();
			JTCode code;
			
			
			try {
				
				code = parser.parse(lex);
				if (code == null) {
					System.out.println("TestParserNG (code == null)!");
				} else {
					code.run();
					System.out.println(" (No." + No + "出力結果)");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		
		public void testParserMultiple() {
			Reader reader = new StringReader("n = 5; i = 3; o = 5; total = 1; while(n >= 2) { total = total + i; n = n - 1; }; if(total == 13) { total = total + o; };");
			Lexer lex = new Lexer(reader);
			Parser parser = new Parser();
			JTCode code;
			
		try {
			code = parser.parse(lex);
			if (code == null) {
				System.out.println("testParserMultiple NG (code == null)!");
			} else {
				JTCode run = code.run();
				if (run.toString().contentEquals("18")) {
					System.out.println("testParserMultiple OK !");
				}else {
					System.out.println("testParserMultiple NG !" + run);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		}
		
		
		public void testParserPrint() {
			Reader reader = new StringReader("print(1209)");
			Lexer lex = new Lexer(reader);
			Parser parser = new Parser();
			JTCode code;
			
		try {
			code = parser.parse(lex);
			if (code == null) {
				System.out.println("testParserPrint NG (code == null)!");
			} else {
				JTCode run = code.run();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		}
		
		

	public static void main(String args[]) {
		ParserTest test = new ParserTest();
		//test.testParser(1, "a=input();print(a);");
		/*test.testParser2();
		test.testParser3();
		test.testParserPlus();
		test.testParserMinus();
		test.testParser4();
		test.testParser5();
		test.testParser6();
		test.testParser7();
		test.testParser8();
		test.testParser9();
		test.testParser10();
		test.testParser11();
		test.testParser12();
		//変数導入テスト
		test.testPaserSymbol();
		test.testPaserSymbol2();
		test.testPaserSymbol3();
		test.testPaserSymbol4();
		test.testPaserSymbol5();
		test.testPaserSymbol6();
		test.testPaserSymbol7();
		//論理演算テスト
		test.testParsertrue();
		test.testParsertrue2();
		test.testParsertrue3();
		test.testParsertrue4();
		test.testParserfalse();
		test.testParserfalse2();
		//複文導入テスト
		test.testParserif();
		test.testParserif2();
		test.testParserif3();
		test.testParserif4();
		test.testParserWhile1();
		test.testParserWhile2();
		test.testParserWhile3();
		test.testParserWhile4();
		test.testParserWhile5();
		test.testParserMultiple();
		//print導入テスト
		test.testParserPrint();
		*/
		test.testParser("1","n = 5; i = 3; o = 5; total = 1; while(n >= 2) { total = total + i; n = n - 1; }; if(total == 13) { total = total + o; };");
		test.testParser("2", "print(129)");
		test.testParser("3", "a = 1; b = 1; while (a <= 10) { b = b * 2; a = a + 1; };  print(2 * 6); print(b)");
		test.testParser("ユーザ定義関数", "def fid(x) { if (x == 0) { 0; } else { if ( x ==1) { 1; } else { fid(x - 1) + fid (x - 2); }; }; }; i = 10; print(fid(i))");
		test.testParser("ユーザ定義関数２", "def tas() { 345; };  print(tas()) ");
		test.testParser("ユーザー定義関数３", "def pow(x) { a = 1; b = 1; while(a <= 3) { b = b * x; a = a + 1; }; b; }; i = 2; print(pow(i)+1) ");
		
	}

}
