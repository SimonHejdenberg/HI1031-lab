/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import Logic.Cart;
import Logic.Category;
import Logic.User;
import Logic.Item;
import Logic.ModelOrder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

/**
 *
 * @author simon
 */
public class ModelOrderDB extends ModelOrder{
    
    public ModelOrderDB(Cart cart) {
        super(cart);
    }
    
    public ModelOrderDB(User user, LinkedList<Item> contents){
        super(user, contents);
    }

    public static Collection getAllIOrders() {
        ArrayList<ModelOrderDB> orders = new ArrayList<ModelOrderDB>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found " + e.getMessage());
            e.printStackTrace();
        }
        try ( Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lab1", "sqladmin", "truepassword1")) {

            String sql = "SELECT * FROM lab1.t_items";
            try ( PreparedStatement stmt = conn.prepareStatement(sql)) {
                ResultSet rs = stmt.executeQuery();
                orders = helperResultSet(rs);
            } catch (SQLException innerSqlEx) {
                innerSqlEx.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }
    
    private static ArrayList<ModelOrderDB> helperResultSet(ResultSet rs) throws SQLException {
        ArrayList<ModelOrderDB> orders = new ArrayList<ModelOrderDB>();
        while (rs.next()) {
            int userid;
            int orderid;
            
            /*
            orders.

            HDFIWG9UIWHIÅVAGHÅQ HGHQIGE E GHEGHGHIGOHGOHGOHFUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUCK
            
            
            
            
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String description = rs.getString("description");
            int quantity = rs.getInt("quantity");
            double price = rs.getDouble("price");
            String categoryString = rs.getString("category");
            Category category = Category.valueOf(categoryString);
            String pictureURL = rs.getString("url");
            System.out.println(name);
            items.add(new ItemDB(id, name, price, description, quantity, category, pictureURL));
        */}
        return orders;
    }

    public static boolean addOrder(Item item) {
        int returnID = -1;
        try {
            Connection con = DBManager.getConnection();
            String sql = "INSERT INTO T_ITEM(name, price, description, quantity, category,url) VALUES(?,?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, item.getName());
            stmt.setDouble(2, item.getPrice());
            stmt.setString(3, item.getDescription());
            stmt.setInt(4, item.getQuantity());
            stmt.setString(5, item.getCategory().toString());
            stmt.setString(6, item.getPictureUrl());
            returnID = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnID > 0 ? true : false;
    }

}
