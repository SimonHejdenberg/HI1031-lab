package DataLayer;

import Enums.Category;
import DataLayer.DBManager;
import Logic.Item;
import java.sql.*;
import java.util.*;

/**
 *
 * @author jemsann
 */
public class ItemDB extends Logic.Item {

    public ItemDB(int id, String name, double price, String description, int quantity, Category category, String pictureUrl) {
        super(id, name, price, description, quantity, category, pictureUrl);
    }

    public ItemDB(int id, String name, double price, Category category, String pictureUrl) {
        super(id, name, price, category, pictureUrl);
    }

    public static Collection getAllItems() {
        ArrayList<ItemDB> items = new ArrayList<ItemDB>();
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
                items = helperResultSet(rs);
            } catch (SQLException innerSqlEx) {
                innerSqlEx.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    public static Collection searchItemsByName(String searchName) {
        ArrayList<ItemDB> items = new ArrayList<ItemDB>();
        try {
            Connection con = DBManager.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM T_ITEM WHERE Name = " + searchName);
            items = helperResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public static Collection searchItemsByCategory(Category searchCategory) {
        ArrayList<ItemDB> items = new ArrayList<ItemDB>();
        try {
            Connection con = DBManager.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM T_ITEM WHERE category = " + searchCategory.toString());
            items = helperResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public static Collection getItemsByStock(boolean inStock) {
        ArrayList<ItemDB> items = new ArrayList<ItemDB>();
        try {
            Connection con = DBManager.getConnection();
            Statement stmt = con.createStatement();
            String sql;
            if (inStock) {
                sql = "SELECT * FROM T_ITEM WHERE quantity > 0";

            } else {
                sql = "SELECT * FROM T_ITEM WHERE quantity = 0";
            }
            ResultSet rs = stmt.executeQuery(sql);
            items = helperResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    private static ArrayList<ItemDB> helperResultSet(ResultSet rs) throws SQLException {
        ArrayList<ItemDB> items = new ArrayList<ItemDB>();
        while (rs.next()) {

            int id = rs.getInt("id");
            String name = rs.getString("name");
            String description = rs.getString("description");
            int quantity = rs.getInt("quantity");
            double price = rs.getDouble("price");
            String categoryString = rs.getString("category");
            Category category = Category.valueOf(categoryString);
            String pictureURL = rs.getString("url");
            items.add(new ItemDB(id, name, price, description, quantity, category, pictureURL));
        }
        return items;
    }

    public static boolean addItem(Item item) {
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
