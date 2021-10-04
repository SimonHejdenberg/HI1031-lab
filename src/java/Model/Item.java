package Model;

import DB.ItemDB;
import java.util.Collection;

/**
 *
 * @author jemsann
 */
public class Item {

    public String name;
    public double price;
    public int quantity;
    public Category category;

    public Item(String name) {
        this.name = name;
    }

    static public Collection searchItems(String search) {
        return ItemDB.searchItems(search);
    }

    public String getName() {
        return name;
    }

}
