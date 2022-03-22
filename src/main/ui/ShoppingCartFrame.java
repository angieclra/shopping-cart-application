package ui;

import model.Item;
import model.ShoppingCart;
import persistence.JsonWriter;
import persistence.JsonReader;

import java.io.FileNotFoundException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

// Shopping Cart GUI
public class ShoppingCartFrame extends JFrame implements ActionListener {
    static JFrame frame;
    private ShoppingCart items;
    private JTextField total;
    private JTextField totalItems;

    private JPanel panel;
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
        setTitle(products.getCartName());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        items = new ShoppingCart("Angelique's Shopping Cart");

        total = new JTextField("$0.00",5);
        total.setEditable(false);
        total.setEnabled(false);
        total.setDisabledTextColor(Color.BLACK);

        totalItems = new JTextField("0", 2);
        totalItems.setEditable(false);
        totalItems.setEnabled(false);
        totalItems.setDisabledTextColor(Color.BLACK);

        firstPanel(products);

        saveLoadButtons();
        shoppingCartInvoice();
        finishShopping();
    }


    // MODIFIES: this
    // EFFECTS: total price and total quantity labels are made ***
    public void firstPanel(ShoppingCart products) throws IOException {
        panel = new JPanel();
        panel.setBackground(new Color(255,136,134));
        panel.setBorder(new TitledBorder("Summary "));

        TitledBorder titledBorder = (TitledBorder)panel.getBorder();
        // titledBorder.setTitleColor(new Color(92,157,153));
        titledBorder.setTitleFont(new Font("Monospaced", Font.ITALIC, 15));
        titledBorder.setTitleColor(Color.WHITE);

        addLabels();

        panel.add(label);
        panel.add(total);
        panel.add(label1);
        panel.add(totalItems);
        add(panel, BorderLayout.NORTH);

        panel = new JPanel(new GridLayout(products.getNumItem(),1));
        for (int i = 0; i < products.getNumItem(); i++) {
            addItem(products.getItems().get(i), panel);
        }

        add(panel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        pack();
    }

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

        label = new JLabel("" + product.getItemName().toUpperCase(Locale.ROOT));
        label.setFont(new Font("Helvetica", Font.BOLD, 20));
        label.setForeground(Color.WHITE);
        centerPanel.add(label);
        panel.add(centerPanel);

        label = new JLabel("\tPRICE: $ " + product.getItemPrice());
        label.setFont(new Font("Monospaced", Font.PLAIN, 15));
        label.setForeground(Color.WHITE);
        centerPanel.add(label);
        panel.add(centerPanel);

        fruitImage = ImageIO.read(new File(product.getItemImage()));
        fruitImageResized = new BufferedImage(50, 50, fruitImage.getType());
        Graphics2D g2d = fruitImageResized.createGraphics();
        g2d.drawImage(fruitImage, 0, 0, 50, 50, null);
        g2d.dispose();

        JLabel label = new JLabel(new ImageIcon(fruitImageResized));
        centerPanel.add(label, FlowLayout.LEFT);
        panel.add(centerPanel);

        buttons(product);
    }


    private void initializeCenterPanel() {
        centerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        centerPanel.setBorder(new TitledBorder(new EtchedBorder(), "ON SALE "));
        TitledBorder titledBorder = (TitledBorder)centerPanel.getBorder();
        titledBorder.setTitleFont(new Font("Helvetica", Font.ITALIC, 12));
        titledBorder.setTitleColor(Color.WHITE);

        centerPanel.setBackground(new Color(255,204,203));
    }


    // MODIFIES: this
    // EFFECTS: add both the add button and remove button to the panel
    public void buttons(final Item product) {
        addButton(product);
        removeButton(product);

        centerPanel.add(addItemButton);
        centerPanel.add(removeItemButton);
        panel.add(centerPanel);
        setVisible(true);
    }


    // MODIFIES: this
    // EFFECTS: remove button is made, when button is clicked, item decrease by one quantity,
    // total price and total quantity are updated
    private void removeButton(Item product) {
        removeItemButton = new JButton("Remove Item");
        removeItemButton.setBounds(100, 100, 100, 4);
        removeItemButton.setForeground(Color.BLACK);
        removeItemButton.setFont(new Font("Monospaced", Font.PLAIN, 12));

        // EFFECTS: remove item to shopping cart when button clicked
        removeItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                items.removeFromCart(product.getItemName());
                updateTotal();
                updateTotalQuantity();
            }
        });
    }


    // MODIFIES: this
    // EFFECTS: add button is made, when button is clicked  item increase by one quantity
    // total price and total quantity are updated
    private void addButton(Item product) {
        addItemButton = new JButton("Add Item");
        addItemButton.setBounds(100, 100, 100, 4);
        addItemButton.setForeground(Color.BLACK);
        addItemButton.setFont(new Font("Monospaced", Font.PLAIN, 12));

        // EFFECTS: add item to shopping cart when button clicked
        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                items.addToCart(product);
                updateTotal();
                updateTotalQuantity();
            }
        });
    }


    // EFFECTS: updates the text field of total everytime a user
    // adds or remove an item off the shopping cart
    public void updateTotal() {
        double amount = items.getPriceAltogether();
        total.setText(NumberFormat.getCurrencyInstance().format(amount));
    }


    // EFFECTS: updates the text field of totalItems everytime a user
    // adds or remove an item off the shopping cart
    public void updateTotalQuantity() {
        int numberItems = items.getNumItem();
        totalItems.setText(NumberFormat.getIntegerInstance().format(numberItems));
    }


    // EFFECTS: implementing ActionListener
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO auto-generated method stub
    }


    // EFFECTS: save and load buttons are made
    public void saveLoadButtons() {
        saveButton = new JButton("Save Shopping Cart");
        saveButton.setBounds(100, 100, 100, 4);
        saveButton.setFont(new Font("Monospaced", Font.PLAIN, 12));

        loadButton = new JButton("Load Shopping Cart");
        loadButton.setBounds(100, 100, 100, 4);
        loadButton.setFont(new Font("Monospaced", Font.PLAIN, 12));

        writeAndReadData();

        bottomPanel = new JPanel();

        bottomPanel.add(saveButton);
        bottomPanel.add(loadButton);
        bottomPanel.setBackground(new Color(255,168,181));

        add(bottomPanel, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }


    // EFFECTS: display list of items that the user bought on the right side of the panel
    public void shoppingCartInvoice() {
        rightPanel = new JPanel();
        rightPanel.setBorder(new TitledBorder(new EtchedBorder(), "Invoice "));
        rightPanel.setBackground(new Color(255,233,224));
        TitledBorder titledBorder = (TitledBorder)rightPanel.getBorder();
        titledBorder.setTitleFont(new Font("Monospaced", Font.ITALIC, 15));

        invoicePane = new JTextArea(30, 20);
        invoicePane.setEditable(false);
        invoicePane.setEnabled(false);
        invoicePane.setDisabledTextColor(Color.BLACK);

        invoicePane.setBackground(Color.WHITE);

        viewInvoice();

        rightPanel.add(scrollPane);
        add(rightPanel, BorderLayout.EAST);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }


    // EFFECTS: view invoice for shopping cart, updates everytime a user adds or remove a new item
    private void viewInvoice() {
        viewInvoiceButton = new JButton("View Invoice");
        viewInvoiceButton.setBounds(100, 100, 100, 4);
        viewInvoiceButton.setFont(new Font("Monospaced", Font.PLAIN, 12));
        bottomPanel.add(viewInvoiceButton);

        scrollPane = new JScrollPane(invoicePane);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        // print invoice with updated number of items and total price
        viewInvoiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String invoiceItems = "\t" + items.printInvoice();
                invoiceItems += "\n Total Items: " + items.getNumItem()
                        + "\n Total Price: $" + items.getPriceAltogether() + "\n"
                        + "\n Thank you for shopping with us!";
                invoicePane.setText(invoiceItems);
            }
        });
        pack();
    }

    // EFFECTS: button for user to finish shopping,
    // shows image of thank you message when button is clicked (visual component)
    public void finishShopping() {
        finishShoppingButton = new JButton("Finish Shopping");
        finishShoppingButton.setBounds(100, 100, 100, 4);
        finishShoppingButton.setFont(new Font("Monospaced", Font.PLAIN, 12));
        bottomPanel.add(finishShoppingButton);

        // EFFECTS: show new frame with thank you image when button clicked
        finishShoppingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame = new JFrame("Thank you! See you again ♡");
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
    // along with the information in it to JSON file
    private void saveData() {
        // EFFECTS: save to JSON file when button clicked
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JsonWriter writer = new JsonWriter("./data/shoppingCart.json");
                try {
                    writer.open();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                writer.write(items);
                writer.close();
            }
        });
    }


    // EFFECTS: load JSON data into GUI, updates the total price and total quantity of the shopping
    // cart when button is pressed. user is allowed to retrieve data from existing JSON file
    private void loadData() {
        // EFFECTS: load current JSON file when button clicked
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JsonReader reader = new JsonReader("./data/shoppingCart.json");
                try {
                    items = reader.read();
                    updateTotalQuantity();
                    updateTotal();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}