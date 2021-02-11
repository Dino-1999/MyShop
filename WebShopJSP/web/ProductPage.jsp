
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product</title>
    </head>
    <body>
        <form action="AddProduct" method="GET">
            Ime: <input type="text" name="name"/>
            Cijena: <input type="text" name="price"/>
            Na stanju: <input type="number" name="kolicina"/>
            <input type="submit" value="Dodaj"/>
        </form>
        <%@include file="Product.jsp" %>
        <a href="index.jsp" align="center">Natrag na početak</a>
    </body>
</html>
