package persistence;

import model.Item;
import model.ShoppingCart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
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
            assertEquals("Angie's Shopping Cart", sc.getName());
            assertEquals(0, sc.getNumItem());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralShoppingCart() {
        JsonReader reader = new JsonReader(".data/testReaderGeneralWorkroom.json");
        try {
            ShoppingCart sc = reader.read();
            assertEquals("Angie's Shopping Cart", sc.getName());
            List<Item> items = sc.getItems();
            assertEquals(2, items.size());
            checkItem("apple", 3, 2.99, items.get(0));
            checkItem("banana", 5, 3.05, items.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}