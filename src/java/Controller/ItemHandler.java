package Controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import Model.Item;

/**
 *
 * @author jemsann
 */
public class ItemHandler {
    public static Collection<Item> getItems(String s){
        Collection c = Item.searchItems(s);
        ArrayList<Item> items = new ArrayList<Item>();
        for(Iterator it = c.iterator();it.hasNext();){
            Item item = (Item) it.next();
            items.add(new Item(item.getName()));
        }
        return items;
    }
}
