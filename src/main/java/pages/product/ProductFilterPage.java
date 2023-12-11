package pages.product;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.base.BasePage;


@Getter
public class ProductFilterPage extends BasePage {

    public ProductFilterPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#search_filters")
    private WebElement productFilterTable;

    @FindBy(css = ".ui-slider-handle:first-of-type")
    private WebElement priceSliderHandleLeft;

    @FindBy(css = ".ui-slider-handle:last-of-type")
    private WebElement priceSliderHandleRight;

    @FindBy(css = "[data-slider-label='Price'] p")
    private WebElement priceScope;

    @FindBy(css = "#_desktop_search_filters_clear_all > button")
    private WebElement clearFilterBtn;


    public void waitForReload() {
        defaultWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".faceted-overlay")));
    }

    public void clickOnClearButton() {
        clickOnBtn(clearFilterBtn);
        waitForReload();
    }
}