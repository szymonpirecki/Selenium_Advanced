package pages.user.order;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class OrderHistoryPage extends BasePage {
    public OrderHistoryPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "tbody tr")
    private List<WebElement> userOrders;


    public OrderLineComponent getSpecificOrderLine(String orderReferenceNumber) {
        return getUserOrderLines().stream()
                .filter(l -> l.getReferenceNumber().equalsIgnoreCase(orderReferenceNumber))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Order line not found for reference number: " + orderReferenceNumber));
    }


    private List<OrderLineComponent> getUserOrderLines() {
        List<OrderLineComponent> rowPages = new ArrayList<>();
        for (WebElement row : userOrders) {
            rowPages.add(new OrderLineComponent(driver, row));
        }
        return rowPages;
    }
}
