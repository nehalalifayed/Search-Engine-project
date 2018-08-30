package f;



import java.util.Vector;

public class Word_Data {
	private String Sword = null;
	private String url = null;
	private Vector<word> data = new Vector();
	private double repeate = 0.0;
	
	
	
	Word_Data(String str, double rep, Vector<word> w, String s) {
		url = str;
		repeate = rep;
		data  = w;
		Sword = s;
	}
	
	public String getUrl() { return url; }
	public Vector<word> getData() { return data; }
	public double getrep() { return repeate; }
}
