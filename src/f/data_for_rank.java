/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package f;

/**
 *
 * @author engdoaa.etman
 */

import java.util.Vector;
public class data_for_rank {
    private String url = null;
    private Double Rank_relvance;
     private Double Rank_pro;
      private Double final_Rank;
    private Vector<word> data = new Vector();
    public String getUrl() { return url; }
    public Vector<word> getData() { return data; }
   data_for_rank(String x,word y)
   {
       url=x;
       data.add(y);
   }
   public void addword(word y) 
   {
       data.add(y);
   }
    public void set_relavence_rank(double y) {Rank_relvance=y;}
    public void set_pro_rank(double y) {Rank_pro=y;}
    public void set_Final_rank(double y) {final_Rank=y;}
    public double get_relavence_rank() {return Rank_relvance;}
    public double get_pro_rank() {return Rank_pro;}
    public double get_Final_rank() {return final_Rank;}      
}
