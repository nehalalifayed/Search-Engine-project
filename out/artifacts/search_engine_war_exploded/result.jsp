<%-- 
    Document   : result
    Created on : Apr 26, 2018, 2:28:44 AM
    Author     : engdoaa.etman
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Result</title>
    <link rel="icon" href="three.png" >
    <link rel= "stylesheet" href="web3.css">
    </head>
    <body>

         <%@page import ="f.Final_Result" %>
         <%@page import ="javax.servlet.http.HttpSession"%>
        <%
            String r;
            HttpSession session1=request.getSession();
            r=(String)session1.getAttribute("QQ");
            Final_Result [] Data= (Final_Result [] )session1.getAttribute("dataresult");
            String f = (String)request.getAttribute("buttonnum");
       // r = (String)request.getAttribute("q");
        %>
         <a style="color:darkblue;margin-left:1px;font-family: 'Arial Unicode MS'" href="index.jsp"> Results For Searching about:
             <span style="color: blue;font-family:'Arial Unicode MS'"><%= r%></span> </a>
        <%
        if(f!="" && f!=null)
        {
       // Integer.parseInt(f);
      int i =  Integer.parseInt(f);
           // int i=0;
            int x;
            if(Data.length < (i*5)+5) x=Data.length;
            else x=(i*5)+5;
       for (int j=i*5;j<x;j++) {%>



          
           <a href=<%=Data[j].geturl()%> > <%=Data[j].gettitle()%></a>
           <a href=<%=Data[j].geturl()%> class="one"> <%= Data[j].geturl()%></a>
           <p class ="two"> <%=Data[j].getmetadata()%> </p>

        <% }}%>
       
     <form method="GET" action="backend" id ="backend"> 
                    
         <%
             for (int u=0; u<Data.length/5;u++)
             { %>
                 
             <input type="submit" class="three" value=<%=u%>  name=<%=u%> >
           
        <%}%>
 
      </form>
        
       
    </body>
</html>
