/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author simon
 */
public class Cart implements Cloneable{
    
    private final User user;
    private LinkedList<Item> contents;
    private Map<Integer, Integer> contMap = null;  //ItemID, amount
    
    public Cart(User user){
        this.user = user;
        this.contents = new LinkedList<>();
        this.contMap = new HashMap<>();
    }
    
    public Cart(User user, LinkedList<Item> contents){
        this.user = user;
        this.contents = contents;
    }
    
    public Cart(Cart cart){
        this.user = cart.getUser();
        this.contents = cart.getContents();
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @return the contents
     */
    public LinkedList<Item> getContents() {
        return contents;
    }
    
    public LinkedList<Item> getContentsCopy() {
        LinkedList<Item> contentsCopy = new LinkedList<>();
        for (Item content : contents) {
            contentsCopy.add(content);
        }
        return contentsCopy;
    }

    /**
     * @return the contents
     */
    public Map<Integer,Integer> getContMap() {
        return contMap;
    }
    
    public Map<Integer,Integer> getContMapCopy() {
        Map<Integer,Integer> contMapCopy = new HashMap<>();
        contMapCopy.putAll(contMap);
        return contMapCopy;
    }
    
    public ModelOrder convertIntoOrder() throws CloneNotSupportedException{
        return (ModelOrder) this.clone();
    }
    
    public void addItem(Integer itemID, Integer amount){
        if (contMap != null) {
            int currentAmount = contMap.containsKey(itemID) ? contMap.get(amount) : 0;
            contMap.put(itemID, currentAmount + amount);
        }
    }
    
    public void removeItem(Integer itemID, Integer amount){
        if (contMap != null && contMap.containsKey(itemID)) {
            int currentAmount = contMap.get(itemID);
            if (currentAmount - amount > 0) {
                contMap.put(itemID, currentAmount - amount);
            } else {
                contMap.remove(itemID);
            }
        }
    }
    
    public void clearCart(){
        contMap.clear();
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        try {
            while (getContents().iterator().hasNext()) {
                Item item = getContents().iterator().next();
                sb.append(item.getName()).append(" (x").append(item.quantity).append(")\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
