package f;

import java.util.Vector;

public class Query_op {
	private String Stem = null;
	private Vector <Word_Data> v = new Vector();
	private String original = null;
	
	public Query_op(String s, Vector<Word_Data>vec, String orig) {
		Stem = s;
		v = vec;
		original = orig;
		
	}
	
	public String getStem() { return Stem; }
	public Vector<Word_Data> getvec() { return v;}
	public String getoriginal () { return original; }

}
