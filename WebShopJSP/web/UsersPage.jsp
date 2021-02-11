<%-- 
    Document   : index
    Created on : Feb 7, 2021, 5:22:37 PM
    Author     : User Dino
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="AddUser" method="GET">
            Ime: <input type="text" name="firstname"/>
            Prezime: <input type="text" name="lastname"/>
            Novac: <input type="text" name="money"/>
            <input type="submit" value="Dodaj"/>
        </form>
        <%@include file="Users.jsp" %>
        <a href="index.jsp" align="center">Natrag na početak</a>
    </body>
</html>
