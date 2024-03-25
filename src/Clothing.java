import java.io.Serializable;

public class Clothing extends Product implements Serializable {

    private String productSize;
    private String productColor;

    public Clothing(){

    }

    // Constructor for creating Clothing objects
    public Clothing(String productID, String productName, int availableItems, double productPrice, String productSize, String productColor) {
        super(productID, productName, availableItems, productPrice);
        this.productSize = productSize;
        this.productColor = productColor;
    }

    // Setter and getter methods for productSize
    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }
    public String getProductSize() {
        return productSize;
    }

    // Setter and getter methods for productColor
    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }
    public String getProductColor() {
        return productColor;
    }

    @Override
    public String toString() {
        // Call the toString method of the superclass (Product) and append Clothing-specific details
        return super.toString() +
                "\nProduct Size: " + productSize +
                "\nProduct Color: " + productColor;
    }
}
