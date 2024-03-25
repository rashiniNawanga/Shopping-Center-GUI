import java.io.Serializable;

public class Electronics extends Product implements Serializable {

    private String productBrand;
    private int productWarranty;

    public Electronics(){

    }

    // Constructor for creating Electronics objects
    public Electronics(String productID, String productName, int availableItems, double productPrice, String productBrand, int productWarranty) {
        super(productID, productName, availableItems, productPrice);
        this.productBrand = productBrand;
        this.productWarranty = productWarranty;
    }

    // Setter and getter methods for productBrand
    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }
    public String getProductBrand() {
        return productBrand;
    }

    // Setter and getter methods for productWarranty
    public void setProductWarranty(int productWarranty) {
        this.productWarranty = productWarranty;
    }
    public int getProductWarranty() {
        return productWarranty;
    }

    @Override
    public String toString() {
        // Call the toString method of the superclass (Product) and append Electronics-specific details
        return super.toString() +
                "\nProduct Brand: " + productBrand +
                "\nProduct Warranty: " + productWarranty;
    }
}
