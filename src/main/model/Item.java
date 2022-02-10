package model;

// Represents a single item having a name, quantity, and price
public class Item {

    private String itemName; // name of the item purchased
    private int itemQuantity; // amount of quantity that will be bought by the user
    private double itemPrice; // price of the item purchased

    // EFFECTS: name of item is set to itemName
    // quantity of item(s) is set to itemQuantity
    // price of item(s) is set to itemPrice
    public Item(String itemName, int itemQuantity, double itemPrice) {
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
        this.itemPrice = itemPrice;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemQuantity(int quantity) {
        this.itemQuantity = quantity;
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

}
