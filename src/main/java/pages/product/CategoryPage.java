package pages.product;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.List;
import java.util.Optional;

@Getter
public class CategoryPage extends BasePage {
    public CategoryPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".block-category ")
    private WebElement categoryCardBlock;

    @FindBy(css = ".total-products")
    private WebElement totalProductsLbl;

    @FindBy(css = ".category-top-menu")
    private WebElement categoryTopMenu;

    @FindAll(@FindBy(css = ".category-sub-menu a"))
    private List<WebElement> subCategories;

    @FindBy(css = ".block-category > h1")
    private WebElement categoryHeader;

    public boolean checkIfHasSubCategories() {
        return !subCategories.isEmpty();
    }

    public void goToSubCategoryByName(String subCategoryName) {
        waitForAllElements(subCategories);
        Optional<WebElement> category = subCategories.stream()
                .filter(c -> c.getText().equalsIgnoreCase(subCategoryName))
                .findAny();
        category.ifPresent(WebElement::click);
        new CategoryPage(driver);
    }

    public List<String> getSubCategoryNames() {
        waitForAllElements(subCategories);
        return subCategories.stream()
                .map(WebElement::getText)
                .toList();
    }
}
