package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    public void testRemoveFromCart() {
        testShoppingCart.addToCart("Apple", 3, 1);
        testShoppingCart.removeFromCart("Apple", 2);
        assertEquals(1, testShoppingCart.getNumItem());
        assertEquals(1, testShoppingCart.getPriceAltogether());
    }

    @Test
    public void testRemoveFromCartWhenQuantityZero() {
        testShoppingCart.addToCart("Apple", 1, 1.00);
        testShoppingCart.removeFromCart("Apple",0);
        assertEquals(1, testShoppingCart.getNumItem());
        assertEquals(1.00, testShoppingCart.getPriceAltogether());
    }

    @Test
    public void testRemoveFromCartWhenItemIsNotThere() {
        testShoppingCart.addToCart("Apple", 1, 2);
        testShoppingCart.addToCart("Chocolate", 1, 1);
        testShoppingCart.removeFromCart("Orange", 1);
        assertEquals(2, testShoppingCart.getNumItem());
        assertEquals(3, testShoppingCart.getPriceAltogether());
    }

    @Test
    public void testRemoveFromCartMore() {
        testShoppingCart.addToCart("Apple", 2, 1.99);
        testShoppingCart.addToCart("Apple", 5, 2.00);
        assertEquals(7, testShoppingCart.getNumItem());
        testShoppingCart.removeFromCart("Apple", 2);
        assertEquals(5, testShoppingCart.getNumItem());
        testShoppingCart.removeFromCart("Apple", 3);
        assertEquals(2, testShoppingCart.getNumItem());
    }

    @Test
    public void testGetNumItem() {
        testShoppingCart.addToCart("Apple", 3, 2.00);
        testShoppingCart.addToCart("Orange", 4, 5.00);
        assertEquals(7, testShoppingCart.getNumItem());
    }

    @Test
    public void testGetPriceAltogether() {
        testShoppingCart.addToCart("Apple", 3, 2.00);
        testShoppingCart.addToCart("Orange", 4, 5.00);
        assertEquals(26.00, testShoppingCart.getPriceAltogether());
    }

    @Test
    public void testGetPriceAltogetherWithRemove() {
        testShoppingCart.addToCart("Banana", 5, 4.00);
        testShoppingCart.addToCart("Banana", 6, 5.00);
        testShoppingCart.removeFromCart("Banana", 2);
        assertEquals(42.00, testShoppingCart.getPriceAltogether());
    }

    @Test
    public void testGetPriceAltogetherWithRemove2() {
        testShoppingCart.addToCart("Apple", 2, 1.99);
        testShoppingCart.addToCart("Apple", 5, 2.00);
        assertEquals(7, testShoppingCart.getNumItem());
        testShoppingCart.removeFromCart("Apple", 2);
        assertEquals(5, testShoppingCart.getNumItem());
        testShoppingCart.removeFromCart("Apple", 3);
        assertEquals(2, testShoppingCart.getNumItem());
        assertEquals(4.00, testShoppingCart.getPriceAltogether());
    }

    @Test
    public void testPrintInvoice() {
        testShoppingCart.addToCart("Apple", 1, 3.99);
        assertEquals("INVOICE\n_____________\nName: APPLE\nPrice: $3.99\n--------------",
                testShoppingCart.printInvoice());
    }

}