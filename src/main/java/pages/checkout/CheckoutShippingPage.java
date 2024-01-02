package pages.checkout;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

public class CheckoutShippingPage extends BasePage {
    public CheckoutShippingPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".delivery-option")
    private List<WebElement> deliveryOptions;
    @FindBy(css = "#checkout-delivery-step button[type='submit']")
    private WebElement confirmShippingBtn;

    public void selectDeliveryOptionByPrice(BigDecimal shippingPrice) {
        DeliveryOptionComponent option = getDeliveryOptions().stream()
                .filter(o -> o.getPrice().compareTo(shippingPrice) == 0)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No delivery option found with price: " + shippingPrice));
        option.selectDeliveryOption();
    }


    public void clickConfirmShippingBtn() {
        click(confirmShippingBtn);
    }

    private List<DeliveryOptionComponent> getDeliveryOptions() {
        waitForAllElements(deliveryOptions);
        return deliveryOptions.stream()
                .map(o -> new DeliveryOptionComponent(driver, o))
                .toList();
    }
}