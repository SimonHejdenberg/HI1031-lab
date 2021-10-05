package main;

//package web.src.main.java;
import Controller.Item;
import UI.ItemInfo;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class App {

    // Connect to your database.
    // Replace server name, username, and password with your credentials
    public static void main(String[] args) throws ClassNotFoundException {
        Collection c = Item.getItems();
        ArrayList<ItemInfo> items = new ArrayList<>();
        for (Iterator it = c.iterator(); it.hasNext();) {
            Item item = (Item) it.next();
            items.add(new ItemInfo(item.getId(), item.getName(), item.getPrice(), item.getDescription(), item.getQuantity(), item.getCategory(), item.getPictureUrl()));
            System.out.println(item.getName());
        }
    }
}
