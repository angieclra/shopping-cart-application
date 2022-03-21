package persistence;

import model.Item;
import model.ShoppingCart;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ShoppingCart sc = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyShoppingCart() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyShoppingCart.json");
        try {
            ShoppingCart sc = reader.read();
            assertEquals("Angie's Shopping Cart", sc.getCartName());
            assertEquals(0, sc.getNumItem());
            assertEquals(0, sc.getPriceAltogether());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralShoppingCart() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralShoppingCart.json");
        try {
            ShoppingCart sc = reader.read();
            assertEquals("Angie's Shopping Cart", sc.getCartName());
            List<Item> items = sc.getItems();
            assertEquals(2, items.size());
            assertEquals(6.05, sc.getPriceAltogether());
            checkItem("apple", 3.0, "./images/apple.jpg", items.get(0));
            checkItem("banana", 3.05, "./images/banana.png", items.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}