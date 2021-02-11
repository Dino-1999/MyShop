/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebShop;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import shop.model.Product;
import shop.model.ProductReader;
import shop.model.User;
import shop.model.UserReader;

/**
 *
 * @author User Dino
 */
public class BuyProducts extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/e-buy?useTimezone=true&serverTimezone=UTC&autoReconnect=true&useSSL=false", "root", "123");) {
                int userId = Integer.valueOf(request.getParameter("userId"));
                int productId = Integer.valueOf(request.getParameter("productId"));
                double price = Double.valueOf(request.getParameter("price"));
                int quantity = Integer.valueOf(request.getParameter("quantity"));
                Statement st = conn.createStatement();
                PreparedStatement ps = conn.prepareStatement("insert into productuser (userID,idProduct,price,quantity) values(?,?,?,?)");
                ps.setInt(1, userId);
                ps.setInt(2, productId);
                ps.setDouble(3, price);
                ps.setInt(4, quantity);
                ps.execute();
                ProductReader pr = new ProductReader();
                List<Product> list = pr.productReader();
                UserReader ur = new UserReader();
                List<User> userList = ur.readUser();
                st.execute("select*from productuser");
                ResultSet rs = st.getResultSet();
                while (rs.next()) {
                    for (User user : userList) {
                        if (user.getId() == rs.getInt("userID")) {
                            if (user.getStanje() < (rs.getInt("quantity") * rs.getDouble("price"))) {
                                out.println("<div align='center' style='text-align=center'>");
                                out.println("<p>Taj kupac nema dovoljno novca</p>");
                                out.println("</div>");
                                out.println("<a align='center' href='index.jsp'>Natrag na početak</a>");
                            } else {
                                for (Product product : list) {
                                    if (product.getId() == rs.getInt("idProduct")) {
                                        if ((product.getKolicina() - rs.getInt("quantity")) > 0) {
                                            PreparedStatement prep = conn.prepareStatement("update product set kolicina=? where idProduct=?");
                                            prep.setInt(1, (product.getKolicina() - rs.getInt("quantity")));
                                            prep.setInt(2, product.getId());
                                            prep.executeUpdate();
                                             PreparedStatement pst = conn.prepareStatement("update user set money=? where userID=?");
                                                    pst.setDouble(1, (user.getStanje() - (rs.getDouble("price")*quantity)));
                                                    pst.setInt(2, user.getId());
                                                    pst.executeUpdate();
                                                    response.sendRedirect("index.jsp");
                                        } else {
                                            int cijena = product.getKolicina();
                                            while (cijena != 0) {
                                                cijena--;
                                                product.setKolicina(cijena);
                                                if (cijena == 0) {
                                                    PreparedStatement pst = conn.prepareStatement("update product set kolicina=? where idProduct=?");
                                                    pst.setInt(1, cijena);
                                                    pst.setInt(2, product.getId());
                                                    pst.executeUpdate();
                                                    out.println("<div align='center' style='text-align=center'>");
                                                    out.println("<p>Kupili ste sve zalihe tog artikla</p>");
                                                    out.println("</div>");
                                                    out.println("<a align='center' href='index.jsp'>Natrag na početak</a>");
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }

                            }
                        }
                    }

                }
                   st.execute("delete from productuser");
            } catch (SQLException ex) {
                out.print(ex.getMessage());
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
