package steps.product;

import org.openqa.selenium.WebDriver;
import pages.home.HeaderPage;
import steps.base.BaseSteps;

import java.util.List;

public class SearchSteps extends BaseSteps {
    public SearchSteps(WebDriver driver) {
        super(driver);
    }

    public SearchSteps sendKeysToSearch(String productName) {
        at(HeaderPage.class).sendKeysToSearch(productName);
        return this;
    }

    public ProductSteps searchForProduct(String productName) {
        sendKeysToSearch(productName);
        at(HeaderPage.class).clickSearchBtn();
        return new ProductSteps(driver);
    }

    public List<String> getSearchSuggestions() {
        return at(HeaderPage.class).getAllSearchSuggestions();
    }
}
