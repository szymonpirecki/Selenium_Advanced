package pages.user.address;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.List;

public class UserAddressPage extends BasePage {


    public UserAddressPage(WebDriver driver) {
        super(driver);
    }

    @FindAll(@FindBy(css = ".address"))
    private List<WebElement> userAddressesContainers;

    public List<UserAddressContainerComponent> getUserAddressesContainers() {
        waitForAllElements(userAddressesContainers);
        return userAddressesContainers.stream()
                .map(ac -> new UserAddressContainerComponent(driver, ac))
                .toList();
    }

    public void deleteAddressesWithoutFlag(String flag) {
        getUserAddressesContainers().stream()
                .filter(ac -> !ac.getAddressTitle().contains(flag))
                .forEach(UserAddressContainerComponent::clickDeleteAddressBtn);
    }
}
