package Logic;

import UI.ItemInfo;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author jemsann
 */
public class ItemHandler {

    public static ArrayList<ItemInfo> items;

    public static Collection<ItemInfo> getItems() {
        Collection c = Item.getItems();
        items = new ArrayList<>();
        for (Iterator it = c.iterator(); it.hasNext();) {
            Item item = (Item) it.next();
            items.add(new ItemInfo(item.getId(), item.getName(), item.getPrice(), item.getDescription(), item.getQuantity(), item.getCategory(), item.getPictureUrl()));
            System.out.println(item.getName());
        }
        return items;
    }

    public static ItemInfo getItem(int id) {
        ItemInfo item = null;
        if (items != null) {
            item = items.get(id - 1);
            return item;
        } else {
            return null;
        }
    }

    public static boolean CreateOrder(Cart cart, User user) {
        Order order = new Order(user, -1, cart, LocalDate.now());
        try {
            boolean status = Order.submitCustomerOrder(order);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
