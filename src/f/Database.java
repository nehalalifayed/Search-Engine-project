package f;

import java.net.UnknownHostException;
import java.util.Vector;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class Database {


	private  DB db;
	private  DBCollection table;
	private  DBCollection Titles;
	private  DBCollection docs;
	private  DBCollection rank;
	//--------constructor---------//
	public Database(String name)
	{
		try {
			MongoClient mongoclient = new MongoClient("localhost",27017);
			System.out.println("connection successfully done");
			db = mongoclient.getDB(name);
			table = db.getCollection("table");
			Titles =  db.getCollection("Titles");
			docs =  db.getCollection("docs");
			rank =  db.getCollection("Rank");
		}catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

//--------------------------------------------------------------------//
	//---------------------retrieve rank----------------//

	public int retrank(String url) {

		BasicDBObject b = new BasicDBObject();
		b.put("links", url);
		DBCursor cursor = rank.find(b);
		if(cursor.hasNext()) {
			return (int) (cursor.next().get("repeat"));
		}
		else
			return -1;
	}

//------------------------------------------------------------//
	//-----------------add documents-------------------//

	public void AddDocs(String url, String doc){
		BasicDBObject document = new BasicDBObject();
		document.put("url", url);
		document.put("doc", doc);
		docs.insert(document);
	}

	//-------------------------------------------------//

	//-----------------add title-----------------//

	public void AddTitles(String url, String title){
		BasicDBObject document = new BasicDBObject();
		//System.out.println("url from database : " +url);
		//System.out.println("title from database : "+title);
		document.put("url", url);
		document.put("title", title);
		Titles.insert(document);


	}

	//------------------------------------------//


	//----------------- Add words data---------------------------//

	public void AddWords(Vector<word> data, String url) {

		for(int i = 0; i < data.size(); i++) {
			BasicDBObject b = new BasicDBObject();
			BasicDBObject b1 = new BasicDBObject();
			BasicDBObject b2 = new BasicDBObject();
			BasicDBObject b3 = new BasicDBObject();

			b.put("word", data.get(i).text());

			DBCursor cursor = table.find(b);
			if(cursor.hasNext()) {
				b1.put("url", url);
				b1.put("repeate", data.get(i).Repeatingtimes());

				data.get(i).Vectortoarray();
				int n = data.get(i).getarray().length;
				word [] words = new word[n];
				words = data.get(i).getarray();
				Vector <BasicDBObject> original = new Vector();
				//System.out.println("n " + n);
				if(n > 0) {
					for(int j = 0; j < words.length; j++) {
						b2.put("original", words[j].text());
						b2.put("positions", words[j].getarrayofpostion());
						b2.put("repeate", words[j].Repeatingtimes());
						b2.put("rank", words[j].getrank());
						original.add(b2);
					}
				}

				b1.put("data", original);
				b3.put("info", b1);
				BasicDBObject b4 = new BasicDBObject("$push",b3);
				table.update(b, b4);

			}

			else {
				Vector<BasicDBObject> info = new Vector();
				b1.put("url", url);
				b1.put("repeate", data.get(i).Repeatingtimes());
				Vector <BasicDBObject> original = new Vector();
				data.get(i).Vectortoarray();
				int n = data.get(i).getarray().length;
				word [] words = new word[n];
				words = data.get(i).getarray();
				System.out.println("n " + n);
				if(n > 0) {
					//word [] words = new word[n] ;
					words = data.get(i).getarray();
					System.out.println("size el words " +words.length );
					for(int j = 0; j < words.length; j++) {

						b2.put("original", words[j].text());
						System.out.println("size el info " + words[j].text());
						b2.put("positions", words[j].getarrayofpostion());
						b2.put("repeate", words[j].Repeatingtimes());
						b2.put("rank", words[j].getrank());
						original.add(b2);
					}
				}

				b1.put("data", original);
				info.add(b1);
				b.put("info", info);
				table.insert(b);

			}
		}

	}
	//--------------------------------------------------------------------//

	//-------------------edit docs--------------------------------//
	public void editDoc(String url, String doc) {

		BasicDBObject newdoc = new BasicDBObject();
		BasicDBObject newdoc1 = new BasicDBObject();
		BasicDBObject newdoc2 = new BasicDBObject();

		newdoc.put("url", url);
		DBCursor cursor = docs.find(newdoc);
		if(cursor.hasNext()) {

			newdoc1.put("doc", doc);
			newdoc2.put("$set", newdoc1);
			docs.update(newdoc, newdoc2);

		}

		else {
			newdoc.put("doc", doc);
			docs.insert(newdoc);
		}



	}
	//-------------------------------------------------------//

	//-------------edit titles------------------//

	public void editTitle(String url, String title) {

		BasicDBObject newdoc = new BasicDBObject();
		BasicDBObject newdoc1 = new BasicDBObject();
		BasicDBObject newdoc2 = new BasicDBObject();

		newdoc.put("url", url);
		DBCursor cursor = Titles.find(newdoc);
		if(cursor.hasNext()) {

			newdoc1.put("title", title);
			newdoc2.put("$set", newdoc1);
			Titles.update(newdoc, newdoc2);

		}

		else {

			newdoc.put("title",title);
			Titles.insert(newdoc);

		}


	}

	//-------------------------------------------------//

	//-------------retrieve documents--------------//
	public  String retdoc(String url) {

		BasicDBObject b = new BasicDBObject();
		b.put("url", url);
		DBCursor cursor = docs.find(b);
		return (String) (cursor.next().get("doc"));

	}

//-------------------------------------------------------//

//---------------------retrieve title----------------//

	public String rettitle(String url) {

		BasicDBObject b = new BasicDBObject();
		b.put("url", url);
		DBCursor cursor = Titles.find(b);
		return (String) (cursor.next().get("title"));

	}

//------------------------------------------------------------//

//---------------retrieve the word data---------------//

	public Vector<Word_Data> retData(String Stem){

		Vector <Word_Data> data = new Vector();
		BasicDBObject Query = new BasicDBObject();
		Query.put("word", Stem);
		DBCursor cursor = table.find(Query);

		if(cursor.hasNext()) {

			BasicDBList info = (BasicDBList) cursor.next().get("info");
			System.out.println("size el info " + info.size());
			for(int i = 0; i < info.size(); i++) {

				BasicDBObject b = (BasicDBObject) info.get(i);
				String link = (String) b.get("url");
				System.out.println("link inside data"+link);
				double repeate = (double) b.get("repeate");
				System.out.println("repeat el stem " + repeate);
				BasicDBList words = (BasicDBList) b.get("data");
				System.out.println("size el words " + words.size());
				Vector <word> W = new Vector();
				for(int j = 0; j < words.size(); j++) {
					BasicDBObject b1 = (BasicDBObject) words.get(j);
					String original = (String) b1.get("original");
					word w = new word(original, "");
					BasicDBList pos = (BasicDBList) b1.get("positions");
					if (pos != null)
					{
						Integer[] positions = new Integer[pos.size()];
					for (int k = 0; k < pos.size(); k++)
					  {
						positions[k] = (Integer) pos.get(k);
					  }
						w.setpos(positions);
					}

					double rep = (double) b1.get("repeate");
					double rank = (double) b1.get("rank");


					w.setrep(rep);
					w.setrank(rank);
					W.add(w);
					Word_Data wd = new Word_Data(link,repeate,W,Stem);
						data.add(wd);
				}




			}

			return data;
		}

		else
		{
			return null;
		}

	}

//---------------------------------------------------------------//

	//---------------show word data-----------//

	public void ShowData(Vector<word>v)
	{
		BasicDBObject searchQuery = new BasicDBObject();

		for (int i = 0; i < v.size(); i++)
		{
			searchQuery.put("word",v.get(i).text() );

			DBCursor cursor = table.find(searchQuery);

			while (cursor.hasNext()) {
				System.out.println(cursor.next());
			}

		}
	}
	//-----------------------------------------------------//

	//-------------------------show titles------------//

	public void showtitles(String url) {
		BasicDBObject ob = new BasicDBObject();
		ob.put("url", url);
		DBCursor cursor = Titles.find(ob);

		if(cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	}
	//------------------------------------------------//

	//--------delete database----------//
	public void Deletecoll()
	{
		db.dropDatabase();
	}
	//---------------------------------------------------------------//


	//---------------update word-------------------//
	public void update(String url)
	{
		BasicDBObject b1 = new BasicDBObject();
		BasicDBObject b2 = new BasicDBObject();
		BasicDBObject b3 = new BasicDBObject();
		BasicDBObject b4 = new BasicDBObject();
		b2.put("link", url);
		b3.put("info", b2);
		b4.put("$pull", b3);

		table.update(b1, b4, false, true);

		//AddData(url,data);

	}
	//--------------------------------------------------------//



}
