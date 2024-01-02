package filterTests;

import org.junit.jupiter.api.RepeatedTest;

import static org.assertj.core.api.Assertions.assertThat;

public class FilterTest extends FilterBase {


    @RepeatedTest(1)
    public void filterTest() {
        var productCountBeforeFilter = productSteps
                .goToCategory(category)
                .getProductCount();

        var filteredProducts = filterSteps
                .setPriceScope(minPrice, maxPrice)
                .getProductsList();

        filterSteps
                .checkFilteredProducts(filteredProducts, minPrice, maxPrice);

        var productCountAfterClear = filterSteps
                .clearFilters()
                .getProductCount();

        assertThat(productCountBeforeFilter).isEqualTo(productCountAfterClear);
    }
}