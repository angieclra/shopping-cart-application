package model;

public class ItemOrder {
    private Item item;
    private double price;

    public ItemOrder(Item item, double price) {
        this.item = item;
        this.price = price;
    }

    public double getPrice() {
        return item.getItemPrice();
    }
}
