package pages.checkout;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class CheckoutPaymentPage extends BasePage {
    public CheckoutPaymentPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "input[data-module-name='ps_checkpayment']")
    private WebElement payByCheckRadioBtn;

    @FindBy(css = "#conditions-to-approve .custom-checkbox")
    private WebElement conditionsToApproveCheckbox;

    @FindBy(css = "#payment-confirmation button[type='submit']")
    private WebElement placeOrderBtn;

    public CheckoutPaymentPage selectPayByCheck() {
        payByCheckRadioBtn.click();
        return this;
    }

    public CheckoutPaymentPage approveConditions() {
        conditionsToApproveCheckbox.click();
        return this;
    }

    public void clickPlaceOrderBtn() {
        clickOnBtn(placeOrderBtn);
    }
}
