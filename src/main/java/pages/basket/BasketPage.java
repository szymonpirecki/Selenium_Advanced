package pages.basket;

import lombok.extern.slf4j.Slf4j;
import model.basket.Basket;
import model.basket.BasketLine;
import model.basket.BasketQueryable;
import model.basket.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
public class BasketPage extends BasePage implements BasketQueryable {
    public BasketPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".cart-item")
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

    public List<BasketLineComponent> getBasketLineComponents() {
        return basketLines.stream()
                .map(bl -> new BasketLineComponent(driver, bl))
                .toList();
    }

    public List<BasketLine> getBasketLines() {
        return getBasketLineComponents().stream()
                .map(BasketLineComponent::toBasketLineModel)
                .toList();
    }

    public BasketLine getFirstBasketLine() {
        if (basketLines.isEmpty()) {
            log.error("Basket is empty, cannot return basket line");
        }
        return getBasketLines().get(0);
    }

    public BigDecimal getBasketTotalValue() {
        waitForElement(basketTotalValue);
        return getBigDecimal(basketTotalValue);
    }

    public void clickProceedToCheckoutBtn() {
        click(proceedToCheckoutBtn);
    }

    public Basket toBasketModel() {
        return new Basket(getBasketLines());
    }

    public void removeProductFromBasket(Product product) {
        getBasketLineComponents().stream()
                .filter(l -> l.getProductName().equals(product.getName()))
                .findAny()
                .orElseThrow(() ->new NoSuchElementException("Product not found in the basket"))
                .clickRemoveBtn();
    }

}