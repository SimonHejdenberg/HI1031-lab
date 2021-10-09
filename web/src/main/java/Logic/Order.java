package Logic;

import DataLayer.ItemDB;
import DataLayer.OrderDB;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jemsann
 */
public class Order {

    public int id;
    public LocalDate orderDate;
    public ArrayList<ItemDB> items;
    public Map<Integer, Integer> contMap;
    public User user;

    protected Order(int id, LocalDate orderDate, ArrayList<ItemDB> items) {
        this.id = id;
        this.orderDate = orderDate;
        this.items = items;
    }
    
    protected Order(User user, int id, Cart cart, LocalDate orderDate) {
        this.user = user;
        this.id = id;
        contMap.putAll(cart.getContMap());
        this.orderDate = orderDate;
    }

    public Order(int id, LocalDate orderDate) {
        this.id = id;
        this.orderDate = orderDate;
    }

    public static Collection getCustomerOrders(User user) {
        return OrderDB.getCustomerOrders(user.getUserID());
    }

    public static Collection submitCustomerOrder(Order order) {
        return OrderDB.submitCustomerOrder(order);
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
    
    public Map<Integer,Integer> getContMap() {
        return contMap;
    }
    
    public Map<Integer,Integer> getContMapCopy() {
        Map<Integer,Integer> contMapCopy = new HashMap<>();
        contMapCopy.putAll(contMap);
        return contMapCopy;
    }
    
    public User getUser(){
        return user;
    }
    
}
