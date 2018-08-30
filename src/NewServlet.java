/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import f.*;
import com.mongodb.*;

/**
 *
 * @author engdoaa.etman
 */
@WebServlet(name = "serveltweb",urlPatterns = "/backend")
public class NewServlet extends HttpServlet {

  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
          throws ServletException, IOException {
      HttpSession session=request.getSession();
      if (request.getParameter("ac")!=null)
      {
        String Query=request.getParameter("THE Query");
        //PrintWriter out=response.getWriter();
        request.setAttribute("q", Query); 
        session.setAttribute("QQ", Query);
        Query_Processing Q=new Query_Processing();
          Query_op[]  result=  Q.processQuery(Query);
          Ranker R=new Ranker();
          Final_Result [] Data= R.Ranking(result);


          session.setAttribute("dataresult", Data);
      }
       
        String r="0";             
        for (int i=0;i<20;i++){
        String x=Integer.toString(i);
        String b1=request.getParameter(x);
       
        if (b1 != null)
        {
           r=b1;
        }
        }
        request.setAttribute("buttonnum", r);
        request.getRequestDispatcher("result.jsp").forward(request, response);
        
     
        
      
       
  }
   
    

}
