package pages.home;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.base.BasePage;
import pages.product.CategoryPage;
import pages.product.ProductGridPage;

import java.util.List;
import java.util.Optional;

@Getter
public class HeaderPage extends BasePage {

    public HeaderPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#contact-link")
    private WebElement contactUsLink;

    @FindBy(css = ".user-info a")
    private WebElement signInLink;

    @FindBy(css = ".account")
    private WebElement userAccountLink;

    @FindBy(css = ".shopping-cart")
    private WebElement shoppingCartBtn;

    @FindBy(css = ".cart-products-count")
    private WebElement cartProductCount;

    @FindBy(css = ".logo")
    private WebElement logo;

    @FindAll(@FindBy(css = "#top-menu > .category"))
    private List<WebElement> mainCategories;

    @FindBy(css = "#top-menu")
    private WebElement categoriesMenu;

    @FindBy(css = "#category-3")
    private WebElement clothesLabel;

    @FindBy(css = "#category-4")
    private WebElement menClothesLabel;

    @FindBy(css = "#category-5")
    private WebElement womenClothesLabel;

    @FindBy(css = "#category-6")
    private WebElement accessoriesLabel;

    @FindBy(css = "#category-7")
    private WebElement stationeryAccessoriesLabel;

    @FindBy(css = "#category-8")
    private WebElement homeAccessoriesLabel;

    @FindBy(css = "#category-9")
    private WebElement artLabel;

    @FindBy(css = "#search_widget input[type='text']")
    private WebElement searchInput;

    @FindBy(css = "button[type='submit']")
    private WebElement searchBtn;

    @FindBy(css = ".ui-autocomplete ")
    private WebElement searchSuggestionsDropdown;

    @FindAll(@FindBy(css = ".ui-menu-item"))
    private List<WebElement> searchSuggestions;

    public HeaderPage sendKeysToSearch(String searchText) {
        clearAndSendKeys(searchInput, searchText);
        return this;
    }

    public ProductGridPage clickOnSearchBtn() {
        defaultWait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();
        return new ProductGridPage(driver);
    }

    public List<String> getAllSearchSuggestions() {
        waitForElement(searchSuggestionsDropdown);
        return searchSuggestions.stream().map(WebElement::getText).toList();
    }

    public List<String> getCategoryNames() {
        waitForAllElements(mainCategories);
        return getMainCategories().stream()
                .map(WebElement::getText)
                .toList();
    }

    public CategoryPage goToCategoryByName(String categoryName) {
        waitForElement(categoriesMenu);
        Optional<WebElement> category = mainCategories.stream()
                .filter(c -> c.getText().equals(categoryName))
                .findFirst();
        category.ifPresent(WebElement::click);
        return new CategoryPage(driver);
    }

    public int getCartProductCount() {
        waitForElement(cartProductCount);
        return getInt(cartProductCount);
    }

    public void goToAccountPage() {
        clickOnBtn(userAccountLink);
    }
}
