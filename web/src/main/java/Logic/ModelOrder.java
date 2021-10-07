/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author simon
 */
public class ModelOrder extends Cart{
    
    private final int orderID;
    private final LocalDate orderDate;
    
    public ModelOrder(Cart cart){
        super(cart);
        orderID = new Random().nextInt(10000);  // !!!!!!!!!!
        orderDate = LocalDate.now();
    }
    
    public ModelOrder(User user, LinkedList contents){
        super(user, contents);
        orderID = new Random().nextInt(10000);  // !!!!!!!!!!
        orderDate = LocalDate.now();
    }

    /**
     * @return the orderDate
     */
    public LocalDate getOrderDate() {
        return orderDate;
    }

    /**
     * @return the orderID
     */
    public int getOrderID() {
        return orderID;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Order no.")
                .append(this.getOrderID())
                .append("of user ")
                .append(this.getUser().getFirstname())
                .append(" ")
                .append(this.getUser().getLastname())
                .append(" made on ")
                .append(this.getOrderDate().toString())
                .append("\n\n")
                .append(super.toString());
        return sb.toString();
    }
}
