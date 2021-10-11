/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import UI.ItemInfo;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author simon
 */
public class Cart implements Cloneable {

    private User user;
    private Map<Integer, Integer> contMap = null;  //ItemID, amount
    public double total;

    public Cart(User user) {
        this.user = user;
        this.contMap = new HashMap<>();
        this.total = 0;
    }

    //Går inte att använda egentligen, eftersom contents måste ge en totalsumma! 
    public Cart(User user, Map<Integer, Integer> contents, int totalCost) {
        this.user = user;
        this.contMap = new HashMap<>();
        this.contMap.putAll(contents);
        this.total = totalCost;
    }

    public Cart(Cart cart) {
        this.user = cart.getUser();
        this.contMap = new HashMap<>();
        this.total = 0;
    }

    public Cart() {
        this.contMap = new HashMap<>();
        this.total = 0;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the contents
     */
    public Map<Integer, Integer> getContMap() {
        return contMap;
    }

    public Map<Integer, Integer> getContMapCopy() {
        Map<Integer, Integer> contMapCopy = new HashMap<>();
        contMapCopy.putAll(contMap);
        return contMapCopy;
    }

    public void addItem(Integer item, Integer amount) {
        if (contMap != null) {
            System.out.println("adding! " + total);
            if (contMap.containsKey(item)) {
                System.out.println("I exists");
                int value = contMap.get(item);
                contMap.put(item, value + amount);
            } else {
                System.out.println("I exist now!");
                contMap.put(item, amount);
            }
        }
    }

    public void removeItem(Integer item, Integer amount) {
        if (contMap != null && contMap.containsKey(item)) {
            int currentAmount = contMap.get(item);
            if (currentAmount - amount > 0) {
                contMap.put(item, currentAmount - amount);
            } else {
                contMap.remove(item);
            }
        }
    }

    public double getTotal() {
        return total;
    }

    public void clearCart() {
        contMap.clear();
    }

    public void setTotal(double total) {
        this.total += total;
    }

}
