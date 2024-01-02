package basketTests;

import base.TestBase;
import org.junit.jupiter.api.BeforeEach;
import steps.basket.BasketSteps;
import steps.product.ProductSteps;

import java.math.BigDecimal;

public class BasketBase extends TestBase {
    protected final String categoryName = System.getProperty("basketTests-category");
    protected final String productName = System.getProperty("basketTests-productName");
    protected final int productQuantity = Integer.parseInt(System.getProperty("basketTests-productQuantity"));
    protected final BigDecimal shippingPrice = new BigDecimal(System.getProperty("shippingPrice"));

    BasketSteps basketSteps;
    ProductSteps productSteps;


    @BeforeEach
    public void setUpBasket() {
        productSteps = new ProductSteps(driver);
        basketSteps = new BasketSteps(driver);
    }
}
