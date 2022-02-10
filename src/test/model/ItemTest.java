package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {

    private Item testItem;

    @BeforeEach
    public void setup() {
        testItem = new Item("apple", 2, 1.99);
    }

    @Test
    public void testConstructor() {
        assertEquals("apple", testItem.getItemName());
        assertEquals(2, testItem.getItemQuantity());
        assertEquals(1.99, testItem.getItemPrice());
    }

}

