package pages.basket;

import lombok.Getter;
import model.basket.AddedToCartPopUpContent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.math.BigDecimal;

@Getter
public class AddedToCartPopUpPage extends BasePage {


    public AddedToCartPopUpPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#myModalLabel")
    private WebElement popupTitle;

    @FindBy(css = ".product-name")
    private WebElement productName;

    @FindBy(css = ".modal-body .product-price")
    private WebElement productPrice;

    @FindBy(css = ".modal-body .product-quantity")
    private WebElement productQuantity;

    @FindBy(css = ".modal-body .cart-products-count")
    private WebElement productCount;

    @FindBy(css = ".subtotal")
    private WebElement subTotalValue;

    @FindBy(css = ".shipping ")
    private WebElement shippingValue;

    @FindBy(css = ".product-total .value")
    private WebElement productTotalValue;

    @FindBy(css = ".cart-content-btn .btn-primary")
    private WebElement continueShoppingBtn;

    @FindBy(css = ".cart-content-btn .btn-primary")
    private WebElement proceedToCheckoutBtn;


    public String getProductName() {
        return productName.getText();
    }

    public BigDecimal getProductPrice() {
        return getBigDecimal(productPrice);
    }

    public int getProductQuantity() {
        return getInt(productQuantity);
    }

    public int getProductCount() {
        return getInt(productCount);
    }

    public BigDecimal getProductTotalValue() {
        return getBigDecimal(productTotalValue);
    }

    public BigDecimal getShippingValue() {
        return getBigDecimal(shippingValue);
    }

    public BigDecimal getSubTotalValue() {
        return getBigDecimal(subTotalValue);
    }

    public AddedToCartPopUpContent getAddedToCartPopUpContent() {
        waitForElement(popupTitle);
        return new AddedToCartPopUpContent(this);
    }

    public void clickContinueShoppingBtn() {
        clickOnBtn(continueShoppingBtn);
    }

    public void clickProceedToCheckoutBtn() {
        clickOnBtn(proceedToCheckoutBtn);
    }
}
