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
    private String cartName;

    // EFFECTS: constructs shopping cart
    public ShoppingCart(String cartName) {
        this.cartName = cartName;
        shoppingCartItems = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: add item to the shopping cart
    public void addToCart(Item item) {
        shoppingCartItems.add(item);
        price += item.getItemPrice();
    }

    // MODIFIES: this
    // EFFECTS: remove item from cart with the given name
    public void removeFromCart(String name) {
        for (int i = 0; i < shoppingCartItems.size(); i++) {
            Item item = shoppingCartItems.get(i);
            if (item.getItemName().equals(name)) {
                shoppingCartItems.remove(item);
                price = getPriceAltogether() - item.getItemPrice();
            }
        }
    }


    public List<Item> getItems() {
        return Collections.unmodifiableList(shoppingCartItems);
    }


    // EFFECTS: return the total items of the whole shopping cart
    public int getNumItem() {
        return shoppingCartItems.size();
    }

    // EFFECTS: return the total of the whole shopping cart
    public double getPriceAltogether() {
        return price;
    }

    public String getCartName() {
        return cartName;
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
        json.put("cartName", cartName);
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

