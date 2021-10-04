package DB;

import Model.Item;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author jemsann
 */
public class ItemDB {

    
    static public Collection searchItems(String search){
        
        ArrayList<Item> items = new ArrayList<Item>();
        items.add(new Item("sak a"));
        items.add(new Item("sak b"));
        
        return items;
    }
}
