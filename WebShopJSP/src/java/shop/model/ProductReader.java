
package shop.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ProductReader {
    public List<Product> productReader() throws SQLException{
        try (Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/e-buy?useTimezone=true&serverTimezone=UTC&autoReconnect=true&useSSL=false","root","123");){
            Statement st=conn.createStatement();
            st.execute("select*from product");
            ResultSet rs=st.getResultSet();
            List<Product> products=new ArrayList<>();
            while(rs.next()){
               Product product=new Product();
               product.setId(rs.getInt("idProduct"));
               product.setName(rs.getString("productname"));
               product.setPrice(rs.getDouble("price"));
               product.setKolicina(rs.getInt("kolicina"));
               products.add(product);
            }
            
            return products;
        } catch (Exception e) {
            return null;
        }
    }
}
