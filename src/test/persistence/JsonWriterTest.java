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
            assertEquals("Angie's Shopping Cart", sc.getCartName());
            assertEquals(0, sc.getNumItem());
            assertEquals(0, sc.getPriceAltogether());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralShoppingCart() {
        try {
            ShoppingCart sc = new ShoppingCart("Angie's Shopping Cart");
            sc.addToCart(new Item("apple", 3.00));
            sc.addToCart(new Item("banana", 3.05));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralShoppingCart.json");
            writer.open();
            writer.write(sc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralShoppingCart.json");
            sc = reader.read();
            assertEquals("Angie's Shopping Cart", sc.getCartName());
            List<Item> items = sc.getItems();
            assertEquals(2, items.size());
            assertEquals(6.05, sc.getPriceAltogether());
            checkItem("apple", 3.00, items.get(0));
            checkItem("banana", 3.05, items.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
