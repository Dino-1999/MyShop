<%-- 
    Document   : BuyList
    Created on : Feb 7, 2021, 7:35:12 PM
    Author     : User Dino
--%>

<%@page import="shop.model.ProductReader"%>
<%@page import="java.util.List"%>
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
            <tr bgcolor='lightgray'><th>Naziv</th><th>Cijena</th>Na stanju</tr>
            <% List<Product> products= new ProductReader().productReader();
              for(Product product:products){              
            %>
            <tr>
                <td><%=product.getName()%></td>
                <td><%=product.getPrice()%></td>
                <td><%=product.getKolicina()%></td>
                 <td>
                     
                    <form action="BuyProducts" method="GET">
                        <input type="text" size="3" name="quantity"/>
                        <input type="hidden" value="<%=product.getId()%>" name="productId"/>
                        <input type="hidden" value="<%=Integer.valueOf(request.getParameter("userId"))%>" name="userId">
                        <input type="hidden" value="<%=product.getPrice()%>" name="price">
                        <input type="submit" value="Add to cart"/>
                    </form>
                    
                </td>
            </tr>
            <%
                }
            %>
        </table>
    </body>
</html>
