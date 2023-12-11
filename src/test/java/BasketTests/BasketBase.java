package BasketTests;

import Base.Pages;
import model.basket.Basket;
import org.junit.jupiter.api.BeforeEach;
import utils.basket.BasketManager;

import java.math.BigDecimal;

public class BasketBase extends Pages {
    protected final String categoryName = System.getProperty("basketTests-category");
    protected final String productName = System.getProperty("basketTests-productName");
    protected final int productQuantity = Integer.parseInt(System.getProperty("basketTests-productQuantity"));
    protected final BigDecimal shippingPrice = new BigDecimal(System.getProperty("shippingPrice"));

    Basket basket;
    BasketManager basketManager;

    @BeforeEach
    public void setUpBasket(){
        basket = new Basket();
        basketManager = new BasketManager(driver, basket, random);
    }
}
