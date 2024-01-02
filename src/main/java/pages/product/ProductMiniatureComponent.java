package pages.product;

import model.basket.Product;
import model.basket.ProductQueryable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.math.BigDecimal;

public class ProductMiniatureComponent extends BasePage implements ProductQueryable {

    public ProductMiniatureComponent(WebDriver driver, WebElement parent) {
        super(driver, parent);
    }

    @FindBy(css = ".thumbnail")
    private WebElement productImage;

    @FindBy(css = ".product-title")
    private WebElement productTitle;

    @FindBy(css = ".price")
    private WebElement productPrice;

    public String getProductTitle() {
        waitForElement(productTitle);
        return productTitle.getText();
    }

    public BigDecimal getProductPrice() {
        return getBigDecimal(productPrice);
    }

    public void goToProductPage() {
        waitForElement(productTitle);
        productTitle.click();
    }


    @Override
    public Product toProductModel() {
        return new Product(getProductTitle(), getProductPrice());
    }
}
