package pages.basket;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.math.BigDecimal;
import java.util.List;

public class BasketPage extends BasePage {
    public BasketPage(WebDriver driver) {
        super(driver);
    }

    @FindAll(@FindBy(css = ".cart-item"))
    private List<WebElement> basketLines;

    @FindBy(css = "#cart-subtotal-products .label")
    private WebElement productsInBasketCount;

    @FindBy(css = "#cart-subtotal-products .value")
    private WebElement productsInBasketValue;

    @FindBy(css = "cart-subtotal-shipping .value")
    private WebElement shippingPrice;

    @FindBy(css = ".cart-total .value")
    private WebElement basketTotalValue;

    @FindBy(css = ".checkout .btn-primary")
    private WebElement proceedToCheckoutBtn;

    public List<BasketLineComponent> getBasketLines() {
        waitForAllElements(basketLines);
        return basketLines.stream()
                .map(bl -> new BasketLineComponent(driver, bl))
                .toList();
    }

    public BigDecimal getBasketTotalValue(){
        waitForElement(basketTotalValue);
        return getBigDecimal(basketTotalValue);
    }

    public void clickProceedToCheckoutBtn(){
        clickOnBtn(proceedToCheckoutBtn);
    }
}
