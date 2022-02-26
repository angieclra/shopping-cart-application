package persistence;

import model.ShoppingCart;
import model.Item;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            ShoppingCart sc = new ShoppingCart("Angie's Shopping Cart");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyShoppingCart() {
        try {
            ShoppingCart sc = new ShoppingCart("Angie's Shopping Cart");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyShoppingCart.json");
            writer.open();
            writer.write(sc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyShoppingCart.json");
            sc = reader.read();
            assertEquals("SC 1", sc.getName());
            assertEquals(0, sc.getNumItem());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralShoppingCart() {
        try {
            ShoppingCart sc = new ShoppingCart("Angie's Shopping Cart");
            sc.addToCart("apple", 3, 2.99);
            sc.addToCart("banana", 5, 3.05);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralShoppingCart.json");
            writer.open();
            writer.write(sc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralShoppingCart.json");
            sc = reader.read();
            assertEquals("Angie's Shopping Cart", sc.getName());
            List<Item> items = sc.getItems();
            assertEquals(2, items.size());
            checkItem("apple", 3, 2.99, items.get(0));
            checkItem("banana", 5, 3.05, items.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
