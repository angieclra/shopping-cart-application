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

    @Test
    public void testSetItemName() {
        testItem.setItemName("apple");
        assertEquals("apple", testItem.getItemName());
    }

    @Test
    public void testSetItemQuantity() {
        testItem.setItemQuantity(3);
        assertEquals(3, testItem.getItemQuantity());
    }

    @Test
    public void testSetPrice() {
        testItem.setPrice(1.9);
        assertEquals(1.9, testItem.getItemPrice());
    }

}

