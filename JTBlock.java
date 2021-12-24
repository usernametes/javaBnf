import java.util.ArrayList;

public class JTBlock extends JTCode {
	ArrayList<JTCode> list;

	JTBlock(ArrayList<JTCode> l) {
		list = l;
	}

	public JTCode run() throws Exception {
		JTCode code = JTBool.True;
		for (JTCode c : list) {
			if (c != null)
				code = c.run();
		}
		return code;
	}
}