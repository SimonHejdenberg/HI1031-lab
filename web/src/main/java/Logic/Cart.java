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
    
    private final User user;
    private LinkedList<Item> contents;
    
    public Cart(User user){
        this.user = user;
        this.contents = new LinkedList<>();
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
    
    public ModelOrder convertIntoOrder() throws CloneNotSupportedException{
        return (ModelOrder) this.clone();
    }
    
    public void addItem(Item newItem){  //rätt sätt att använda quantity?
//        for (Item content : getContents()) {
//            if (content.id == newItem.id) {
//                content.quantity += newItem.quantity;
//                return;
//            }
//        }
//        getContents().add(newItem);
    }
    
    public void removeItem(Item selectedItem){  //rätt sätt att använda quantity?
//        Iterator it = getContents().iterator();
//        Item item;
//        while (it.hasNext()) {
//            item = (Item) it.next();    // varför ger den objekt här utan konvertering? Generellt iterator, inte item specifikt
//            if (item.id == selectedItem.id) {
//                if (item.quantity - selectedItem.quantity <= 0) {
//                    it.remove();
//                    return;
//                } else {
//                    item.quantity -= selectedItem.quantity;
//                    return;
//                }
//            }
//            
//        }
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
