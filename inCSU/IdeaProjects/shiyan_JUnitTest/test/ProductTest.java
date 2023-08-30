import org.junit.Test;
import static org.junit.Assert.*;

public class ProductTest {

    private String productId = "1";
    private String price = "3 yuan";
    private String name = "Coca-Cola";
    private String description = "It is delicious";

    private Product product = new Product(productId, price, name, description);

    @Test
    public void testGetProductId() {
        assertEquals(productId, product.getProductId());
    }

    @Test
    public void testGetPrice() {
        assertEquals(price, product.getPrice());
    }

    @Test
    public void testGetName() {
        assertEquals(name, product.getName());
    }

    @Test
    public void testGetDescription() {
        assertEquals(description, product.getDescription());
    }
}
