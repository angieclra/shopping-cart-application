package ui;

import model.ShoppingCart;

import java.util.Scanner;

// Shopping Cart App
public class ShoppingCartApp {

    private ShoppingCart cart;
    private Scanner sc;

    // EFFECTS: runs the Shopping Cart application
    public ShoppingCartApp() {
        cart = new ShoppingCart();
        runApp();
    }

    // Reference: TellerApp
    // MODIFIES: this
    // EFFECTSL processes user input
    public void runApp() {
        boolean keepGoing = true;
        String command = null;

        sc = new Scanner(System.in);

        while (keepGoing) {
            displayMenu();
            command = sc.next();

            if (command.equals("6")) {
                keepGoing = false;
            } else if (command.equals("5")) {
                doFinishShopping();
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nSee you again, thank you for shopping with us!");
    }

    // Reference: TellerApp
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
        System.out.println("\t6 -> Quit app");
    }

    // MODIFIES: this
    // EFFECTS: asks for user input for the item's name, quantity, and price
    // added the item into the shopping cart
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

        cart.addToCart(name, quantity, price);
        System.out.println(quantity + " " + name + "(s) " + "successfully added to cart.");

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
            System.out.println("How many " + name + " do you want to remove?");
            quantity = sc.nextInt();
            cart.removeFromCart(name, quantity);
            System.out.println(quantity + " " + name + "(s) successfully removed from cart.");
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

}