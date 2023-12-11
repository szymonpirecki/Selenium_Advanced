package pages.checkout;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.math.BigDecimal;
import java.util.List;

public class CheckoutShippingPage extends BasePage {
    public CheckoutShippingPage(WebDriver driver) {
        super(driver);
    }

    @FindAll(@FindBy(css = ".delivery-option"))
    private List<WebElement> deliveryOptions;
    @FindBy(css = "#checkout-delivery-step button[type='submit']")
    private WebElement confirmShippingBtn;

    public List<DeliveryOptionComponent> getDeliveryOptions() {
        waitForAllElements(deliveryOptions);
        return deliveryOptions.stream()
                .map(o -> new DeliveryOptionComponent(driver, o))
                .toList();
    }

    public CheckoutShippingPage selectDeliveryOptionByPrice(BigDecimal shippingPrice) {
        getDeliveryOptions().stream()
                .filter(o -> o.getPrice().compareTo(shippingPrice) == 0)
                .limit(1)
                .forEach(DeliveryOptionComponent::selectDeliveryOption);
        return this;
    }

    public void clickConfirmShippingBtn() {
        clickOnBtn(confirmShippingBtn);
    }
}