/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author simon
 */
public class Cart implements Cloneable{
    
    private final int userID;
    private final LinkedList<Item> contents = new LinkedList<>();
    
    public Cart(int userID){
        this.userID = userID;
    }
    
    public ModelOrder convertIntoOrder() throws CloneNotSupportedException{
        return (ModelOrder) this.clone();
    }
    
    public void addItem(Item newItem){  //rätt sätt att använda quantity?
        for (Item content : contents) {
            if (content.id == newItem.id) {
                content.quantity += newItem.quantity;
                return;
            }
        }
        contents.add(newItem);
    }
    
    public void removeItem(Item selectedItem){  //rätt sätt att använda quantity?
        Iterator it = contents.iterator();
        Item item;
        while (it.hasNext()) {
            item = (Item) it.next();    // varför ger den objekt här utan konvertering? Generellt iterator, inte item specifikt
            if (item.id == selectedItem.id) {
                if (item.quantity - selectedItem.quantity <= 0) {
                    it.remove();
                    return;
                } else {
                    item.quantity -= selectedItem.quantity;
                    return;
                }
            }
            
        }
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        try {
            while (contents.iterator().hasNext()) {
                Item item = contents.iterator().next();
                sb.append(item.getName()).append(" (x").append(item.quantity).append(")\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
