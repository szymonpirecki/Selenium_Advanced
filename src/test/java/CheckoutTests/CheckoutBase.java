package CheckoutTests;

import Base.Pages;
import model.basket.Basket;
import org.junit.jupiter.api.BeforeEach;
import utils.basket.BasketManager;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckoutBase extends Pages {

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


    Basket basket;
    BasketManager basketManager;

    @BeforeEach
    public void setUpBasket() {
        basket = new Basket();
        basketManager = new BasketManager(driver, basket, random);
    }

    protected String getExpectedConfirmationTitle(String orderReferenceNumber){
        var expectedPattern = "Order Reference " + orderReferenceNumber + " - placed on " + new SimpleDateFormat("MM/dd/yyyy").format(new Date());
        return Pattern.quote(expectedPattern);
    }

    protected void checkAddress(String address, List<String> expectedContent){
        for (String addressElement : expectedContent){
            assertThat(address).contains(addressElement);
        }
    }
}
