package ui;

import model.ShoppingCart;
import model.Item;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

// Shopping Cart App
public class ShoppingCartApp {
    private static final String JSON_STORE = "./data/shoppingCart.json";
    private ShoppingCart cart;
    private Scanner sc;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the Shopping Cart application
    public ShoppingCartApp() {
        cart = new ShoppingCart("Angie's Shopping Cart");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runApp();
    }

    // Reference: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
    // MODIFIES: this
    // EFFECTSL processes user input
    public void runApp() {
        boolean keepGoing = true;
        String command = null;

        sc = new Scanner(System.in);

        while (keepGoing) {
            displayMenu();
            command = sc.next();

            if (command.equals("8")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nSee you again, thank you for shopping with us!");
    }

    // Reference: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
    // MODIFIES: this
    // EFFECTS: processes user command
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
        } else if (command.equals("6")) {
            doSaveCart();
        } else if (command.equals("7")) {
            doLoadCart();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> Add item to basket");
        System.out.println("\t2 -> Remove item from basket");
        System.out.println("\t3 -> Sum of total quantity");
        System.out.println("\t4 -> Total cost of your shopping cart");
        System.out.println("\t5 -> Finish shopping and get invoice");
        System.out.println("\t6 -> Save current shopping cart to file");
        System.out.println("\t7 -> Load current shopping cart from file");
        System.out.println("\t8 -> Quit app");
    }

    // MODIFIES: this
    // EFFECTS: asks for user input for the item's name, quantity, and price
    // added the item into the shopping cart
    private void doAdd() {
        String name;
        double price;
        Scanner sc = new Scanner(System.in);

        System.out.println("What is the name of your item?");
        name = sc.next();

        System.out.println("How much is the price?");
        price = sc.nextDouble();

        cart.addToCart(new Item(name, price));
        System.out.println(name.toUpperCase() + " successfully added to cart.");

    }

    // MODIFIES: this
    // EFFECTS: asks for user input which item to remove,
    // remove the item picked by the user from the shopping cart
    private void doRemove() {
        String answer;
        String name;
        int quantity;

        System.out.println("Are you sure you want to remove item(s) from the cart? (y/n)");
        answer = sc.next();

        if (answer.equals("y")) {
            System.out.println("Name of item you would like to remove:");
            name = sc.next();
            cart.removeFromCart(name);
            System.out.println(name.toUpperCase() + " successfully removed from cart.");
        } else if (answer.equals("n")) {
            System.out.println("Please pick another option to proceed.");
            displayMenu();
        } else {
            System.out.println("Selection not valid, please enter valid options.");
            doRemove();
        }
    }

    // MODIFIES: this
    // EFFECTS: prints the total quantity in the user's shopping cart
    private void doTotalQuantity() {
        System.out.println("\n" + "Total quantity of items in your shopping cart: " + cart.getNumItem());
    }

    // MODIFIES: this
    // EFFECTS: prints the total cost/ price of the user's shopping cart
    private void doTotalCost() {
        System.out.println("\n" + "Total cost for this purchase is: $" + cart.getPriceAltogether());
    }

    // MODIFIES: this
    // EFFECTS: prints the invoice for the user along with the name, the price of the items,
    // and the total cart
    private void doFinishShopping() {
        System.out.println("\n" + cart.printInvoice() + "\nTotal: $" + cart.getPriceAltogether());
    }

    // EFFECTS: saves the shopping cart to file
    private void doSaveCart() {
        try {
            jsonWriter.open();
            jsonWriter.write(cart);
            jsonWriter.close();
            System.out.println("Saved " + cart.getCartName() + "to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads shopping cart from file
    private void doLoadCart() {
        try {
            cart = jsonReader.read();
            System.out.println("Loaded " + cart.getCartName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read to file: " + JSON_STORE);
        }
    }


}