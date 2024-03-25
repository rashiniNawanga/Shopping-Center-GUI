import java.util.ArrayList;
import java.util.List;


public class ShoppingCart {

    private static List<CartItem> cartItems;


    public ShoppingCart() {
        this.cartItems = new ArrayList<>();
    }

    // Method to add a product to the shopping cart
    public static void addProduct(Product product) {
        // Check if the product is already in the cart
        for (CartItem cartItem : cartItems) {
            if (cartItem.getProduct().getProductID().equals(product.getProductID())) {
                cartItem.incrementQuantity();
                return;
            }
        }

        // If the product is not in the cart, add a new CartItem with quantity 1
        cartItems.add(new CartItem(product, 1));
    }

    // Method to get the list of CartItems in the shopping cart
    public List<CartItem> getCartItems() {
        return cartItems;
    }


    // Override toString method to provide a formatted string representation of the shopping cart
    @Override
    public String toString() {
        return cartItems.toString();
    }

    // CartItem class represents an item in the shopping cart with a product and quantity
    public static class CartItem {
        private Product product;
        private int quantity;

        // Constructor to create a CartItem with a product and quantity
        public CartItem(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }

        // Getter method to retrieve the product of the CartItem
        public Product getProduct() {
            return product;
        }

        // Getter method to retrieve the quantity of the CartItem
        public int getQuantity() {
            return quantity;
        }

        // Method to increment the quantity of the CartItem
        public void incrementQuantity() {
            quantity++;
        }

        // Method to decrement the quantity of the CartItem (with a check to avoid negative quantity)
        public void decrementQuantity() {
            if (quantity != 1) {
                quantity--;
            }
        }
    }
}
