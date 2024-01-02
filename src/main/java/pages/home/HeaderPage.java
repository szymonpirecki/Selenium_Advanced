package pages.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.base.BasePage;

import java.util.List;
import java.util.Optional;

public class HeaderPage extends BasePage {

    public HeaderPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".account")
    private WebElement userAccountLink;

    @FindBy(css = ".shopping-cart")
    private WebElement shoppingCartBtn;

    @FindBy(css = ".cart-products-count")
    private WebElement cartProductCount;

    @FindBy(css = ".logo")
    private WebElement logo;

    @FindBy(css = "#top-menu > .category")
    private List<WebElement> mainCategories;

    @FindBy(css = "#top-menu")
    private WebElement categoriesMenu;

    @FindBy(css = "#search_widget input[type='text']")
    private WebElement searchInput;

    @FindBy(css = "button[type='submit']")
    private WebElement searchBtn;

    @FindBy(css = ".ui-autocomplete ")
    private WebElement searchSuggestionsDropdown;

    @FindBy(css = ".ui-menu-item")
    private List<WebElement> searchSuggestions;

    public void sendKeysToSearch(String searchText) {
        sendKeys(searchInput, searchText);
    }

    public void clickSearchBtn() {
        defaultWait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();
    }

    public List<String> getAllSearchSuggestions() {
        waitForElement(searchSuggestionsDropdown);
        return searchSuggestions.stream().map(WebElement::getText).toList();
    }

    public List<String> getCategoryNames() {
        waitForAllElements(mainCategories);
        return mainCategories.stream()
                .map(WebElement::getText)
                .toList();
    }

    public void goToCategory(String categoryName) {
        waitForElement(categoriesMenu);
        Optional<WebElement> category = mainCategories.stream()
                .filter(c -> c.getText().equals(categoryName))
                .findFirst();
        category.ifPresent(WebElement::click);
    }

    public int getCartProductCount() {
        waitForElement(cartProductCount);
        return getInt(cartProductCount);
    }

    public void goToAccountPage() {
        click(userAccountLink);
    }

    public void goHomePage() {
        click(logo);
    }
}
