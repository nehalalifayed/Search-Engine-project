package f;


import com.mongodb.DBObject;
import com.mongodb.*;
import java.util.Collections;
import java.util.Vector;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author engdoaa.etman
 */
public class Ranker {

    public Ranker()
    {}
    public  Query_op[]    getdata(String QUERY)
    {
        Vector<word> zeft1 =new Vector ();
        Vector<word> zeft2=new Vector ();
        Vector<word> zeft3 =new Vector ();
        Vector<word> zeft4 =new Vector ();
        Vector<word> zeft5 =new Vector ();
        Vector<word> zeft6 =new Vector ();
        word ay =new word("computing","");
        ay.setrep(10);
        ay.setrank(0.2);
        zeft1.add(ay);
        word ay1 =new word("computer","");
        ay1.setrep(5);
        ay1.setrank(0.3);
        zeft1.add(ay1);
        word ay3 =new word("computers","");
        ay3.setrep(4);
        ay3.setrank(.02);
        zeft1.add(ay3);
        //********************************************
        word ay4 =new word("Engineer","");
        ay4.setrep(5);
        ay4.setrank(0.1);
        zeft2.add(ay4);
        word ay5 =new word("engines","");
        ay5.setrep(15);
        ay5.setrank(0.4);
        zeft2.add(ay5);
        word ay6 =new word("engineering","");
        ay6.setrep(4);
        ay6.setrank(.02);
        zeft2.add(ay6);
        //******************
        //********************************************
        word ay7 =new word("beautuiful","");
        ay7.setrep(4);
        ay7.setrank(0.3);
        zeft3.add(ay7);
        word ay8 =new word("beauty","");
        ay8.setrep(3);
        ay8.setrank(0.4);
        zeft3.add(ay8);
        word ay9 =new word("beaut","");
        ay9.setrep(2);
        ay9.setrank(.02);
        zeft3.add(ay9);
        //******************
        //********************************************
        word ay10 =new word("computing","");
        ay10.setrep(2);
        ay10.setrank(0.3);
        zeft4.add(ay10);
        word ay11=new word("computer","");
        ay11.setrep(10);
        ay11.setrank(0.4);
        zeft4.add(ay11);
        //***************************************
        word ay12=new word("beautuiful","");
        ay12.setrep(10);
        ay12.setrank(0.2);
        zeft5.add(ay12);
        //*******************
        word ay13 =new word("Engineer","");
        ay13.setrep(5);
        ay13.setrank(0.1);
        zeft6.add(ay13);
        word ay14=new word("engineering","");
        ay14.setrep(5);
        ay14.setrank(0.4);
        zeft6.add(ay14);
        //******************
        Word_Data l0=new Word_Data("https://edition.cnn.com/sport/tennis",10,zeft1,"computer");
        Word_Data l1=new Word_Data("https://edition.cnn.com/sport/tennis",20,zeft2,"engineering");
        Word_Data l2=new Word_Data("https://edition.cnn.com/sport/tennis",30,zeft3,"beautuiful");
        Word_Data l3=new Word_Data("http://money.cnn.com/data/markets/",5,zeft4,"computer");
        Word_Data l4=new Word_Data("http://money.cnn.com/data/markets/",10,zeft5,"beautuiful");
        Word_Data l5=new Word_Data("http://money.cnn.com/data/markets/",4,zeft6,"engineering");

        Query_op[]  result =new Query_op[3];

        Vector <Word_Data> v0 = new Vector();
        Vector <Word_Data> v1 = new Vector();
        Vector <Word_Data> v2 = new Vector();
        v0.add(l0);
        v0.add(l3);

        v1.add(l1);
        v1.add(l5);

        v2.add(l2);
        v2.add(l4);



        result[0]=new Query_op("compute",v0,"computer");
        result[1]=new Query_op("engine",v1,"engineering");
        result[2]=new Query_op("beauty",v2,"beautuiful");
        return result;
    }
    public  Final_Result[] Ranking(Query_op[]  result) {
 //************************* Data For TESTING*************************************
         Database D = new Database("Sengine");
         String Query="";
//***************knowing Which pharse and which not***********************
Vector<Final_Result> final_result=new Vector();   
    if(result.length ==1){//*************RANK Phrase_results*******************************
        Query =result[0].getStem();
        Vector<Word_Data> temp0 =result[0].getvec();
        Double IDF=(double)temp0.size()/63;
        for (int i=0;i<temp0.size();i++)
        {
          Final_Result e=new Final_Result();
          e.seturl(temp0.get(i).getUrl());
          double r=temp0.get(i).getrep()*IDF;
          // r=r*Crawerler RAnk
         //  r=r*(double)D.retrank(temp0.get(i).getUrl())*10*10*10;
          e.setrank(r);
          final_result.add(e);
        }
        
        
    }//*************END RANK Phrase_results*******************************
   

 else{ //*************RANK not phrase_results******************************* 
        
//******************display before Any edit ******************
        for (int i=0;i<result.length;i++)
        { System.out.print(result[i].getStem() +" ");
           System.out.println("links:   ");

          for(int p=0; p<result[i].getvec().size();p++)
          {
              System.out.print(result[i].getvec().get(p).getUrl() +" ");
              System.out.println(result[i].getvec().get(p).getrep());
            for(int y=0; y<result[i].getvec().get(p).getData().size();y++)
            {
              System.out.print(result[i].getvec().get(p).getData().get(y).text()+"  ");
              System.out.print(result[i].getvec().get(p).getData().get(y).getrank()+"  ");
              System.out.println(result[i].getvec().get(p).getData().get(y).Repeatingtimes());
           }
          }

           System.out.println();
           
           
           }
  //*********************************************************************
  //*************Edit data to format more acessable**********************
  Vector<data_for_rank> data = new Vector<data_for_rank>();      
  for (int w=0;w<result.length;w++){
      
    String stem=result[w].getStem();
    String Orginall=result[w].getoriginal();
    Query=Query+" "+Orginall;
    Vector<Word_Data> temp =result[w].getvec();
    
     for (int u=0;u<temp.size();u++){
         
         word y=new word(stem,"");
         y.setorgininQuery(Orginall);
         y.setorignaldata(temp.get(u).getData());
         y.setIdf(temp.size());
         
         System.out.println("**************TEST ****"+y.getIDf());
         
         y.setrepeatTF(temp.get(u).getrep());
         int t=check(data,temp.get(u).getUrl());
         if(t==-1)
         {
             data_for_rank temp2=new  data_for_rank(temp.get(u).getUrl(),y); 
             data.add(temp2);
         }
         else 
         {
              data_for_rank temp2=data.get(t);
              data.remove(t);
              temp2.addword(y);
              data.add(temp2);
         }  
     }
  }
  //*********************Display data after edit ******************************
  System.out.println("After EDIT:");
  for (int d=0; d<data.size();d++)
  {
      System.out.println(data.get(d).getUrl());
      for(int d1=0;d1<data.get(d).getData().size();d1++)
      {
          System.out.print(data.get(d).getData().get(d1).text()+"   "+data.get(d).getData().get(d1).GetorgininQuery()+"   "
                 +data.get(d).getData().get(d1).Repeatingtimes()+"  ");
          System.out.print(" "+ data.get(d).getData().get(d1).getIDf()+"  ");
          System.out.println("Originals ");
          for(int y=0;y<data.get(d).getData().get(d1).getvec().size();y++)
          {
          System.out.print(data.get(d).getData().get(d1).getvec().get(y).text()+"   ");
          System.out.print(data.get(d).getData().get(d1).getvec().get(y).getrank()+"   ");
          System.out.println(data.get(d).getData().get(d1).getvec().get(y).Repeatingtimes()+"   ");
          
          }
      }
       System.out.println();
  } 
  
  //*********************************************************************
   // *******************Calcuting the rank************************
   System.out.println(":::::::RANKING::::::: ");
  data_for_rank [] data1 = new data_for_rank[data.size()];
   data1  = (data_for_rank[]) data.toArray(data1);
   
    
    for(int i=0;i<data1.length;i++)
    {
        System.out.println(data1[i].getUrl());
        double UrlRANK_relvance=0;
       Vector<word> tt= data1[i].getData();
       for(int j=0;j<tt.size();j++)
       {
        double rankstem= tt.get(j).getIDf()* tt.get(j).Repeatingtimes();
        System.out.println("Stem RAnk: "+ rankstem +" for  "+tt.get(j).text());
        String ORG= tt.get(j).GetorgininQuery();
        Vector<word> t=tt.get(j).getvec();
        double rankoriganals=0;
        for(int p=0; p<t.size();p++)
        {
          //  System.out.println(t.size());
          if (t.get(p).text().equals(ORG))
             rankoriganals+=(t.get(p).Repeatingtimes()*t.get(p).getrank()*0.8);
          else 
               rankoriganals+=(t.get(p).Repeatingtimes()*t.get(p).getrank()*0.2);
        }
        
        UrlRANK_relvance+= (rankstem+rankoriganals);
       } 
      //UrlRANK_relvance=UrlRANK_relvance*Crawerler rank
        System.out.println("RANKK"+D.retrank(data1[i].getUrl()));
       //UrlRANK_relvance=UrlRANK_relvance*(double) D.retrank(data1[i].getUrl())*10*10*10;

       data1[i].set_relavence_rank(UrlRANK_relvance);
       Final_Result t=new  Final_Result();
       t.seturl(data1[i].getUrl());
       t.setrank(UrlRANK_relvance);
       final_result.add(t);
        System.out.println(data1[i].get_relavence_rank());
    }
  } //*************End of RANK not phrase_results*******************************   
    
    //***********************sort &get Data from Urls FOR Phrase or NOT *************************************************
      Collections.sort(final_result);
      System.out.println("Sorted*****");
      for(int i=0;i<final_result.size();i++)
      {
       System.out.println(final_result.get(i).geturl() +"  "+final_result.get(i).getrank());
      }
           
      Final_Result [] datatoview =geturlinfo(final_result,Query,D);
     //****************************************************************************************************
       return   datatoview;
     }//***********End OF MAIN**************************
    public  int check(Vector<data_for_rank> a, String b)
    {
         for (int s=0;s<a.size();s++)   
         {
             if (a.get(s).getUrl() .equals(b))
             {
                 return s;
             }
             
         }
        return -1;
    }  
  
    public  String func(String s , String html1)
        {
           // parsing el html page we keda 
           Document doc = Jsoup.parse(html1);
           Elements r = doc.select("html");
           String html=r.text();
 
           String Sinppet ="";
           boolean contain = html.contains(s);
           if(contain)
           {
               int firstindex= html.indexOf(s);
               if (firstindex >= 100)
               {
                   Sinppet = html.substring(firstindex-100, firstindex+100);
                   return Sinppet;
               }
               else
               {
                Sinppet = html.substring(firstindex, firstindex+200);
                return Sinppet;
               }  
           }
           else{
               String[]splited = s.split("\\s+");
               String[]temp = s.split("\\s+");
               //System.out.println("da5alt fe el else ");
               int size = splited.length;
               for (int i=0 ; i<size ; i++)
               {
                   String x ="";
                   for (int j= 0 ;j<size-1;j++)
                   {
                       x+=temp[j];
                       x+=" ";
                   }
                  // System.out.println(x);
                   contain = html.contains(x);
                 //   System.out.println(contain);
           if(contain)
           {
               int firstindex= html.indexOf(x);
               if (firstindex >= 100)
               {
                   Sinppet = html.substring(firstindex-100, firstindex+100);
                   return Sinppet;
               }
               else
               {
                Sinppet = html.substring(firstindex, firstindex+200);
                return Sinppet;
               }  
           }
           else size--;
           if(size == 2) break;
               }
               ////////////////////////////////////////////////////////////////////////
               size = splited.length;
               for (int i=0 ; i<size ; i++)
               {
                   String x ="";
                   for (int j= i ;j<size;j++)
                   {
                       x+=temp[j];
                       x+=" ";
                   }
                  // System.out.println(x);
                   contain = html.contains(x);
                   //System.out.println(contain);
           if(contain)
           {
               int firstindex= html.indexOf(x);
               if (firstindex >= 100)
               {
                   Sinppet = html.substring(firstindex-100, firstindex+100);
                   return Sinppet;
               }
               else
               {
                Sinppet = html.substring(firstindex, firstindex+200);
                return Sinppet;
               }  
           }
               }
           
          ////////////////////////////////////////////////////////////////////////////////
               //   System.out.println(splited.length );
          for (int i=0 ; i< splited.length ;i++)
          {
                 String x = splited[i];
                 contain = html.contains(x);
                 //  System.out.println(x);
           if(contain)
           {
               int firstindex= html.indexOf(x);
               if (firstindex >= 100)
               {
                   Sinppet = html.substring(firstindex-100, firstindex+100);
                   return Sinppet;
               }
               else
               {
                Sinppet = html.substring(firstindex, firstindex+200);
                return Sinppet;
               }  
           }
          }
           }

            Sinppet = html.substring(0, 200);
         return Sinppet;
        }
    public   Final_Result[] geturlinfo(Vector<Final_Result>vec,String Query,Database D){
        
        
        Final_Result [] data0 = new Final_Result[vec.size()];
        data0  = (Final_Result[]) vec.toArray(data0);
        for (int p=0; p< data0.length;p++)
        {
         String x=data0[p].geturl();
         data0[p].settitle(D.rettitle(x));
         System.out.println(D.rettitle(x));
         String y=D.retdoc(x);
         String s=func(Query,y);
        data0[p].setmetadata(s);
        }
        
        return data0;
    }
    
      public static Final_Result[] GETDATA(String Query){
               
      return null;  
      }
     
}
