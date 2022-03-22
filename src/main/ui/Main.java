package ui;

import java.io.IOException;

import model.ShoppingCart;
import model.Item;

public class Main {

    public static void main(String[] args) throws IOException {
        ShoppingCart list = new ShoppingCart("♡ Angelique's Shopping Cart ♡");
        list.addToCart(new Item("Apple", 3.25, "./images/apple.png"));
        list.addToCart(new Item("Banana", 2.39, "./images/banana.jpg"));
        list.addToCart(new Item("Grape", 5.26, "./images/grape.jpg"));
        list.addToCart(new Item("Strawberry", 3.63, "./images/strawberry.png"));
        list.addToCart(new Item("Orange", 2.47, "./images/orange.jpg"));

        ShoppingCartFrame frame = new ShoppingCartFrame(list);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

}
