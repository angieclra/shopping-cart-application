package ui;

import model.Item;
import model.ShoppingCart;

import java.util.Arrays;
import java.util.Scanner;

public class ShoppingCartApp {

    private ShoppingCart cart1;
    private Scanner sc;

    public ShoppingCartApp() {
        cart1 = new ShoppingCart();
        runApp();
    }

    public void runApp() {
        boolean keepGoing = true;
        String command = null;

        sc = new Scanner(System.in);

        while (keepGoing) {
            displayMenu();
            command = sc.next();

            if (command.equals("6")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nSee you again, thank you!");
    }

    private void processCommand(String command) {

        if (command.equals("1")) {
            doAdd();
        } else if (command.equals("2")) {
            doRemove();
        } else if (command.equals("3")) {
            doTotalQuantity();
        } else if (command.equals("4")) {
            doTotalCost();
        } else if (command.equals("5")) {
            doFinishShopping();
        } else {
            System.out.println("Selection not valid...");
        }
    }


    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> Add item to basket");
        System.out.println("\t2 -> Remove item from basket");
        System.out.println("\t3 -> Sum of total quantity");
        System.out.println("\t4 -> Total cost of your shopping cart");
        System.out.println("\t5 -> Finish shopping and get invoice");
        System.out.println("\t6 -> Quit app");
    }

    private void doAdd() {
        String name;
        double price;
        int quantity;
        Scanner sc = new Scanner(System.in);

        System.out.println("What is the name of your item?");
        name = sc.next();

        System.out.println("How much is the price?");
        price = sc.nextDouble();

        System.out.println("What is the quantity?");
        quantity = sc.nextInt();

        cart1.addToCart(name, quantity, price);
        System.out.println(quantity + " " + name + "(s) " + "successfully added to cart.");

    }


    private void doRemove() {
        String answer;

        System.out.println("Are you sure to remove this item from the cart?");
        answer = sc.next();

        if (answer.equals("y")) {
            System.out.println("Pick the item you want to remove: ");
        } else if (answer.equals("n")) {
            System.out.println("Please pick another option to proceed.");
            doRemove();
        } else {
            System.out.println("Selection not valid, please enter valid options.");
        }
    }

    private void doTotalQuantity() {
        System.out.println("\n" + "Total quantity of items in your shopping cart: " + cart1.getNumItem());
    }

    private void doTotalCost() {
        System.out.println("\n" + "Total cost for this purchase is: " + cart1.getPriceAltogether());
    }

    private void doFinishShopping() {

    }

}



