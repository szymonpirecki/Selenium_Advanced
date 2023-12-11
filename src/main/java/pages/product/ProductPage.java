package pages.product;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;
import pages.basket.AddedToCartPopUpPage;

import java.math.BigDecimal;

@Getter
public class ProductPage extends BasePage {
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#quantity_wanted")
    private WebElement quantityInput;

    @FindBy(css = ".add-to-cart")
    private WebElement addToCartBtn;

    @FindBy(css = ".current-price [itemprop='price']")
    private WebElement currentPrice;

    public ProductPage setQuantity(int quantity) {
        clearAndSendKeys(quantityInput, String.valueOf(quantity));
        return this;
    }

    public void clickAddToCart() {
        clickOnBtn(addToCartBtn);
        new AddedToCartPopUpPage(driver);
    }

    public BigDecimal getPrice() {
        return getBigDecimal(currentPrice);
    }
}
