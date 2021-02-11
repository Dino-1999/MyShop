
package shop.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class UserReader implements Serializable {
    public List<User> readUser(){
        try (Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/e-buy?useTimezone=true&serverTimezone=UTC&autoReconnect=true&useSSL=false","root","123");){
            Statement st=conn.createStatement();
            st.execute("select*from user");
            ResultSet rs=st.getResultSet();
            List<User> users=new ArrayList<>();
            while(rs.next()){
                User user=new User();
                user.setId(rs.getInt("userID"));
                user.setfName(rs.getString("firstname"));
                user.setlName(rs.getString("lastname"));
                user.setStanje(rs.getDouble("money"));
                users.add(user);
            }
            return users;
        } catch (Exception e) {
            return null;
        }
    }
}
