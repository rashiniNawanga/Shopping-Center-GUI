import java.io.Serializable;

public class Product implements Serializable {

    public String productID;
    public String productName;
    public int availableItems;
    public double productPrice;

    public Product(){

    }
    public Product(String productID, String productName, int availableItems, double productPrice) {
        // Initialize Product attributes
        this.productID = productID;
        this.productName = productName;
        this.availableItems = availableItems;
        this.productPrice = productPrice;
    }

    // Setter and getter methods for productID
    public void setProductID(String productID) {
        this.productID = productID;
    }
    public String getProductID() {
        return productID;
    }

    // Setter and getter methods for productName
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getProductName() {
        return productName;
    }

    // Setter and getter methods for availableItems
    public void setAvailableItems(int availableItems) {
        this.availableItems = availableItems;
    }
    public int getAvailableItems() {
        return availableItems;
    }

    // Setter and getter methods for productPrice
    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
    public double getProductPrice() {
        return productPrice;
    }

    // Override toString method to provide a formatted string representation of the object
    @Override
    public String toString() {
        return "\nProduct ID: " + productID +
                "\nProduct Name: " + productName +
                "\nAvailable Items: " + availableItems +
                "\nProduct Price: " + productPrice;
    }
}
