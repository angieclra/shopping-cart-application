package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {

    private ShoppingCart testShoppingCart;

    @BeforeEach
    public void setup() {
        testShoppingCart = new ShoppingCart();
    }

    @Test
    public void testAddToCart() {
        testShoppingCart.addToCart("Apple", 2, 3.99);
        assertEquals("Apple", testShoppingCart.getName());
        assertEquals(7.98, testShoppingCart.getPriceAltogether());
        assertEquals(2, testShoppingCart.getNumItem());
    }

    @Test
    public void testAddMultipleItemsToCart() {
        testShoppingCart.addToCart("Apple", 2, 3.99);
        testShoppingCart.addToCart("Orange", 3, 2.00);
        assertEquals("Orange", testShoppingCart.getName());
        assertEquals(5, testShoppingCart.getNumItem());
        assertEquals(13.98, testShoppingCart.getPriceAltogether());
    }

    @Test
    public void testDeleteFromCart() {
        testShoppingCart.addToCart("Apple", 3, 1.99);
        testShoppingCart.deleteFromCart("Apple", 2);
        assertEquals(1, testShoppingCart.getNumItem());
    }

    @Test
    public void testDeleteFromCartMore() {
        testShoppingCart.addToCart("Apple", 2, 1.99);
        testShoppingCart.addToCart("Apple", 5, 2.00);
        assertEquals(7, testShoppingCart.getNumItem());
        testShoppingCart.deleteFromCart("Apple", 2);
        assertEquals(5, testShoppingCart.getNumItem());
        testShoppingCart.deleteFromCart("Apple", 3);
        assertEquals(2, testShoppingCart.getNumItem());
    }

}