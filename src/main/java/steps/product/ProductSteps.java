package steps.product;

import lombok.extern.slf4j.Slf4j;
import model.basket.Product;
import org.openqa.selenium.WebDriver;
import pages.home.HeaderPage;
import pages.product.CategoryPage;
import pages.product.ProductFilterPage;
import pages.product.ProductGridPage;
import steps.base.BaseSteps;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ProductSteps extends BaseSteps {
    public ProductSteps(WebDriver driver) {
        super(driver);
    }

    public Product getProductDetails(String productName) {
        return at(ProductGridPage.class).getSpecificProduct(productName);
    }

    public ProductSteps goToCategory(String categoryName) {
        at(HeaderPage.class).goToCategory(categoryName);
        return new ProductSteps(driver);
    }

    public List<String> getCategoryNames() {
        return at(HeaderPage.class).getCategoryNames();
    }

    public List<Product> getProductsList() {
        return at(ProductGridPage.class).getProductsList();
    }

    public int getProductCount() {
        return at(ProductGridPage.class).getProductCount();
    }

    public Product getRandomProduct() {
        return getRandom(at(ProductGridPage.class).getProductsList());
    }

    public int getRandomQuantity() {
        return random.nextInt(5) + 1;
    }

    //Assertions
    public void validateSubCategoriesDetailsForCategory(String categoryName) {
        log.info("Checking: {}", categoryName);
        goToCategory(categoryName);

        if (!at(CategoryPage.class).checkIfHasSubCategories()) {
            return;
        }

        for (String subCategoryName : at(CategoryPage.class).getSubCategoryNames()) {
            log.info("Checking: {}", subCategoryName);
            at(CategoryPage.class).goToSubCategory(subCategoryName);
            validateCategoryDetails(subCategoryName);
            driver.navigate().back();
        }
    }

    public void validateCategoryDetails(String categoryName) {
        log.info("Checking: {}", categoryName);
        goToCategory(categoryName);

        var categoryHeader = at(CategoryPage.class).getCategoryHeader();
        var productCountLbl = at(CategoryPage.class).getTotalProductsCount();
        var productCount = at(ProductGridPage.class).getProductCount();

        log.debug("Category name: " + categoryName + "\n" +
                "Category header: " + categoryHeader + "\n" +
                "Products counter: " + productCountLbl + "\n" +
                "Number of displayed products:" + productCount);

        assertThat(categoryHeader).isEqualToIgnoringCase(categoryName);
        assertThat(productCountLbl).isEqualTo(productCount);
        assertThat(at(ProductFilterPage.class).isFilterTableDisplayed()).isTrue();
    }
}