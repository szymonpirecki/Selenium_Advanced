package pages.product;

import lombok.extern.slf4j.Slf4j;
import model.basket.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
public class ProductGridPage extends BasePage {
    public ProductGridPage(WebDriver driver) {
        super(driver);
        waitForAllElements(productsOnGrid);
    }

    @FindBy(css = ".product")
    private List<WebElement> productsOnGrid;

    public int getProductCount() {
        return productsOnGrid.size();
    }

    public List<ProductMiniatureComponent> getProductsMiniatureList() {
        return productsOnGrid.stream()
                .map(p -> new ProductMiniatureComponent(driver, p))
                .toList();
    }

    public List<Product> getProductsList() {
        return getProductsMiniatureList().stream()
                .map(ProductMiniatureComponent::toProductModel)
                .toList();
    }


    public void goToProductPage(String productName) {
        getProductsMiniatureList().stream()
                .filter(p -> p.getProductTitle().equalsIgnoreCase(productName))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("Product not found: " + productName))
                .goToProductPage();
    }


    public Product getSpecificProduct(String productName) {
        return getProductsList().stream()
                .filter(p -> p.getName().equals(productName))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("Product not found: " + productName));
    }

}