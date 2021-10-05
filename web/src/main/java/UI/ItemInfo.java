package UI;

import Controller.Category;

/**
 *
 * @author jemsann
 */
public class ItemInfo extends Controller.Item {

    public ItemInfo(int id, String name, double price, String description, int quantity, Category category, String pictureUrl) {
        super(id, name, price, description, quantity, category, pictureUrl);
    }

    public ItemInfo(int id, String name, double price, Category category, pictureUrl) {
        super(id, name, price, category, pictureUrl);
    }

}
