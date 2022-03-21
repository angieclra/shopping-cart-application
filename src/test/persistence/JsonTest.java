package persistence;

import model.Item;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkItem(String name, double price, String image, Item item) {
        assertEquals(name, item.getItemName());
        assertEquals(price, item.getItemPrice());
        assertEquals(image, item.getItemImage());
    }
}
