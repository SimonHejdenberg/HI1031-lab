package DataLayer;

import DataLayer.DBManager;
import Enums.Category;
import Enums.Category;
import Logic.Item;
import Logic.Order;
import UI.ItemInfo;
import java.sql.Connection;
import java.sql.Date;
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
        throw new UnsupportedOperationException();  //!!!
    }

    public static Collection getAllCustomerOrders(int customerID) {
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
    
    public static boolean submitCustomerOrder(Order order) throws SQLException{
        Connection con = null;
        PreparedStatement prepStatTOrder = null; //T_Order: | OrderID(pk) | UserID(fk,pk) | OrderDate | TotalCost (om vi ska ha den) |
        PreparedStatement prepStatTOrderItems = null; //T_OrderItems: | OrderID(fk,pk) | ItemID(fk,pk) | Amount |
        int orderID = -1;
        try {
            con = DBManager.getConnection();
            con.setAutoCommit(false);
            
            String sqlTOrder = "INSERT INTO T_ORDER (UserID, OrderDate) VALUES (?,?)";
            prepStatTOrder = con.prepareStatement(sqlTOrder, prepStatTOrder.RETURN_GENERATED_KEYS);
            prepStatTOrder.setInt(1, order.getUser().getUserID());
            prepStatTOrder.setDate(2, java.sql.Date.valueOf(order.getOrderDate()));
            prepStatTOrder.executeUpdate();

            try (ResultSet generatedKeys = prepStatTOrder.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    orderID = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating order failed, no ID obtained.");
                }
            } catch (Exception e) {
            }
                
            try {
                String sqlTOrderItems = "INSERT INTO T_ORDER (OrderID, ItemID, Amount) VALUES (?,?,?)";
                prepStatTOrderItems = con.prepareStatement(sqlTOrderItems);
                
                for (Map.Entry<ItemInfo,Integer> en : order.contMap.entrySet()) {
                    prepStatTOrderItems.setInt(1, orderID);
                    prepStatTOrderItems.setInt(2, en.getKey().id);
                    prepStatTOrderItems.setInt(3, en.getValue());
                    prepStatTOrderItems.executeUpdate();
                }
                con.commit();
            } catch (Exception e) {
            }
        } catch (Exception e) {
            con.rollback();
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
