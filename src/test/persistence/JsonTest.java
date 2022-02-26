package persistence;

import model.Item;
import model.ShoppingCart;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkItem(String name, int quantity, double price, Item item) {
        assertEquals(name, item.getItemName());
        assertEquals(quantity, item.getItemQuantity());
        assertEquals(price, item.getItemPrice());
    }
}
