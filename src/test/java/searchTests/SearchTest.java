package searchTests;

import model.basket.Product;
import org.junit.jupiter.api.RepeatedTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchTest extends SearchBase {

    @RepeatedTest(1)
    public void shouldSearchRandomProductTest() {
        var randomProductName = productSteps.getRandomProduct().getName();
        List<Product> searchResultList = searchSteps
                .searchForProduct(randomProductName)
                .getProductsList();

        assertThat(searchResultList)
                .isNotEmpty()
                .allSatisfy(product -> product.getName().equals(randomProductName));
    }

    @RepeatedTest(1)
    public void shouldSuggestSpecificProductsDuringSearchingTest() {
        List<String> searchSuggestions = searchSteps
                .sendKeysToSearch(keyword)
                .getSearchSuggestions();

        assertThat(searchSuggestions)
                .isNotEmpty()
                .allMatch(suggestion -> suggestion.contains(keyword));
    }
}