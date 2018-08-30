package f;


import java.util.Vector;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author engdoaa.etman
 */
final class word {


    private final String text;

    private final String tag;
    private  double repeat;
    private  int position=0;
    public  word [] orginalarr ;
    private  double rank = 0;
    Vector<word> original = new Vector<word>();
    Vector<Integer> postions = new Vector<Integer>();
    // دي الي نهائيه
    private  Integer [] postionsarr ;

    private  String ORgintext;//inquery
    private double idf;



    // point initialized from parameters
    public word(String x, String y) {
        this.text = x;
        this.tag = y;
        repeat=1;

    }
    //********

    public void Addtoarray(word x )
    {
        original.add(x);
    }
    public void Vectortoarray()
    {
    orginalarr = new word[original.size()];
    orginalarr  = (word[]) original.toArray(orginalarr);

    }
    public word [] getarray ()
    {
        return orginalarr;
    }
    public Vector<word> getvec()
    {
        return original;
    }
    public void setorignaldata (Vector<word>  t)
    {
        original=t;
    }
    //**********
    public void Vectortoarraypostion()
    {

        for(int y=0;y<original.size();y++)
        {
            if(original.get(y)==null)
                original.remove(y);
        }
    postionsarr = new Integer[postions.size()];
    postionsarr  = (Integer[]) postions.toArray(postionsarr);

    }

     public void Addpostion(Integer x )
    {
        postions.add(x);
    }

     public Integer [] getarrayofpostion ()
    {
        return postionsarr;
    }



//*******************************


    // Accessory methods
    public String text() { return text; }
    public String tag() { return tag; }
    public double Repeatingtimes() { return repeat; }
    public void Increpeat() {  repeat=repeat+1; }
    public void setrepeatTF(double x) {  repeat=x; }
    public double  getrank() {return rank;}
    public void setrank(double v)
    {
        rank =  v;
    }
    public void incrank(double v)
    {
        rank =  rank+v;
    }
     public int  getposition() {return position;}
    public void setposition(int v) {position = v;}

    //NEw
    public String GetorgininQuery(){return ORgintext;}
     public void setorgininQuery(String  x){ORgintext=x;}
     public double getIDf(){return idf;}
     public void setIdf(int  x){ idf=(double)x/63;}


    // return a string representation of this point


    //***********************************************
    public void setpos ( Integer [] p) {
    	postionsarr = p;
    }

    public void setrep (double rep) {
    	repeat = rep;
    }

    public int getsize() { return original.size(); }

}






