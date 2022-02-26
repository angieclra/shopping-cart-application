package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a single item having a name, quantity, and price
public class Item implements Writable {

    private String itemName; // name of the item purchased
    private int itemQuantity; // amount of quantity that will be bought by the user
    private double itemPrice; // price of the item purchased

    // EFFECTS: name of item is set to itemName
    // quantity of item(s) is set to itemQuantity
    // price of item(s) is set to itemPrice
    public Item(String itemName, double itemPrice) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setPrice(double price) {
        this.itemPrice = price;
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", itemName);
        json.put("price", itemPrice);
        return json;

    }

}
