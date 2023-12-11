package pages.user.order;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class UserOrderDetailsPage extends BasePage {
    public UserOrderDetailsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#order-infos .box:nth-of-type(1) strong")
    private WebElement orderConfirmationTitle;

    @FindBy(css = "#order-history tbody tr")
    private List<WebElement> orderStatusLines;

    @FindBy(css = "#delivery-address address")
    private WebElement deliveryAddressContainer;

    @FindBy(css = "#invoice-address address")
    private WebElement invoiceAddressContainer;

    @FindBy(css = "#order-products tfoot tr:last-child td:last-child")
    private WebElement totalPrice;


    public List<UserOrderStatusLineComponent> getOrderStatusLines() {
        List<UserOrderStatusLineComponent> statusLines = new ArrayList<>();
        for (WebElement line : orderStatusLines) {
            statusLines.add(new UserOrderStatusLineComponent(driver, line));
        }
        return statusLines;
    }

    public String getOrderConfirmationTitle() {
        return orderConfirmationTitle.getText();
    }

    public String getDeliveryAddress() {
        return deliveryAddressContainer.getText();
    }

    public String getInvoiceAddress() {
        return invoiceAddressContainer.getText();
    }

    public BigDecimal getTotalPrice() {
        return getBigDecimal(totalPrice);
    }


}
