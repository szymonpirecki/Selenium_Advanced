package pages.user.order;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserOrdersPage extends BasePage {
    public UserOrdersPage(WebDriver driver) {
        super(driver);
    }

    @FindAll(@FindBy(css = "tbody tr"))
    private List<WebElement> userOrders;


    public List<UserOrderLineComponent> getUserOrderLines() {
        List<UserOrderLineComponent> rowPages = new ArrayList<>();
        for (WebElement row : userOrders) {
            rowPages.add(new UserOrderLineComponent(driver, row));
        }
        return rowPages;
    }

    public Optional<UserOrderLineComponent> getSpecificOrderLine(String orderReferenceNumber) {
        return getUserOrderLines().stream()
                .filter(l -> l.getReferenceNumber().equalsIgnoreCase(orderReferenceNumber))
                .findFirst();
    }
}
