

<%@page import="java.util.List"%>
<%@page import="shop.model.ProductReader"%>
<%@page import="shop.model.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Web Shop</title>
    </head>
    <body>
        <h1>Web Shop</h1>
        <table border='1'>
            <tr bgcolor='lightgray'><th>Ime</th><th>Cijena</th><th>Na stanju</th></tr>
            <% List<Product> products= new ProductReader().productReader();
              for(Product product:products){              
            %>
            <tr>
                <td><%=product.getName()%></td>
                <td><%=product.getPrice()%></td>
                <td><%=product.getKolicina()%></td>
                 <td>
                    <form action="DeleteProducts" method="GET">
                       <input type="hidden" value="<%=product.getId()%>" name="ID"/>
                        <input type="submit" value="delete"/>
                    </form>
                </td>
            </tr>
            <%
                }
            %>
        </table>
    </body>
</html>
