package ui;

import model.Item;
import model.ShoppingCart;
import persistence.JsonWriter;
import persistence.JsonReader;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

// Shopping Cart Frame
public class ShoppingCartFrame extends JFrame implements ActionListener {
    private static JFrame frame;

    private double amount;
    private int numberItems;

    private ShoppingCart items;
    private JTextField totalPrice;
    private JTextField totalItems;

    private JPanel topPanel;
    private JPanel centerPanel;
    private JPanel bottomPanel;
    private JPanel rightPanel;

    private JLabel label;
    private JLabel label1;

    private JTextArea invoicePane;

    private JButton addItemButton;
    private JButton removeItemButton;
    private JButton viewInvoiceButton;
    private JButton finishShoppingButton;
    private JButton loadButton;
    private JButton saveButton;

    private BufferedImage fruitImage;
    private BufferedImage fruitImageResized;

    private JScrollPane scrollPane;

    // MODIFIES: this
    // EFFECTS: sets the frame of the application
    public ShoppingCartFrame(ShoppingCart products) throws IOException {
        initializeTopPanel(products);
        initializeBottomPanel();
        initializeInvoicePanel();
        initializeFinishShopping();
    }


    // EFFECTS: set the text fields for total price and total quantity of the shopping cart
    private void setTotalField() {
        totalPrice = new JTextField("$0.00",5);
        totalPrice.setEditable(false);
        totalPrice.setEnabled(false);
        totalPrice.setDisabledTextColor(Color.BLACK);

        totalItems = new JTextField("0", 2);
        totalItems.setEditable(false);
        totalItems.setEnabled(false);
        totalItems.setDisabledTextColor(Color.BLACK);
    }


    // MODIFIES: this
    // EFFECTS: set the title of the frame
    private void titleFrame(ShoppingCart products) {
        setTitle(products.getCartName());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    // MODIFIES: this
    // EFFECTS: initialize top panel of the frame, with the labels for total price and total quantity
    // of the shopping cart and text fields for displaying the total price and total quantity
    public void initializeTopPanel(ShoppingCart products) throws IOException {
        // initialized new panel
        titleFrame(products);
        items = new ShoppingCart("♡ Angelique's Shopping Cart ♡");
        setTotalField();

        topPanel = new JPanel();
        topPanel.setBackground(new Color(255,136,134));
        topPanel.setBorder(new TitledBorder("Summary "));
        TitledBorder titledBorder = (TitledBorder) topPanel.getBorder();
        titledBorder.setTitleFont(new Font("Monospaced", Font.ITALIC, 15));
        titledBorder.setTitleColor(Color.WHITE);

        addLabels();

        // add all the labels and text fields to top panel
        topPanel.add(label);
        topPanel.add(totalPrice);
        topPanel.add(label1);
        topPanel.add(totalItems);
        add(topPanel, BorderLayout.NORTH);

        // for loop for displaying every item in the shopping cart on the top panel
        topPanel = new JPanel(new GridLayout(products.getNumItem(),1));
        for (int i = 0; i < products.getNumItem(); i++) {
            addItem(products.getItems().get(i), topPanel);
        }

        add(topPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        pack();
    }


    // EFFECTS: add labels for total price and quantity
    private void addLabels() {
        label = new JLabel("Total Price:");
        label.setFont(new Font("Helvetica", Font.BOLD, 25));
        label.setForeground(Color.WHITE);

        label1 = new JLabel("Total Quantity:");
        label1.setFont(new Font("Helvetica", Font.BOLD, 25));
        label1.setForeground(Color.WHITE);
    }


    // MODIFIES: this
    // EFFECTS: displays each of the item name, price, and image that are available on the shopping cart app
    public void addItem(final Item product, JPanel panel) throws IOException {
        initializeCenterPanel();
        addLabelForEachFruit(product);
        addImageForEachFruit(product);
        panel.add(centerPanel);
        addAddAndRemoveButtons(product);
    }

    // MODIFIES: this
    // EFFECTS: adds image beside name of fruit for each fruit item
    private void addImageForEachFruit(Item product) throws IOException {
        fruitImage = ImageIO.read(new File(product.getItemImage()));
        fruitImageResized = new BufferedImage(50, 50, fruitImage.getType());
        Graphics2D g2d = fruitImageResized.createGraphics();
        g2d.drawImage(fruitImage, 0, 0, 50, 50, null);
        g2d.dispose();
        JLabel label = new JLabel(new ImageIcon(fruitImageResized));
        centerPanel.add(label, FlowLayout.LEFT);
    }

    // MODIFIES: this
    // EFFECTS: adds label for each fruit item
    private void addLabelForEachFruit(Item product) {
        label = new JLabel("" + product.getItemName().toUpperCase(Locale.ROOT));
        label.setFont(new Font("Helvetica", Font.BOLD, 20));
        label.setForeground(Color.WHITE);
        centerPanel.add(label);

        label = new JLabel("\t- Price: $ " + product.getItemPrice());
        label.setFont(new Font("Sans Serif", Font.PLAIN, 15));
        label.setForeground(Color.WHITE);
        centerPanel.add(label);
    }


    // EFFECTS: initialized the center panel
    private void initializeCenterPanel() {
        centerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        centerPanel.setBorder(new TitledBorder(new EtchedBorder(), "ON SALE "));
        TitledBorder titledBorder = (TitledBorder)centerPanel.getBorder();
        titledBorder.setTitleFont(new Font("Helvetica", Font.ITALIC, 12));
        titledBorder.setTitleColor(Color.WHITE);

        centerPanel.setBackground(new Color(255,204,203));
    }


    // MODIFIES: this
    // EFFECTS: adds both the add button and remove button to the panel
    public void addAddAndRemoveButtons(final Item product) {
        addButton(product);
        removeButton(product);

        centerPanel.add(addItemButton);
        centerPanel.add(removeItemButton);

        topPanel.add(centerPanel);

        setVisible(true);
    }


    // MODIFIES: this
    // EFFECTS: remove button is made, when button is clicked, items quantity decrease by one,
    // total price and total quantity are updated
    private void removeButton(Item product) {
        removeItemButton = new JButton("Remove Item");
        removeItemButton.setBounds(100, 100, 100, 4);
        removeItemButton.setForeground(Color.BLACK);
        removeItemButton.setFont(new Font("Monospaced", Font.PLAIN, 12));

        // MODIFIES: this
        // EFFECTS: remove item to shopping cart when button clicked
        removeItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                items.removeFromCart(product.getItemName());
                updateTotalPrice();
                updateTotalQuantity();
            }
        });
    }


    // MODIFIES: this
    // EFFECTS: add button is made, when button is clicked, items quantity increase by one,
    // total price and total quantity are updated
    private void addButton(Item product) {
        addItemButton = new JButton("Add Item");
        addItemButton.setBounds(100, 100, 100, 4);
        addItemButton.setForeground(Color.BLACK);
        addItemButton.setFont(new Font("Monospaced", Font.PLAIN, 12));

        // MODIFIES: this
        // EFFECTS: add item to shopping cart when button clicked
        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                items.addToCart(product);
                updateTotalPrice();
                updateTotalQuantity();
            }
        });
    }


    // EFFECTS: updates the text field of total everytime a user
    // adds or remove an item off the shopping cart
    public void updateTotalPrice() {
        amount = items.getPriceAltogether();
        NumberFormat formatter = new DecimalFormat("#0.00");
        formatter.format(amount);
        totalPrice.setText(NumberFormat.getCurrencyInstance().format(amount));
    }


    // EFFECTS: updates the text field of totalItems everytime a user
    // adds or remove an item off the shopping cart
    public void updateTotalQuantity() {
        numberItems = items.getNumItem();
        totalItems.setText(NumberFormat.getIntegerInstance().format(numberItems));
    }


    // EFFECTS: initialize bottom panel along with add and remove buttons on the panel
    public void initializeBottomPanel() {
        saveAndLoadButton();
        writeAndReadData();

        bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(255,168,181));
        bottomPanel.add(saveButton);
        bottomPanel.add(loadButton);
        add(bottomPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }


    // EFFECTS: save and load buttons are made
    private void saveAndLoadButton() {
        // save button
        saveButton = new JButton("Save Shopping Cart");
        saveButton.setBounds(100, 100, 100, 4);
        saveButton.setFont(new Font("Monospaced", Font.PLAIN, 12));

        // load button
        loadButton = new JButton("Load Shopping Cart");
        loadButton.setBounds(100, 100, 100, 4);
        loadButton.setFont(new Font("Monospaced", Font.PLAIN, 12));
    }


    // EFFECTS: display list of items that the user bought on the right side of the panel
    public void initializeInvoicePanel() {
        rightPanel = new JPanel();
        rightPanel.setBorder(new TitledBorder(new EtchedBorder(), "Invoice "));
        rightPanel.setBackground(new Color(255,233,224));
        TitledBorder titledBorder = (TitledBorder)rightPanel.getBorder();
        titledBorder.setTitleFont(new Font("Monospaced", Font.ITALIC, 15));

        initializeInvoicePane();
        viewInvoice();

        add(rightPanel, BorderLayout.EAST);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }


    // EFFECTS: initialize invoice pane with scroll pane, user can scroll if items in large quantity
    private void initializeInvoicePane() {
        invoicePane = new JTextArea(30, 20);
        invoicePane.setEditable(false);
        invoicePane.setEnabled(false);
        invoicePane.setDisabledTextColor(Color.BLACK);
        invoicePane.setBackground(Color.WHITE);

        scrollPane = new JScrollPane(invoicePane);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        rightPanel.add(scrollPane);
    }


    // EFFECTS: view invoice for shopping cart, updates everytime a user adds or remove a new item
    private void viewInvoice() {
        viewInvoiceButton = new JButton("View Invoice");
        viewInvoiceButton.setBounds(100, 100, 100, 4);
        viewInvoiceButton.setFont(new Font("Monospaced", Font.PLAIN, 12));
        bottomPanel.add(viewInvoiceButton);

        // print invoice with updated number of items and total price
        viewInvoiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String invoiceItems = "\t" + items.printInvoice();
                invoiceItems += "\n Total Items: " + totalItems.getText()
                        + "\n Total Price: " + totalPrice.getText() + "\n"
                        + "\n Thank you for shopping with us!";
                invoicePane.setText(invoiceItems);
            }
        });
        pack();
    }


    // EFFECTS: button for user to finish shopping,
    // shows image of thank you message when button is clicked
    public void initializeFinishShopping() {
        finishShoppingButton = new JButton("Finish Shopping");
        finishShoppingButton.setBounds(100, 100, 100, 4);
        finishShoppingButton.setFont(new Font("Monospaced", Font.PLAIN, 12));
        bottomPanel.add(finishShoppingButton);

        // MODIFIES: this
        // EFFECTS: show new frame with thank you image when button clicked
        finishShoppingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame = new JFrame("Thank you! See you again  ︎◡̈");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().setBackground(Color.WHITE);
                frame.setLayout(new FlowLayout());

                ImageIcon icon = new ImageIcon("./images/thank you.jpg");
                JLabel label = new JLabel(icon);
                frame.add(label);
                frame.pack();
                frame.setSize(500,500);
                dispose();
                frame.setVisible(true);
            }
        });
        pack();
    }


    // EFFECTS: load and write the data to JSON file
    public void writeAndReadData() {
        loadData();
        saveData();
    }


    // EFFECTS: when button is pressed it saves current state of shopping cart,
    // along with the information in it to JSON data
    private void saveData() {
        // MODIFIES: this
        // EFFECTS: save to JSON file when button clicked
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JsonWriter writer = new JsonWriter("./data/shoppingCart.json");
                try {
                    writer.open();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                writer.write(items);
                writer.close();
            }
        });
    }


    // EFFECTS: load JSON data, updates the total price and total quantity of the shopping
    // cart when button is pressed. user is allowed to retrieve data from existing JSON file
    private void loadData() {
        // MODIFIES: this
        // EFFECTS: load current JSON file when button clicked
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JsonReader reader = new JsonReader("./data/shoppingCart.json");
                try {
                    items = reader.read();
                    updateTotalQuantity();
                    updateTotalPrice();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }


    // MODIFIES: this
    // EFFECTS: implementing ActionListener
    @Override
    public void actionPerformed(ActionEvent e) {
    }

}