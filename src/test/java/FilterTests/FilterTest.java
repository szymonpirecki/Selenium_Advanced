package FilterTests;

import org.junit.jupiter.api.RepeatedTest;

import static org.assertj.core.api.Assertions.assertThat;

public class FilterTest extends FilterBase {


    @RepeatedTest(1)
    public void filterTest() {
        headerPage.goToCategoryByName(accessories_category);
        var preFilterCount = productGridPage.getNumberOfDisplayedProducts();

        setPriceScope(minPrice, maxPrice);
        var filteredProducts = productGridPage.getProductsList();

        filteredProducts.forEach(p -> {
            assertThat(p.getProductPrice()).isGreaterThan(minPrice).isLessThan(maxPrice);
        });

        productFilterPage.clickOnClearButton();
        var postClearCount = productGridPage.getNumberOfDisplayedProducts();
        assertThat(preFilterCount).isEqualTo(postClearCount);
    }
}