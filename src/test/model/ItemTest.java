package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {

    private Item testItem;

    @BeforeEach
    public void setup() {
        testItem = new Item("apple", 1.99, "./images/apple.png");
    }

    @Test
    public void testConstructor() {
        assertEquals("apple", testItem.getItemName());
        assertEquals(1.99, testItem.getItemPrice());
        assertEquals("./images/apple.png", testItem.getItemImage());
    }

    @Test
    public void testSetItemName() {
        testItem.setItemName("apple");
        assertEquals("apple", testItem.getItemName());
    }

    @Test
    public void testSetPrice() {
        testItem.setPrice(1.9);
        assertEquals(1.9, testItem.getItemPrice());
    }

    @Test
    public void testSetImage() {
        testItem.setImage("./images/apple.png");
        assertEquals("./images/apple.png", testItem.getItemImage());
    }

}

