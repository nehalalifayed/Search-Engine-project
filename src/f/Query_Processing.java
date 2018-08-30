package f;

import java.util.HashMap;
import java.util.Vector;
import java.util.Arrays;

import org.tartarus.snowball.ext.englishStemmer;


public class Query_Processing {


	
	public  Query_op [] processQuery(String Query) {
		
		String Phrase = null;
		int i = Query.indexOf("\"") ;
		Database db = new Database("Sengine");
		if(i != -1)
		{
			Phrase = Query.substring(Query.indexOf('"')+1, Query.lastIndexOf('"')).trim();
			System.out.println(Phrase);
			 String delStr = Query.substring(Query.indexOf('"'), Query.lastIndexOf('"')+1);
			Query = Query.replace(delStr, "");
			Query_op [] q = Phrase_Processing(Phrase);
			return q;
		}
		
		else {
			
			//---splitting the query into array--- 
			String[] arr = Query.split(" ");
			//this array will contain the stemmed words of the query
			String [] stem = new String[arr.length];
			//declaring an instance of class stemmer and Stopwords
			englishStemmer stemmer=new englishStemmer();
			Stopwords sws =new Stopwords();
			
			//stemming the query words
			for(int i1 = 0; i1 < arr.length; i1++) {
				
				stemmer.setCurrent(arr[i1]);
			if (stemmer.stem())
			stem[i1]=stemmer.getCurrent();
			
			}
			
			// this vector contains the words that we will search for 
			Vector <String> stemmed = new Vector();
			
			// this vector contains the original words that we will search for 
			Vector <String> original = new Vector();
			
			// removing stop words
			for(int j = 0; j < arr.length; j++) {
				
			 boolean test = sws.isStopword(arr[j].toLowerCase());
			 
			 if(!test) {
				stemmed.add(stem[j]);
				original.add(arr[j]);
			 }
				
			}
			
			System.out.println("after removing stop words");
			for(int j = 0; j <stemmed.size(); j++) {
				
				System.out.println(stemmed.get(j));
				
			}
			
			Vector <Query_op> op = new Vector(); 
			for(int j = 0; j < stemmed.size(); j++) {
				Vector <Word_Data> v = db.retData(stemmed.get(j));
				Query_op q = new Query_op(stemmed.get(j),v,original.get(j));
				op.add(q);
				
				
			}
			Query_op [] data1 = new Query_op[op.size()];
			data1  = (Query_op[]) op.toArray(data1);
			
			return data1;
			
			
		}

		
	
	
	
			
		}
	
	public Query_op [] Phrase_Processing(String Phrase) {
		
		Query_op [] op1 = processQuery(Phrase);
		Vector<Query_op> op = new Vector<Query_op>(Arrays.asList(op1));
		String Sword = "";
		String Oword = "";
		
		for(int i = 0; i < op.size(); i++) {
			Sword += " "+op.get(i).getStem();
			Oword += " " +op.get(i).getoriginal();
			
		}
		
		// keeping only urls that have original words of the phrase
		for(int i = 0; i < op.size(); i++) {
			
			String ori = op.get(i).getoriginal();
			Vector <Word_Data> v = op.get(i).getvec();
			
			for(int j = 0; j < v.size(); j++) {
				
				int l;
				Vector <word> w = v.get(j).getData();
				
				for(l = 0; l < w.size(); l++) {
					
					if(!(w.get(l).text() .equals(ori) ))
					w.remove(l);

				}
				
				if(0 == w.size())
				{
					v.remove(j);
					j--;
				}
			}
		}
		
		
		//keeping only urls that has all words in the phrase
		HashMap <String, Integer> mp = new HashMap<String, Integer>();		
		
		for(int i = 0; i < op.size(); i++) {
			
			Vector<Word_Data> v = op.get(i).getvec();
			
			for(int j = 0; j < v.size(); j++) {
				
				
				mp.merge(v.get(j).getUrl(), 1, Integer::sum);
				
				
			}
			
		}
		
		for(int i = 0; i < op.size(); i++) {
			
			Vector<Word_Data> v = op.get(i).getvec();
			
			for(int j = 0; j < v.size(); j++) {
				
				if(mp.get(v.get(j).getUrl()) != op.size()) {
					v.remove(j);
					j--;
				}
			}
			
		}
		
		//check if the phrase is exist in these urls
		
		HashMap<String, Integer> rep = new HashMap <String, Integer>();
		
		Vector <Word_Data> wd1 = op.get(0).getvec();
		Vector <String> urls = new Vector <String>();
		
		
		for(int i = 0; i < wd1.size(); i++) {
			HashMap<Integer, Integer> map = new HashMap <Integer, Integer>();
			String url = wd1.get(i).getUrl();
			urls.add(url);
			Vector<Integer> pos1 = wd1.get(i).getData().get(0).postions;
			for(int j = 1; j < op.size(); j++) {
				
				Vector <Word_Data> v = op.get(j).getvec();
				
				for(int k = 0; k < v.size(); k++) {
					
					if(url .equals(v.get(k).getUrl()) ) {
						
						Vector <Integer> pos2 = v.get(k).getData().get(0).postions;
						int n = 0;
						for(int l = 0 ; l <pos1.size(); l++) {
							for(int h = 0; h <pos2.size(); h++) {
								
								if(pos2.get(h)-j == pos1.get(l)) {
									map.merge(pos1.get(l), 1, Integer::sum);
									break;
								}
								
							}
						}
						
						
					}
					
					
				}
				
				for(int m = 0; m < pos1.size(); m++) {
					
					if(map.get(pos1.get(m)) == op.size() -1) {
						mp.merge(url, 1, Integer::sum);
					}
				}
				
			}
			
			
		}
		
		Vector <Query_op> outq = new Vector <Query_op>();
		Vector <Word_Data> outw = new Vector <Word_Data>();
		for(int i = 0; i < urls.size(); i++) {
			
			if(mp.get(urls.get(i)) > 0) {
				
				Vector <word> wd = new Vector <word>();
				Word_Data w = new Word_Data (urls.get(i), (double)mp.get(urls.get(i)), wd, "");
				outw.add(w);
			}
			
		}
		
		Query_op Q = new Query_op(Sword, outw, Oword);
		outq.add(Q);

		Query_op [] data1 = new Query_op[outq.size()];
		data1  = (Query_op[]) outq.toArray(data1);
		return data1;
		
	}
	}
	
