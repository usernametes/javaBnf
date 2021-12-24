    import java.io.IOException;
    import java.io.Reader;
     
    class LexerReader {
    	private Reader reader;
    	private boolean unget_p = false; // unreadしたかどうかのフラグ
    	private int ch;
     
    	public LexerReader(Reader r) {
    		reader = r;
    	}
     
    	public int read() throws IOException {
    		// unread時はreader.read()を呼びださず既に読み込んでいる文字を返す
    		if (unget_p) {
    			unget_p = false;
    		} else {
    			ch = reader.read();
    		}
    		return ch;
    	}
     
    	/** 読み込んだ文字を一文字戻す */
    	public void unread() {
    		unget_p = true;
    	}
    }