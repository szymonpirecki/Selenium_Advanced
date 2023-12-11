package pages.product;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.List;
import java.util.Optional;

@Slf4j
public class ProductGridPage extends BasePage {
    public ProductGridPage(WebDriver driver) {
        super(driver);
    }

    @FindAll(@FindBy(css = ".product"))
    private List<WebElement> productsOnGrid;

    public int getNumberOfDisplayedProducts() {
        waitForAllElements(productsOnGrid);
        return productsOnGrid.size();
    }

    public List<ProductMiniatureComponent> getProductsList() {
        waitForAllElements(productsOnGrid);
        return productsOnGrid.stream()
                .map(p -> new ProductMiniatureComponent(driver, p))
                .toList();
    }

    public String getRandomProductName() {
        waitForAllElements(productsOnGrid);
        List<ProductMiniatureComponent> productsList = getProductsList();
        return productsList.get(random.nextInt(productsList.size())).getProductTitle();
    }

    public ProductPage goToProductPageByName(String productName) {
        waitForAllElements(productsOnGrid);
        Optional<ProductMiniatureComponent> any = getProductsList().stream()
                .filter(p -> p.getProductTitle().equalsIgnoreCase(productName))
                .findAny();
        any.ifPresent(ProductMiniatureComponent::goToProductPage);
        return new ProductPage(driver);
    }
}