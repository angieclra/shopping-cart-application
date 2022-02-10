package model;

import java.util.ArrayList;
import java.util.Locale;

public class ShoppingCart {

    private ArrayList<Item> shoppingCartItems;
    private double price;
    private String name;

    public ShoppingCart() {
        shoppingCartItems = new ArrayList<Item>();
    }

    public void addToCart(String name, int quantity, double price) {
        for (int i = 0; i < quantity; i++) {
            Item item = new Item(name, quantity, price);
            shoppingCartItems.add(item);
        }
        this.name = name;
        this.price += (price * quantity);
    }

    public void removeFromCart(String name, int quantity) {
        for (int i = 0; i < getNumItem(); i++) {
            Item item = shoppingCartItems.get(i);
            if (item.getItemName().equals(name)) {
                if (quantity != 0) {
                    shoppingCartItems.remove(i);
                    quantity = quantity - 1;
                    price = getPriceAltogether() - item.getItemPrice();
//                } else {
//                    System.out.println("No item named " + name + " found in the cart. "
//                            + "Nothing is removed from the cart.");
//                }
                } else {
                    break;
                }
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

    public String invoiceReceipt() {
        String content;

        content = "INVOICE\n" + "_____________";
        for (int i = 0; i < getNumItem(); i++) {
            content += "\nName: " + shoppingCartItems.get(i).getItemName().toUpperCase(Locale.ROOT) + "\nPrice: $"
                    + shoppingCartItems.get(i).getItemPrice() + "\n--------------";
        }
        return content;
    }

}

