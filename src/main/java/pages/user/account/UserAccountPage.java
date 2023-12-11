package pages.user.account;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class UserAccountPage extends BasePage {
    public UserAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#addresses-link")
    WebElement userAddressesLink;
    @FindBy(css = "#history-link")
    WebElement orderHistoryLink;

    public void goToUserAddresses() {
        clickOnBtn(userAddressesLink);
    }

    public void goToOrderHistory() {
        clickOnBtn(orderHistoryLink);
    }
}
