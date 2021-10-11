package DataLayer;

import DataLayer.DBManager;
import Enums.Category;
import Enums.Category;
import Logic.Item;
import Logic.Order;
import UI.ItemInfo;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 *
 * @author jemsann
 */
public class OrderDB extends Logic.Order {

    public OrderDB(int id, LocalDate orderDate) {
        super(id, orderDate);
    }

    public static OrderDB getCustomerOrder(Order order) {
        try {
            Connection con = DBManager.getConnection();
        } catch (Exception e) {
        }
        return null;
    }

    //String sql = "SELECT * FROM t_review, t_book WHERE UserID = ? AND t_review.BookID = t_book.BookID";
    /*
        String sql = "SELECT * FROM db_bookstore.t_book "
            + "WHERE BookID IN "
            + "(SELECT BookID from db_bookstore.t_created "
            + "WHERE AuthorID IN "
            + "(SELECT AuthorID from db_bookstore.t_author WHERE firstName LIKE ? OR lastName LIKE ?));";
     */
    public static Collection getAllCustomerOrders(int customerID) {
        try {
            Connection con = DBManager.getConnection();
            con.setAutoCommit(false);
            String sqlTOrder = "SELECT * FROM T_ORDERITEMS WHERE ORDERID IN ("
                    + "SELECT ORDERID FROMT T_ORDER WHERE USERID = ?) ORDER BY ORDERID ASC";
            PreparedStatement prepStatTOrder = con.prepareStatement(sqlTOrder);
            prepStatTOrder.setInt(1, customerID);

            ResultSet rs = prepStatTOrder.executeQuery();

            ArrayList<OrderDB> orders = new ArrayList<OrderDB>();

            while (rs.next()) {
                if (orders.size() > 0 && rs.getInt(1) == orders.get(orders.size() - 1).getId()) {
                    //orders.get(orders.size()-1).getContMap().put(
                    //        new ItemInfo(rs.getInt(2), sqlTOrder, count, Category.Android, sqlTOrder), )
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order != null ? order : null;
    }

    public static boolean submitCustomerOrder(Order order) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lab1", "sqladmin", "truepassword1");

        PreparedStatement prepStatTOrder = null; //T_Order: | OrderID(pk) | UserID(fk) | OrderDate | TotalCost (om vi ska ha den) |
        PreparedStatement prepStatTOrderItems = null; //T_OrderItems: | OrderID(fk,pk) | ItemID(fk,pk) | Amount |
        int orderID = -1;

        try {
            con.setAutoCommit(false);

            String sqlTOrder = "INSERT INTO t_order (UserID, OrderDate, TotalCost) VALUES (?,?,?)";
            prepStatTOrder = con.prepareStatement(sqlTOrder, prepStatTOrder.RETURN_GENERATED_KEYS);
            prepStatTOrder.setInt(1, order.getUser().getUserID());
            prepStatTOrder.setDate(2, java.sql.Date.valueOf(order.getOrderDate()));
            prepStatTOrder.setDouble(3, order.getTotal());
            prepStatTOrder.executeUpdate();

            try ( ResultSet generatedKeys = prepStatTOrder.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    orderID = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating order failed, no ID obtained.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                String sqlTOrderItems = "INSERT INTO t_orderitems (OrderID, ItemID, Amount) VALUES (?,?,?)";
                prepStatTOrderItems = con.prepareStatement(sqlTOrderItems);

                for (Map.Entry<ItemInfo, Integer> en : order.contMap.entrySet()) {
                    prepStatTOrderItems.setInt(1, orderID);
                    prepStatTOrderItems.setInt(2, en.getKey().id);
                    prepStatTOrderItems.setInt(3, en.getValue());
                    prepStatTOrderItems.executeUpdate();
                    System.out.println("Added " + orderID + " " + en.getKey().id);
                }
                con.commit();
            } catch (Exception e) {
            }

        } catch (Exception e) {
            con.rollback();
            e.printStackTrace();
            return false;
        } finally {
            try {   //temp
                prepStatTOrder.close();
                prepStatTOrderItems.close();
            } catch (Exception e) {
            }
        }
        return true;
    }

    private static LocalDate convertToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
