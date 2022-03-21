package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {

    private ShoppingCart testShoppingCart;
    private Item apple;
    private Item banana;

    @BeforeEach
    public void setup() {
        testShoppingCart = new ShoppingCart("Angie's Shopping Cart");
        apple = new Item("Apple", 2.00, "./images/apple.png");
        banana = new Item("Banana", 4.00, "./images/banana.jpg");
    }

    @Test
    public void testConstructor() {
        assertEquals("Angie's Shopping Cart", testShoppingCart.getCartName());
        assertEquals("Apple", apple.getItemName());
        assertEquals(2.00, apple.getItemPrice());
        assertEquals("./images/apple.png", apple.getItemImage());

        assertEquals("Banana", banana.getItemName());
        assertEquals(4.00, banana.getItemPrice());
        assertEquals("./images/banana.jpg", banana.getItemImage());
    }

    @Test
    public void testAddToCart() {
        testShoppingCart.addToCart(apple);
        assertEquals(2.00, testShoppingCart.getPriceAltogether());
        assertEquals(1, testShoppingCart.getNumItem());
    }

    @Test
    public void testAddMultipleItemsToCart() {
        testShoppingCart.addToCart(apple);
        testShoppingCart.addToCart(banana);
        assertEquals(2, testShoppingCart.getNumItem());
        assertEquals(6.0, testShoppingCart.getPriceAltogether());
    }

    @Test
    public void testRemoveFromCart() {
        testShoppingCart.addToCart(apple);
        testShoppingCart.removeFromCart("Apple");
        assertEquals(0, testShoppingCart.getPriceAltogether());
    }

    @Test
    public void testRemoveFromCartWhenQuantityZero() {
        testShoppingCart.addToCart(apple);
        testShoppingCart.removeFromCart("Apple");
        assertEquals(0, testShoppingCart.getNumItem());
        assertEquals(0, testShoppingCart.getPriceAltogether());
    }

    @Test
    public void testRemoveFromCartWhenItemIsNotThere() {
        testShoppingCart.addToCart(apple);
        testShoppingCart.addToCart(banana);
        testShoppingCart.removeFromCart("Orange");
        assertEquals(2, testShoppingCart.getNumItem());
        assertEquals(6.0, testShoppingCart.getPriceAltogether());
    }

    @Test
    public void testRemoveFromCartMore() {
        testShoppingCart.addToCart(apple);
        testShoppingCart.addToCart(banana);
        assertEquals(2, testShoppingCart.getNumItem());
        testShoppingCart.removeFromCart("Apple");
        assertEquals(1, testShoppingCart.getNumItem());
        testShoppingCart.removeFromCart("Banana");
        assertEquals(0, testShoppingCart.getNumItem());

    }

    @Test
    public void testGetNumItem() {
        testShoppingCart.addToCart(apple);
        testShoppingCart.addToCart(banana);
        assertEquals(2, testShoppingCart.getNumItem());
    }

    @Test
    public void testGetPriceAltogether() {
        testShoppingCart.addToCart(apple);
        testShoppingCart.addToCart(banana);
        assertEquals(6.0, testShoppingCart.getPriceAltogether());
    }

    @Test
    public void testGetPriceAltogetherWithRemove() {
        testShoppingCart.addToCart(banana);
        testShoppingCart.addToCart(apple);
        testShoppingCart.removeFromCart("Banana");
        assertEquals(2.0, testShoppingCart.getPriceAltogether());
    }

    @Test
    public void testPrintInvoice() {
        testShoppingCart.addToCart(apple);
        assertEquals("INVOICE\n_____________\nName: APPLE\nPrice: $2.0\n--------------",
                testShoppingCart.printInvoice());
    }

}