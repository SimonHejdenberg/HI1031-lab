package UI;

import Controller.Category;

/**
 *
 * @author jemsann
 */
public class ItemInfo extends Controller.Item {

    public ItemInfo(int id, String name, double price, String description, int quantity, Category category) {
        super(id, name, price, description, quantity, category);
    }

    public ItemInfo(int id, String name, double price, Category category) {
        super(id, name, price, category);
    }

}
