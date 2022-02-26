package model;

import java.util.ArrayList;
import java.util.Locale;

// Represents a shopping cart with the items purchased in it
public class ShoppingCart {
    private ArrayList<Item> shoppingCartItems; // array list consisting of the items (objects)
    private double price; // add fields to represent changing properties of a shopping cart
    private String name;
    private int quantity;
    private Item item;

    // EFFECTS: constructs shopping cart
    public ShoppingCart() {
        item = new Item(name, quantity, price);
        shoppingCartItems = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: add item to cart with the given name, quantity, and price
    public void addToCart(String name, int quantity, double price) {
        for (int i = 0; i < quantity; i++) {
            item = new Item(name, quantity, price);
            shoppingCartItems.add(item);
        }
        this.name = name;
        this.price += (price * quantity);
    }

//    // MODIFIES: this
//    // EFFECTS: remove item from cart with the given name and quantity
//    public void removeFromCart(String name, int quantity) {
//        for (Item item: shoppingCartItems) {
//           // Item item = shoppingCartItems.get(i);
//            if (item.getItemName().equals(name)) {
//                shoppingCartItems.remove(item);
//            }
//            this.name = name;
//            this.price += (price * quantity);
//        }
//    }
    public void removeFromCart(String name, int quantity) {
        quantity = shoppingCartItems.size();
        for (int i = 0; i < getNumItem(); i++) {
            Item item = shoppingCartItems.get(i);
            if (item.getItemName().equals(name)) {
                if (quantity != 0) {
                    shoppingCartItems.remove(i);
                    quantity = quantity - i;
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

    // EFFECTS: return the name
    public String getName() {
        return name;
    }

    // EFFECTS: return the total items (quantity) of the whole shopping cart
    public int getNumItem() {
        return shoppingCartItems.size();
    }

    // EFFECTS: return the total of the whole shopping cart
    public double getPriceAltogether() {
        return price;
    }

    // MODIFIES: this
    // EFFECTS: prints the invoice for the user along with the name of the items, alongside with the price,
    // and total of the whole shopping cart made by the user
    public String printInvoice() {
        String content;

        content = "INVOICE\n" + "_____________";
        for (int i = 0; i < getNumItem(); i++) {
            content += "\nName: " + shoppingCartItems.get(i).getItemName().toUpperCase(Locale.ROOT) + "\nPrice: $"
                    + shoppingCartItems.get(i).getItemPrice() + "\n--------------";
        }
        return content;
    }

}

