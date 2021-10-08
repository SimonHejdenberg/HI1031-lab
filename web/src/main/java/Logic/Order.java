package Logic;

import DataLayer.ItemDB;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author jemsann
 */
public class Order {

    public int id;
    public LocalDate orderDate;
    public ArrayList<ItemDB> items;
    public User user;

    protected Order(int id, LocalDate orderDate, ArrayList<ItemDB> items) {
        this.id = id;
        this.orderDate = orderDate;
        this.items = items;
    }
    
    protected Order(User user, int id, Cart cart, LocalDate orderDate) {
        this.user = user;
        this.id = id;
        //this.items =
        this.orderDate = orderDate;
    }

    public Order(int id, LocalDate orderDate) {
        this.id = id;
        this.orderDate = orderDate;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public ArrayList<ItemDB> getItems() {
        return items;
    }

    public void setItems(ArrayList<ItemDB> items) {
        this.items = items;
    }
    
    

}
