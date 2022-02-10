package model;

import java.util.ArrayList;
import java.util.Arrays;

public class ShoppingCart {

    private ArrayList<Item> shoppingCartItems;
    private double price;
    private String name;

    public ShoppingCart() {
        shoppingCartItems = new ArrayList<>();
    }

    public void addToCart(String name, int quantity, double price) {
        for (int i = 0; i < quantity; i++) {
            Item item = new Item(name, quantity, price);
            shoppingCartItems.add(item);
        }
        this.name = name;
        this.price += (price * quantity);
    }

    public void deleteFromCart(String name, int quantity) {
        for (int i = 0; i < getNumItem(); i++) {
            Item item = shoppingCartItems.get(i);
            if (quantity != 0) {
                if (item.getItemName().equals(name)) {
                    shoppingCartItems.remove(i);
                    quantity--;
                }
            } else {
                break;
            }
        }
    }

    public String getName() {
        return name;
    }

    public int getNumItem() {
        return shoppingCartItems.size();
    }

    public double getPriceAltogether() {
        return price;
    }

//    public String invoiceReceipt() {
//        String contents = "\nINVOICE\n";
//        contents += "\nItem\tName\tPrice";
//
//        for (int i = 0; i < shoppingCartItems.size(); i++) {
//            contents += shoppingCartItems.get(i).toString() + "\n";
//            System.out.println("\n Total Price:" + price);
//        }
//        return contents;
//    }
}
