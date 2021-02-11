


package WebShop;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddUser extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            try (Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/e-buy?useTimezone=true&serverTimezone=UTC&autoReconnect=true&useSSL=false","root","123");){
                PreparedStatement ps=conn.prepareStatement("insert into user(firstname,lastname,money) values(?,?,?)");
                String firstName=request.getParameter("firstname");
                String lastName=request.getParameter("lastname");
                double money=Double.valueOf(request.getParameter("money"));
                ps.setString(1, firstName);
                ps.setString(2, lastName);
                ps.setDouble(3, money);
                ps.execute();
                response.sendRedirect("UsersPage.jsp");
            } catch (SQLException ex) {
                out.println(ex.getMessage());
            }  
            
           
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
