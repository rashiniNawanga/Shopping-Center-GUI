import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ShoppingManagerGUI {

    private static DefaultTableModel tableModel;
    private static JFrame frame;
    private static JTextArea productTextArea;
    private static JTable cartTable;
    private static ShoppingCart shoppingCart;
    private static DefaultTableModel cartTableModel;

    // Method to create and initialize the GUI
    public static void createGUI() {

        shoppingCart = new ShoppingCart();

        // Create the main frame
        frame = new JFrame("Westminster Shopping Centre");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Create a panel for the combo box and shopping cart button
        JPanel comboPanel = new JPanel();
        comboPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel label = new JLabel("Select Product Category:");

        String[] productTypes = {"All", "Clothes", "Electronics"};
        JComboBox<String> comboBox = new JComboBox<>(productTypes);

        // Action listener for combo box
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedProductType = (String) comboBox.getSelectedItem();
                System.out.println("Selected Product Type: " + selectedProductType);
                // Add logic to visualize products based on the selected type
            }
        });

        // Button to open the shopping cart window
        JButton shoppingCartButton = new JButton("Shopping Cart");
        shoppingCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openShoppingCartWindow();
            }
        });

        comboPanel.add(label);
        comboPanel.add(comboBox);
        comboPanel.add(shoppingCartButton);

        panel.add(comboPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Create a table model for product display
        tableModel = new DefaultTableModel(new Object[]{"Product ID", "Name", "Available Items", "Price", "Info"}, 0);

        for (Product product : WestminsterShoppingManager.newProduct) {
            Object[] row = {product.getProductID(), product.getProductName(),
                    product.getAvailableItems(), product.getProductPrice(),};
            tableModel.addRow(row);
        }

        // Create a table with the product data
        JTable table = new JTable(tableModel);

        table.setPreferredScrollableViewportSize(new Dimension(500, 200));

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    displaySelectedProductDetails(selectedRow);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);

        panel.add(scrollPane);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel productDetailsTitleLabel = new JLabel("Product Details");
        panel.add(productDetailsTitleLabel);

        // Text area for displaying selected product details
        productTextArea = new JTextArea();
        productTextArea.setEditable(false);
        productTextArea.setPreferredSize(new Dimension(500, 150));
        panel.add(productTextArea);

        // Button to add selected product to the shopping cart
        JButton addToCartButton = new JButton("Add to Cart");
        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    String productId = (String) tableModel.getValueAt(selectedRow, 0);
                    Product selectedProduct = findProductById(productId);
                    if (selectedProduct != null) {
                        shoppingCart.addProduct(selectedProduct);
                        selectedProduct.setAvailableItems(selectedProduct.getAvailableItems() - 1);
                        JOptionPane.showMessageDialog(frame, "Product added to the shopping cart!");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Error: Product not found!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        panel.add(addToCartButton);

        frame.getContentPane().add(panel);
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Method to display details of the selected product
    private static void displaySelectedProductDetails(int selectedRow) {
        String productID = (String) tableModel.getValueAt(selectedRow, 0);
        Product selectedProduct = findProductById(productID);

        if (selectedProduct != null) {
            String productDetails = String.format("Selected Product Details:\n\n" +
                            "Product ID: %s\n" +
                            "Name: %s\n" +
                            "Available Items: %d\n" +
                            "Price: %s",
                    selectedProduct.getProductID(), selectedProduct.getProductName(), selectedProduct.getAvailableItems(),
                    selectedProduct.getProductPrice());
            productTextArea.setText(productDetails);
        }
    }

    // Method to find a product by its ID
    private static Product findProductById(String productID) {
        for (Product product : WestminsterShoppingManager.newProduct) {
            if (product.getProductID().equals(productID)) {
                return product;
            }
        }
        return null;
    }

    // Method to open the shopping cart window
    private static void openShoppingCartWindow() {
        JFrame shoppingCartFrame = new JFrame("Shopping Cart");
        shoppingCartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        cartTableModel = new DefaultTableModel(new Object[]{"Product ID", "Name", "Quantity", "Price"}, 0);

        cartTable = new JTable(cartTableModel);

        JScrollPane scrollPane = new JScrollPane(cartTable);

        List<ShoppingCart.CartItem> cartItems = shoppingCart.getCartItems();

        for (ShoppingCart.CartItem cartItem : cartItems) {
            Product product = cartItem.getProduct();
            int quantity = cartItem.getQuantity();
            Object[] row = {product.getProductID(), product.getProductName(), quantity, product.getProductPrice()};
            cartTableModel.addRow(row);
        }

        shoppingCartFrame.getContentPane().add(scrollPane);

        shoppingCartFrame.setSize(500, 400);

        shoppingCartFrame.setLocationRelativeTo(frame);

        shoppingCartFrame.setVisible(true);
    }
}
