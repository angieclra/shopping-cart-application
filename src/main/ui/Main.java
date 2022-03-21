package ui;

import java.io.IOException;

import model.ShoppingCart;
import model.Item;

public class Main {

    public static void main(String[] args) throws IOException {
        ShoppingCart list = new ShoppingCart("Angie's Shopping Cart");
        list.addToCart(new Item("Apple", 3.45, "./images/apple.png"));
        list.addToCart(new Item("Banana", 2.99, "./images/banana.jpg"));
        list.addToCart(new Item("Grape", 5.25, "./images/grape.jpg"));
        list.addToCart(new Item("Strawberry", 3.85, "./images/strawberry.png"));
        list.addToCart(new Item("Orange", 2.49, "./images/orange.jpg"));

        ShoppingCartFrame frame = new ShoppingCartFrame(list);
        frame.setVisible(true);
    }

}
