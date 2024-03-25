import java.io.FileNotFoundException;
import java.io.IOException;

public interface ShoppingManager {

    public void addProduct();

    public void deleteProduct();

    void saveInFile() throws IOException;

    public void loadFile() throws FileNotFoundException;


}
