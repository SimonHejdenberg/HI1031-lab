package main;

//package web.src.main.java;
import Logic.Item;
import UI.ItemInfo;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/*

    NOTES
Om vi ska följa MVC så måste vi flytta en del grejer som ligger i Controllern och lägga dem i Model.

*/

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
