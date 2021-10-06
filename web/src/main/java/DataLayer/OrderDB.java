package DataLayer;

import DataLayer.DBManager;
import Logic.Category;
import Logic.Category;
import Logic.Item;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author jemsann
 */
public class OrderDB extends Logic.Order {

    private OrderDB(int id, LocalDate orderDate, ArrayList<ItemDB> items) {
        super(id, orderDate, items);
    }

    public OrderDB(int id, LocalDate orderDate) {
        super(id, orderDate);
    }

    public static Collection getCustomerOrders(int customerID) {
        ArrayList<OrderDB> orders = new ArrayList<OrderDB>();
        try {
            Connection con = DBManager.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM T_ORDER WHERE CustomerID = " + customerID);

            int id = rs.getInt("id");
            Date orderDate = rs.getDate("orderdate");
            orders.add(new OrderDB(id, convertToLocalDate(orderDate)));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public static OrderDB getCustomerOrder(int orderID, LocalDate date) {
        OrderDB order = new OrderDB(orderID, date);
        try {

            //behöver dubbla SQL statements, pga koppling Order - Item
            
            Connection con = DBManager.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT 1 FROM T_ORDEROVERVIEW WHERE OrderID = " + orderID);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                String categoryString = rs.getString("category");
                Category category = Category.valueOf(categoryString);
                String pictureUrl = rs.getString("url");
                order.items.add(new ItemDB(id, name, price, description, quantity, category, pictureUrl));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order.items != null ? order : null;
    }

    private static LocalDate convertToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
