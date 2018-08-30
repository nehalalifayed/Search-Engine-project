package f;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author engdoaa.etman
 */
public class Final_Result implements Comparable<Final_Result> {
    private String URL;
    private String metadata;
    private String title;
    private double rank ;
    public Final_Result(String x, String y ,String z)
     { 
       URL=x;
       metadata=y;
       title=z;
         
         
     }
    public Final_Result()
    {
    }
    public void seturl(String x){ URL=x;}
    public void setmetadata(String x){ metadata=x;}
    public void settitle(String x){ title=x;}
    public void setrank(double x){ rank=x;}
    public String geturl( ){return URL  ;}
    public String getmetadata( ){ return metadata  ;}
    public String gettitle( ){ return title  ;}
    public double getrank(){ return rank  ;}
    public static Final_Result[] GETDATA(String Query){
        Final_Result [] Data= new Final_Result[30];
        Data[0]=new Final_Result("www.google.com" ,"Ay klam fadyy","0"+"GOOGLE IT :)"+Query);
        Data[1]=new Final_Result("www.facebook.com" ,"Ay klam fadyy","1"+"Social :)"+Query);
        Data[2]=new Final_Result("www.instgram.com" ,"Ay klam fadyy","2"+"images :)"+Query);
        Data[3]=new Final_Result("www.google.com" ,"Ay klam fadyy","3"+"GOOGLE IT :)"+Query);
        Data[4]=new Final_Result("www.facebook.com" ,"Ay klam fadyy","4"+"Social :)"+Query);
        Data[5]=new Final_Result("www.instgram.com" ,"Ay klam fadyy","5"+"images :)"+Query);
        Data[6]=new Final_Result("www.google.com" ,"Ay klam fadyy","6"+"GOOGLE IT :)"+Query);
        Data[7]=new Final_Result("www.facebook.com" ,"Ay klam fadyy","7"+"Social :)"+Query);
        Data[8]=new Final_Result("www.instgram.com" ,"Ay klam fadyy","8"+"images :)"+Query);
        Data[9]=new Final_Result("www.google.com" ,"Ay klam fadyy","9"+"GOOGLE IT :)"+Query);
        Data[10]=new Final_Result("www.google.com" ,"Ay klam fadyy","10"+"GOOGLE IT :)"+Query);
        Data[11]=new Final_Result("www.facebook.com" ,"Ay klam fadyy","11"+"Social :)"+Query);
        Data[12]=new Final_Result("www.instgram.com" ,"Ay klam fadyy","12"+"images :)"+Query);
        Data[13]=new Final_Result("www.google.com" ,"Ay klam fadyy","13"+"GOOGLE IT :)"+Query);
        Data[14]=new Final_Result("www.facebook.com" ,"Ay klam fadyy","14"+"Social :)"+Query);
        Data[15]=new Final_Result("www.instgram.com" ,"Ay klam fadyy","15"+"images :)"+Query);
        Data[16]=new Final_Result("www.google.com" ,"Ay klam fadyy","16"+"GOOGLE IT :)"+Query);
        Data[17]=new Final_Result("www.facebook.com" ,"Ay klam fadyy","17"+"Social :)"+Query);
        Data[18]=new Final_Result("www.instgram.com" ,"Ay klam fadyy","18"+"images :)"+Query);
        Data[19]=new Final_Result("www.google.com" ,"Ay klam fadyy","19"+"GOOGLE IT :)"+Query);
        Data[20]=new Final_Result("www.google.com" ,"Ay klam fadyy","20"+"GOOGLE IT :)"+Query);
        Data[21]=new Final_Result("www.facebook.com" ,"Ay klam fadyy","21"+"Social :)"+Query);
        Data[22]=new Final_Result("www.instgram.com" ,"Ay klam fadyy","22"+"images :)"+Query);
        Data[23]=new Final_Result("www.google.com" ,"Ay klam fadyy","23"+"GOOGLE IT :)"+Query);
        Data[24]=new Final_Result("www.facebook.com" ,"Ay klam fadyy","24"+"Social :)"+Query);
        Data[25]=new Final_Result("www.instgram.com" ,"Ay klam fadyy","25"+"images :)"+Query);
        Data[26]=new Final_Result("www.google.com" ,"Ay klam fadyy","26"+"GOOGLE IT :)"+Query);
        Data[27]=new Final_Result("www.facebook.com" ,"Ay klam fadyy","27"+"Social :)"+Query);
        Data[28]=new Final_Result("www.instgram.com" ,"Ay klam fadyy","28"+"images :)"+Query);
        Data[29]=new Final_Result("www.google.com" ,"Ay klam fadyy","29"+"GOOGLE IT :)"+Query);
        return Data;
    }
    @Override
    public int compareTo(Final_Result o) {
      double compareQuantity = ((Final_Result) o).getrank(); 
		
		//ascending order
		return  (int)Math.round( compareQuantity - this.rank );  
    }
}
