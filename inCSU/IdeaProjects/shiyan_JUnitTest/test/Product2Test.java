import org.junit.Test;
import static org.junit.Assert.*;

public class Product2Test {

    private String productId = "2";
    private String price = "5 yuan";
    private String name = "Chotolate";
    private String description = "It is sweet";

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
