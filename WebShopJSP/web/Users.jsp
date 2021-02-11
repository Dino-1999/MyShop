

<%@page import="shop.model.UserReader"%>
<%@page import="shop.model.User"%>

<%@page import="shop.model.Product"%>
<%@page import="java.util.List"%>
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
            <tr bgcolor='lightgray'><th>ID</th><th>Ime</th><th>Prezime</th><th>Novac</th></tr>
            <% List<User> users= new UserReader().readUser();
              for(User user:users){              
            %>
            <tr>
                <td><%=user.getId()%></td>
                <td><%=user.getfName()%></td>
                <td><%=user.getlName()%></td>
                <td><%=user.getStanje()%></td>
                <td>
                    <form action="DeleteUser" method="GET">
                        <input type="hidden" value="<%=user.getId()%>" name="ID"/>
                        <input type="submit" value="delete"/>
                    </form>
                        <form action="BuyList.jsp" method="GET">
                        <input type="hidden" value="<%=user.getId()%>" name="userId"/>
                        <input type="submit" value="buy"/>
                    </form>
                </td>
            </tr>
            <%
                }
            %>
        </table>
    </body>
</html>
