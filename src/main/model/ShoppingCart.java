package model;

import ui.ShoppingCartApp;

import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingCart {

    private ArrayList<Item> shoppingCartItems;
    private double price;

    public ShoppingCart() {
        shoppingCartItems = new ArrayList<>();
    }

    public void deleteItem(Item i) {
        shoppingCartItems.remove(i);
    }

    public void addToCart(String name, int quantity, double price) {
        for (int i = 0; i < quantity; i++) {
            Item item = new Item(name, quantity, price);
            shoppingCartItems.add(item);
        }
        this.price += (price * quantity);
    }

    public int getNumItem() {
        return shoppingCartItems.size();
    }

    public double getPriceAltogether() {
        return price;
    }

//    public String toString() { // print invoice???
//        String contents = "\nShopping Cart\n";
//        contents += "\nItem\tName\tPrice";
//
//        for (int i = 0; i < numItem; i++) {
//            contents += shoppingCartItems.get(i).toString() + "\n";
//            System.out.println("\n Total Price:" + price);
//        }
//        return contents;
//    }
}
