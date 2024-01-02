package steps.product;

import lombok.extern.slf4j.Slf4j;
import model.basket.Product;
import org.openqa.selenium.WebDriver;
import pages.product.ProductFilterPage;
import steps.base.BaseSteps;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class FilterSteps extends BaseSteps {

    public FilterSteps(WebDriver driver) {
        super(driver);
    }


    public ProductSteps clearFilters() {
        at(ProductFilterPage.class).clearFilter();
        return new ProductSteps(driver);
    }

    public ProductSteps setPriceScope(BigDecimal from, BigDecimal to) {
        at(ProductFilterPage.class, productFilterPage -> {
            productFilterPage.adjustSliderHandle(ProductFilterPage.SliderType.LEFT, at(ProductFilterPage.class).getMinPriceFromWebsite(), from);
            productFilterPage.adjustSliderHandle(ProductFilterPage.SliderType.RIGHT, at(ProductFilterPage.class).getMaxPriceFromWebsite(), to);
        });
        return new ProductSteps(driver);
    }

    //Assertions
    public void checkFilteredProducts(List<Product> filteredProducts, BigDecimal minPrice, BigDecimal maxPrice) {
        assertThat(filteredProducts)
                .isNotEmpty()
                .withFailMessage("There are no products in this price range");
        filteredProducts.forEach(product -> assertThat(product.getPrice())
                .isGreaterThanOrEqualTo(minPrice)
                .isLessThanOrEqualTo(maxPrice)
                .withFailMessage("Product {} is outside price range", product.getName()));
    }
}
