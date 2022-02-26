package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

// Represents a shopping cart with the items purchased in it
public class ShoppingCart implements Writable {
    private ArrayList<Item> shoppingCartItems; // array list consisting of the items (objects)
    private double price; // add fields to represent changing properties of a shopping cart
    private String name;
    private int quantity;
    private Item item;

    // EFFECTS: constructs shopping cart
    public ShoppingCart(String name) {
        this.name = name;
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

    // MODIFIES: this
    // EFFECTS: remove item from cart with the given name and quantity
    public void removeFromCart(String name) {
        int quantity = shoppingCartItems.size();
        for (int i = 0; i < getNumItem(); i++) {
            Item item = shoppingCartItems.get(i);
            if (item.getItemName().equals(name)) {
                if (quantity != 0) {
                    shoppingCartItems.remove(i);
                    quantity = quantity - i;
                    price = getPriceAltogether() - item.getItemPrice();
                } else {
                    break;
                }
            }
        }
    }

    public List<Item> getItems() {
        return Collections.unmodifiableList(shoppingCartItems);
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("items", itemsToJson());
        return json;
    }

    // EFFECTS: return things in this shopping cart as a JSON array
    private JSONArray itemsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Item i: shoppingCartItems) {
            jsonArray.put(i.toJson());
        }

        return jsonArray;
    }
}

