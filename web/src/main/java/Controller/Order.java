package Controller;

import DB.ItemDB;
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

    protected Order(int id, LocalDate orderDate, ArrayList<ItemDB> items) {
        this.id = id;
        this.orderDate = orderDate;
        this.items = items;
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
