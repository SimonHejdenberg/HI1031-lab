package DataLayer;

import DataLayer.DBManager;
import Enums.Category;
import Enums.Category;
import Logic.Item;
import Logic.Order;
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

    public static Collection getCustomerOrders(int customerID) {    //rename to getAllCustomerOrders ?
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
    
    /*
    Förslag på T_Order:
    CREATE TABLE IF NOT EXISTS T_Order(
        OrderID INT NOT NULL,
        UserID INT NOT NULL,
        OrderDate DATE NOT NULL,
        --TotalCost DOUBLE NOT NULL,      // Ska vi ha denna?
        CONSTRAINT t_order_pk PRIMARY KEY(OrderID, UserID),
        CONSTRAINT t_order_userid_fk FOREIGN KEY(UserID) REFERENCES T_User(UserID) ON DELETE CASCADE,
    );
    
    Förslag på T_OrderItems:
    CREATE TABLE IF NOT EXISTS T_Order(
        OrderID INT NOT NULL,
        ItemID INT NOT NULL,
        Amount INT NOT NULL,
        CONSTRAINT t_orderitems_pk PRIMARY KEY(OrderID, ItemID),
        CONSTRAINT t_orderitems_orderid_fk FOREIGN KEY(OrderID) REFERENCES T_Order(OrderID) ON DELETE CASCADE,
        CONSTRAINT t_orderitems_itemid_fk FOREIGN KEY(ItemID) REFERENCES T_Item(ItemID) ON DELETE SET NULL,
    );
    
    CREATE TABLE IF NOT EXISTS T_Created(
    BookID INT NOT NULL,
    AuthorID INT NOT NULL,
    CONSTRAINT t_created_pk PRIMARY KEY(BookID, AuthorID),
    CONSTRAINT t_created_bookid_fk FOREIGN KEY(BookID) REFERENCES T_Book(BookID) ON DELETE CASCADE,
    CONSTRAINT t_created_authorid_fk FOREIGN KEY(AuthorID) REFERENCES T_Author(AuthorID) ON DELETE CASCADE
    );
    
    
    */
    public static Collection submitCustomerOrder(Order order) { //T_Order: | OrderID(pk) | UserID(fk,pk) | OrderDate | TotalCost (om vi ska ha den) |
        PreparedStatement pstmt = null;
        try {
            Connection con = DBManager.getConnection();
            con.setAutoCommit(false);
            String sql = "INSERT INTO T_ORDER (OrderID, UserID, OrderDate) VALUES (?,?,?)";
            pstmt = con.prepareStatement(sql);
            
            
            StringBuilder sqlBuilder = new StringBuilder();
            try {
                sqlBuilder.append("INSERT INTO T_ORDER (OrderID, UserID, OrderDate) VALUES (");
                sqlBuilder.append(order.getId());
                sqlBuilder.append(",");
                sqlBuilder.append(order.getUser().getUserID());
                sqlBuilder.append(",");
                sqlBuilder.append(order.getOrderDate());  // korrekt format?
                sqlBuilder.append(");");   //inget ; behövs?
                
                
            } catch (Exception e) {
            }

            
        } catch (Exception e) {
        } finally {
            try {   //temp
                pstmt.close();
            } catch (Exception e) {
            }
        }
        /*
        INSERT INTO T_Author (firstName, lastName, dateOfBirth) VALUES ('J.R.R.', 'Tolkien', '1892-01-03');
        INSERT INTO T_Author (firstName, lastName, dateOfBirth) VALUES ('J.K.', 'Rowling', '1965-07-31');

        INSERT INTO T_Created (BookID, AuthorID) Values ((SELECT Bookid from T_BOOK where BOOKID = 1),
        (select AuthorID from T_author where AuthorID = 1));
        INSERT INTO T_Created (BookID, AuthorID) Values ((SELECT Bookid from T_BOOK where BOOKID = 2),
        (select AuthorID from T_author where AuthorID = 1));
        INSERT INTO T_Created (BookID, AuthorID) Values ((SELECT Bookid from T_BOOK where BOOKID = 3),
        (select AuthorID from T_author where AuthorID = 1));
        INSERT INTO T_Created (BookID, AuthorID) Values ((SELECT Bookid from T_BOOK where BOOKID = 4),
        (select AuthorID from T_author where AuthorID = 2));
        */
        return null;
    }

    private static LocalDate convertToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
