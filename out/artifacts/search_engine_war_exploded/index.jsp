<%-- 
    Document   : interface
    Created on : Apr 26, 2018, 2:28:25 AM
    Author     : engdoaa.etman
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="icon" href="three.png" >
         <link rel= "stylesheet" href="web.css">
        <title>Search Engine</title>
    </head>
    <body>
       <h1> we will find what you need  </h1>
   <form action="backend" method="GET" id ="backend">
      <input type="text" placeholder="what do you want to search about ?" name="THE Query" class ="one"> 
      <input type = "submit" class = "two" name ="ac" value="search">
   </form>
    </body>
</html>
