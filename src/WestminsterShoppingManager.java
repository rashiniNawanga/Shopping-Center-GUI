import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class WestminsterShoppingManager implements ShoppingManager {

    public static void main(String[] args) throws IOException {
        WestminsterShoppingManager shop = new WestminsterShoppingManager();
        shop.storage();
    }

    Scanner inputChoice = new Scanner(System.in);

    // ArrayList to store products
    public static ArrayList<Product> newProduct = new ArrayList<>();


    // Method to display the console menu
    public static void menuBar() {
        System.out.println("\n----- Welcome to the Westminster Shopping Manager CONSOLE MENU -----");
        System.out.println("1 - Add a new product ");
        System.out.println("2 - Delete a product ");
        System.out.println("3 - Print the list of the products ");
        System.out.println("4 - Save products in a file ");
        System.out.println("5 - open the GUI");
        System.out.println("6 - Exit");

        System.out.println("\nEnter you option :- ");
    }

    // Method to handle the main functionality and user interactions
    public void storage() throws IOException {
        loadFile();
        newProduct.add(new Electronics("E001","Fan", 10, 2000.00, "Innovex", 12));
        newProduct.add(new Electronics("E002","Mobile Phone", 5, 20000.00, "Samsung", 12));
        newProduct.add(new Electronics("E003","Headset", 12, 8000.00, "JBL", 6));
        newProduct.add(new Clothing("C001","Denim", 20, 2000.00, "L", "Orange"));
        newProduct.add(new Clothing("C002","T-Shirt", 15, 4000.00, "M", "Blue"));
        newProduct.add(new Clothing("C003","Cargo Pant", 5, 2500.00, "L", "Pink"));
        newProduct.add(new Clothing("C004","Blouse", 12, 3000.00, "S", "Green"));


        while (true) {

            menuBar();
            String choice = inputChoice.next();

            switch (choice) {

                case "1":
                    addProduct();
                    break;

                case "2":
                    deleteProduct();
                    break;

                case "3":
                    printProductsList();
                    break;

                case "4":
                    saveInFile();
                    break;

                case "5":
                    startGUI();
                    break;

                case "6":
                    System.out.println("Exited!!!");
                    System.exit(0);

                default:
                    System.out.println("!!WRONG INPUT!! Integer Required (1-5)");
                    break;
            }
        }
    }

    // Method to add a new product to the system
    public void addProduct() {
        if (newProduct.size() < 50) {
            int option;
            int availableItems;
            double productPrice;
            int productWarranty;

            System.out.println("\n ------- Adding a new Product to the system -------\n");

            System.out.println("Do you want to add Electronics or Clothing?\n 1.Clothing \n 2.Electronics");
            System.out.print("Enter Your Choice (1 or 2):");
            try {
                option = inputChoice.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid Option!! Please Enter 1 or 2");
                inputChoice.nextLine();
                addProduct();
                return;
            }

            if (!(option == 1) && option != 2) {
                System.out.println("Invalid Option!! Please Enter 1 or 2");
                addProduct();
            }else {
                System.out.print("\nPlease enter product ID : ");
                String productID = inputChoice.next();

                System.out.print("Please enter product name : ");
                String productName = inputChoice.next();

                System.out.println("Please enter number of available items : ");
                try {
                    availableItems = inputChoice.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid integer for available items.");
                    inputChoice.nextLine();
                    addProduct();
                    return;
                }

                System.out.println("Please enter the product price : ");
                try {
                    productPrice = inputChoice.nextDouble();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number for product price.");
                    inputChoice.nextLine();
                    addProduct();
                    return;
                }

                if (option == 2) {

                    System.out.println("Please enter the product productBrand : ");
                    String productBrand = inputChoice.next();

                    System.out.println("Please enter the product productWarranty (months) : ");
                    try {
                        productWarranty = inputChoice.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid integer for product warranty.");
                        inputChoice.nextLine();
                        addProduct();
                        return;
                    }

                    Electronics electronicItem = new Electronics(productID, productName, availableItems, productPrice, productBrand, productWarranty);
                    newProduct.add(electronicItem);

                } else if (option == 1) {

                    System.out.println("Enter the size of the item : ");
                    String productSize = inputChoice.next();

                    System.out.println("Enter the color of the item : ");
                    String productColor = inputChoice.next();

                    Clothing clothingItem = new Clothing(productID, productName, availableItems, productPrice, productSize, productColor);
                    newProduct.add(clothingItem);
                } else {
                    System.out.println("Invalid input!");
                    addProduct();
                    return;
                }
                System.out.println("\n---product has been added successfully into the system ---");
            }

        } else {
            System.out.println("Maximum limit reached. Can not add more than 50 products");
        }
    }

    // Method to delete a product from the system
    public void deleteProduct() {

        ArrayList<Product> removeP = new ArrayList<>();

        System.out.println("\n ------- Deleting a Product from the system -------\n");
        System.out.println("Enter the product ID : ");

        String deleteID = inputChoice.next();

        for (Product p : newProduct) {
            if (deleteID.equals(p.getProductID())) {
                removeP.add(p);
            }
        }

        if (!removeP.isEmpty()) {
            newProduct.removeAll(removeP);
            System.out.println("\n---Products removed---");
        } else {
            System.out.println("No products found with the specified ID.");
        }
    }

    // Method to print the list of products
    public static void printProductsList() {
        for (Product product : newProduct) {
            System.out.println(product);

        }
    }

    // Method to save products in a file
    @Override
    public void saveInFile() throws IOException {

        File txtFile = new File("Products.txt");

        FileWriter fw = new FileWriter(txtFile);

        PrintWriter pw = new PrintWriter(fw);

        for (Product product : newProduct) {
            pw.println(product.toString());
        }

        pw.close();

        System.out.println("\n---Saved in the file---");
    }

    // Method to load products from a file
    public void loadFile() {
        try {
            File txtFile = new File("Products.txt");
            Scanner file = new Scanner(txtFile);

            String fileContent = "";
            while (file.hasNextLine()) {
                fileContent = fileContent.concat(file.nextLine() + "\n");
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    // Method to start the GUI
    public void startGUI() {
        ShoppingManagerGUI shoppingManagerGUI = new ShoppingManagerGUI();
        ShoppingManagerGUI.createGUI();

    }
}


