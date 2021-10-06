/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Logic.Item;
import java.util.ArrayList;

/**
 *
 * @author simon
 */
public class Customer extends User{
        
    //Antingen har vi en simpel lista av items...
    private ArrayList<Item> cart1 = new ArrayList<>();
    
    //...Eller så använder vi klassen cart som är super till order. 
    //Tycker vi använder cart som är super, så slipper vi skriva mer kod
    private Cart cart2;
    
    public Customer(int userID, String firstname, String lastname, String username, String password, Enum seclevel){
        super(userID, firstname, lastname, username, password, seclevel);
        newCart();
    }

    /**
     * @return the cart
     */
    public ArrayList<Item> getCart1() {
        return cart1;
    }
    
    public Cart getCart2() {
        return cart2;
    }
    
    public final void newCart(){
        cart2 = new Cart(this.getUserID());
    }
}
