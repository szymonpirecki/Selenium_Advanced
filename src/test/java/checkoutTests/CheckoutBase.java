package checkoutTests;

import base.TestBase;
import org.junit.jupiter.api.BeforeEach;
import steps.basket.BasketSteps;
import steps.checkout.CheckoutSteps;
import steps.product.ProductSteps;
import steps.user.AccountSteps;
import steps.user.LogInSteps;

import java.math.BigDecimal;
import java.util.List;

public class CheckoutBase extends TestBase {

    protected final String firstName = System.getProperty("checkoutTests-user-firstName");
    protected final String lastName = System.getProperty("checkoutTests-user-lastName");
    protected final String email = System.getProperty("checkoutTests-user-email");
    protected final String password = System.getProperty("checkoutTests-user-password");
    protected final String deliveryAddress = System.getProperty("checkoutTests-deliveryAddress-address");
    protected final String deliveryPostCode = System.getProperty("checkoutTests-deliveryAddress-postCode");
    protected final String deliveryCity = System.getProperty("checkoutTests-deliveryAddress-city");
    protected final String deliveryCountry = System.getProperty("checkoutTests-deliveryAddress-country");
    protected final String invoiceAddress = System.getProperty("checkoutTests-invoiceAddress-address");
    protected final String invoicePostCode = System.getProperty("checkoutTests-invoiceAddress-postCode");
    protected final String invoiceCity = System.getProperty("checkoutTests-invoiceAddress-city");
    protected final String invoiceCountry = System.getProperty("checkoutTests-invoiceAddress-country");
    protected List<String> deliveryAddressContent = List.of(firstName, lastName, deliveryAddress, deliveryPostCode, deliveryCity, deliveryCountry);
    protected List<String> invoiceAddressContent = List.of(firstName, lastName, invoiceAddress, invoicePostCode, invoiceCity, invoiceCountry);
    protected final String category = System.getProperty("checkoutTests-product-category");
    protected final String productName = System.getProperty("checkoutTests-product-name");
    protected final int productQuantity = Integer.parseInt(System.getProperty("checkoutTests-product-quantity"));
    protected final BigDecimal shippingPrice = new BigDecimal(System.getProperty("shippingPrice"));
    protected final String expectedPaymentStatus = System.getProperty("checkoutTests-paymentStatus");

    LogInSteps prepSteps;
    ProductSteps productSteps;
    AccountSteps accountSteps;
    BasketSteps basketSteps;
    CheckoutSteps checkoutSteps;

    @BeforeEach
    public void setUpBasket() {
        prepSteps = new LogInSteps(driver);
        productSteps = new ProductSteps(driver);
        accountSteps = new AccountSteps(driver);
        basketSteps = new BasketSteps(driver);
        checkoutSteps = new CheckoutSteps(driver);
    }
}
