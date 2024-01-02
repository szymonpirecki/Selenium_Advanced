package pages.basket;

import model.basket.BasketLine;
import model.basket.BasketLineQueryable;
import model.basket.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.base.BasePage;

import java.math.BigDecimal;

public class BasketLineComponent extends BasePage implements BasketLineQueryable {
    public BasketLineComponent(WebDriver driver, WebElement parent) {
        super(driver, parent);
    }

    @FindBy(css = ".product-line-info a.label")
    private WebElement productName;

    @FindBy(css = ".product-line-info .price")
    private WebElement productPrice;

    @FindBy(css = ".js-cart-line-product-quantity")
    private WebElement productQuantity;

    @FindBy(css = ".remove-from-cart")
    private WebElement removeFromCartBtn;

    public String getProductName() {
        return productName.getText();
    }

    public BigDecimal getProductPrice() {
        return getBigDecimal(productPrice);
    }

    public int getProductQuantity() {
        return getValue(productQuantity);
    }

    public void clickRemoveBtn() {
        click(removeFromCartBtn);
        defaultWait.until(ExpectedConditions.stalenessOf(removeFromCartBtn));
    }

    @Override
    public BasketLine toBasketLineModel() {
        return new BasketLine(new Product(getProductName(), getProductPrice()), getProductQuantity());
    }
}
