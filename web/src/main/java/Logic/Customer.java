/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

/**
 *
 * @author simon
 */
public class Customer extends User {

    //Antingen har vi en simpel lista av items...
    private Cart cart = new Cart(this);
    

    //...Eller så använder vi klassen cart som är super till order. 
    //Tycker vi använder cart som är super, så slipper vi skriva mer kod
    private Cart cart2;

    public Customer(String firstname, String lastname, String username, String password, Enum seclevel) {
        super(firstname, lastname, username, password, seclevel);
        newCart();
    }

    public Cart getCart2() {
        return cart2;
    }

    public final void newCart() {
        cart2 = new Cart(this);
    }
}
