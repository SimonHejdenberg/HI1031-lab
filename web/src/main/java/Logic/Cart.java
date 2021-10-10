/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import UI.ItemInfo;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author simon
 */
public class Cart implements Cloneable {

    private User user;
    private LinkedList<Item> contents;
    private Map<ItemInfo, Integer> contMap = null;  //ItemID, amount

    public Cart(User user) {
        this.user = user;
        this.contents = new LinkedList<>();
        this.contMap = new HashMap<>();
    }

    public Cart(User user, LinkedList<Item> contents) {
        this.user = user;
        this.contents = contents;
    }

    public Cart(Cart cart) {
        this.user = cart.getUser();
        this.contents = cart.getContents();
    }

    public Cart() {
        this.contMap = new HashMap<>();
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
    public Map<ItemInfo, Integer> getContMap() {
        return contMap;
    }

    public Map<ItemInfo, Integer> getContMapCopy() {
        Map<ItemInfo, Integer> contMapCopy = new HashMap<>();
        contMapCopy.putAll(contMap);
        return contMapCopy;
    }


    public void addItem(ItemInfo item, Integer amount) {
        if (contMap != null) {
            int currentAmount = contMap.containsKey(item) ? contMap.get(amount) : 0;
            contMap.put(item, currentAmount + amount);
        }
    }

    public void removeItem(ItemInfo item, Integer amount) {
        if (contMap != null && contMap.containsKey(item)) {
            int currentAmount = contMap.get(item);
            if (currentAmount - amount > 0) {
                contMap.put(item, currentAmount - amount);
            } else {
                contMap.remove(item);
            }
        }
    }

    public void clearCart() {
        contMap.clear();
    }

    @Override
    public String toString() {
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
