package model;

import org.json.JSONObject;
import persistence.Writable;

import java.text.DecimalFormat;
import java.text.NumberFormat;

// Represents a single item having a name, quantity, and price
public class Item implements Writable {

    private String itemName; // name of the item purchased
    private double itemPrice; // price of the item purchased
    private String itemImage;

    // EFFECTS: name of item is set to itemName
    // quantity of item(s) is set to itemQuantity
    // price of item(s) is set to itemPrice
    public Item(String itemName, double itemPrice, String itemImage) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemImage = itemImage;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public String getItemName() {
        return itemName;
    }

    public double getItemPrice() {
        NumberFormat formatter = new DecimalFormat("#0.00");
        formatter.format(itemPrice);
        return itemPrice;
    }

    public String getItemImage() {
        return itemImage;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", itemName);
        json.put("price", itemPrice);
        json.put("image", itemImage);
        return json;

    }

}
