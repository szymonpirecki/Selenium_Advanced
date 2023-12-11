package pages.user.order;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class UserOrderStatusLineComponent extends BasePage {
    public UserOrderStatusLineComponent(WebDriver driver, WebElement parent) {
        super(driver, parent);
    }

    @FindBy(xpath = "./td[1]")
    private WebElement date;

    @FindBy(xpath = "./td[2]")
    private WebElement paymentStatus;

    public String getDate() {
        return date.getText().trim();
    }

    public String getPaymentStatus() {
        return paymentStatus.getText().trim();
    }

}
