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
    private Map<ItemInfo, Integer> contMap = null;  //ItemID, amount
    public double total;

    public Cart(User user) {
        this.user = user;
        this.contMap = new HashMap<>();
        this.total = 0;
    }

    //Går inte att använda egentligen, eftersom contents måste ge en totalsumma! 
    public Cart(User user, Map<ItemInfo, Integer> contents, int totalCost) {
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
            total += item.getPrice() * amount;
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
            total -= item.getPrice() * amount;
        }
    }

    public double getTotal() {
        return total;
    }

    public void clearCart() {
        contMap.clear();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        try {
            Set<ItemInfo> keySet = getContMap().keySet();
            Iterator<ItemInfo> it = keySet.iterator();
            while (it.hasNext()) {
                Item item = it.next();
                sb.append(item.getName()).append(" (x").append(item.quantity).append(")\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
