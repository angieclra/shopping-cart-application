package model;

public class Item {

    private String itemName;
    private int itemQuantity;
    private double itemPrice;

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
//
//    public String toString() { // do i need this??????
//        return itemName + " price: " + itemPrice + " qty: " + itemQuantity;
//    }

}
