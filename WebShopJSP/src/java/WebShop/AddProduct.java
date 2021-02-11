
package WebShop;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddProduct extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/e-buy?useTimezone=true&serverTimezone=UTC&autoReconnect=true&useSSL=false","root","123");) {
            PreparedStatement ps=conn.prepareStatement("insert into product (productname,price,kolicina) values(?,?,?)");
            String name=request.getParameter("name");
            double price= Double.valueOf(request.getParameter("price"));
            int kolicina=Integer.valueOf(request.getParameter("kolicina"));
            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.setInt(3, kolicina );
            ps.execute();
            response.sendRedirect("ProductPage.jsp");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

 
}
