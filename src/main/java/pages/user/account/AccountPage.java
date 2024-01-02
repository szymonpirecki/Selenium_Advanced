package pages.user.account;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class AccountPage extends BasePage {
    public AccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#addresses-link")
    WebElement userAddressesLink;
    @FindBy(css = "#history-link")
    WebElement orderHistoryLink;

    public void goToUserAddresses() {
        click(userAddressesLink);
    }

    public void goToOrderHistory() {
        click(orderHistoryLink);
    }
}
