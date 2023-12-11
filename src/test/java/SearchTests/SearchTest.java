package SearchTests;

import org.junit.jupiter.api.RepeatedTest;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchTest extends SearchBase {

    @RepeatedTest(1)
    public void shouldSearchRandomProductTest() {
        var randomProductName = productGridPage.getRandomProductName();
        long count = headerPage
                .sendKeysToSearch(randomProductName)
                .clickOnSearchBtn()
                .getProductsList().stream()
                .filter(p -> p.getProductTitle().equals(randomProductName))
                .count();

        assertThat(count).isEqualTo(1);
    }

    @RepeatedTest(1)
    public void shouldSuggestSpecificProductsDuringSearchingTest() {
        headerPage
                .sendKeysToSearch(keyword)
                .waitForElement(headerPage.getSearchSuggestionsDropdown());

        var allSearchSuggestions = headerPage.getAllSearchSuggestions();

        assertThat(allSearchSuggestions)
                .isNotEmpty()
                .allMatch(suggestion -> suggestion.contains(keyword));
    }
}