package ui;

import model.Item;
import model.ShoppingCart;
import persistence.JsonWriter;
import persistence.JsonReader;

import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;


public class ShoppingFrame extends JFrame implements ActionListener {
    static JFrame frame;

    private ShoppingCart items;
    private JTextField total;
    private JPanel panel;
    private JLabel label;
    private JLabel label1;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panelImage;
    private JTextField quantity;
    private JTextField totalItems;
    private Item product;
    private double priceInputted;
    private JPanel container;
    private JButton addItemButton;
    private JButton removeItemButton;
    private JButton viewShoppingCartButton;
    private JButton finishShoppingButton;
    private JButton loadButton;
    private JButton saveButton;

    private BufferedImage fruitImage;
    private BufferedImage fruitImageResized;

    private BufferedImage addItemImage;
    private BufferedImage addItemImageResized;

    private JInternalFrame controlPanel;
    private JDesktopPane desktop;

    private JList itemList;

    public ShoppingFrame(ShoppingCart products) throws IOException {
        setTitle(products.getCartName());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        items = new ShoppingCart("Angie's Shopping Cart");

        total = new JTextField("$0.00",5);
        total.setEditable(false);
        total.setEnabled(false);
        total.setDisabledTextColor(Color.BLACK);

        totalItems = new JTextField("0", 3);
        totalItems.setEditable(false);
        totalItems.setEnabled(false);
        totalItems.setDisabledTextColor(Color.BLACK);

        firstPanel(products);

        saveLoadButtons();
        savedShoppingCartTable();
        finishShopping();
    }

    private void firstPanel(ShoppingCart products) throws IOException {
        panel = new JPanel();
        panel.setBackground(new Color(92,157,153));

        label = new JLabel("Total Price:");
        label.setFont(new Font("Helvetica", Font.ITALIC, 25));
        label.setForeground(Color.WHITE);

        label1 = new JLabel("Total Quantity:");
        label1.setFont(new Font("Helvetica", Font.ITALIC, 25));
        label1.setForeground(Color.WHITE);

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
        pack();
    }

    public void addItem(final Item product, JPanel panel) throws IOException {
        panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel1.setBackground(new Color(183, 218, 212));

        label = new JLabel("" + product.getItemName());
        label.setFont(new Font("Helvetica", Font.BOLD, 20));
        label.setForeground(Color.WHITE);
        panel1.add(label);
        panel.add(panel1);

        label = new JLabel("- Price: $" + product.getItemPrice());
        label.setFont(new Font("Helvetica", Font.PLAIN, 15));
        label.setForeground(Color.WHITE);
        panel1.add(label);
        panel.add(panel1);

        fruitImage = ImageIO.read(new File(product.getItemImage()));
        fruitImageResized = new BufferedImage(50, 50, fruitImage.getType());
        Graphics2D g2d = fruitImageResized.createGraphics();
        g2d.drawImage(fruitImage, 0, 0, 50, 50, null);
        g2d.dispose();

        JLabel label = new JLabel(new ImageIcon(fruitImageResized));
        panel1.add(label, FlowLayout.LEFT);
        panel.add(panel1);

        buttons(product);
    }

    public void buttons(final Item product) {
        addButton(product);
        removeButton(product);

        panel1.add(addItemButton);
        panel1.add(removeItemButton);
        panel.add(panel1);
        setVisible(true);
    }

    private void removeButton(Item product) {
        removeItemButton = new JButton("Remove Item");
        removeItemButton.setBounds(100, 100, 100, 4);
        removeItemButton.setForeground(Color.BLACK);

        removeItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                items.removeFromCart(product.getItemName());
                updateTotal();
                updateTotalQuantity();
            }
        });
    }

    private void addButton(Item product) {
        addItemButton = new JButton("Add Item");
        addItemButton.setBounds(100, 100, 100, 4);
        addItemButton.setForeground(Color.BLACK);

        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                items.addToCart(product);
                updateTotal();
                updateTotalQuantity();
            }
        });
    }

    public void updateTotal() {
        double amount = items.getPriceAltogether();
        total.setText(NumberFormat.getCurrencyInstance().format(amount));
    }

    public void updateTotalQuantity() {
        int numberItems = items.getNumItem();
        totalItems.setText(NumberFormat.getIntegerInstance().format(numberItems));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO auto-generated method stub
    }

    public void saveLoadButtons() {
        saveButton = new JButton("Save Shopping Cart");
        saveButton.setBackground(new Color(183, 218, 212));
        saveButton.setBounds(100, 100, 100, 4);
        saveButton.setFont(new Font("Monospaced", Font.PLAIN, 12));

        loadButton = new JButton("Load Shopping Cart");
        loadButton.setBackground(new Color(183, 218, 212));
        loadButton.setBounds(100, 100, 100, 4);
        loadButton.setFont(new Font("Monospaced", Font.PLAIN, 12));

        writeAndReadData();

        panel2 = new JPanel();

        panel2.add(saveButton);
        panel2.add(loadButton);
        panel2.setBackground(new Color(92,157,153));

        add(panel2, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public void savedShoppingCartTable() {
        panel3 = new JPanel();
        panel3.setBackground(new Color(201,218,216));

        JScrollPane scrollPane = new JScrollPane();
        itemList = new JList(items.getItems().toArray());

        viewButton();

        scrollPane.setViewportView(itemList);
        itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        itemList.setSelectedIndex(0);
        panel3.add(scrollPane);
        add(panel3, BorderLayout.EAST);
        setVisible(true);
    }

    private void viewButton() {
        viewShoppingCartButton = new JButton("View Shopping Cart");
        viewShoppingCartButton.setBackground(new Color(183, 218, 212));
        viewShoppingCartButton.setBounds(100, 100, 100, 4);
        viewShoppingCartButton.setFont(new Font("Monospaced", Font.PLAIN, 12));
        panel2.add(viewShoppingCartButton);

        viewShoppingCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int size = itemList.getModel().getSize();
                StringBuilder allItems = new StringBuilder("Items purchased:");
                for (int i = 0; i < size; i++) {
                    allItems.append("\n").append(itemList.getModel().getElementAt(i));
                }
                System.out.print(itemList);
            }
        });
    }

    // EFFECTS: button for user to finish shopping,
    // shows image of thank you message when button is clicked.
    public void finishShopping() {
        frame = new JFrame("Thank you! See you again.");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.white);
        frame.setLayout(new FlowLayout());

        finishShoppingButton = new JButton("Finish Shopping");
        finishShoppingButton.setBackground(new Color(183, 218, 212));
        finishShoppingButton.setBounds(100, 100, 100, 4);
        finishShoppingButton.setFont(new Font("Monospaced", Font.PLAIN, 12));
        panel2.add(finishShoppingButton);

        finishShoppingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon icon = new ImageIcon("./images/thankyou.jpg");
                JLabel label = new JLabel(icon);
                frame.add(label);
                frame.pack();
                frame.setSize(500,500);
            }
        });

        frame.setVisible(true);
    }

    public void writeAndReadData() {
        loadData();
        saveData();
    }

    private void saveData() {
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

    private void loadData() {
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
                List<Item> items1 = items.getItems();
            }
        });
    }
}