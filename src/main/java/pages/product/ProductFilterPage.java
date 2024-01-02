package pages.product;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.base.BasePage;

import java.math.BigDecimal;
import java.util.Arrays;


@Getter
@Slf4j
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

    public enum SliderType {
        LEFT, RIGHT
    }

    public void waitForReload() {
        defaultWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".faceted-overlay")));
    }

    public void clearFilter() {
        click(clearFilterBtn);
        waitForReload();
    }

    public boolean isFilterTableDisplayed() {
        log.info("Checking if filter table is displayed");
        return productFilterTable.isDisplayed();
    }

    public void adjustSliderHandle(SliderType sliderType, BigDecimal currentValue, BigDecimal desiredValue) {
        WebElement sliderHandle = (sliderType.equals(SliderType.LEFT)) ? priceSliderHandleLeft : priceSliderHandleRight;

        int adjustments = determineAdjustmentAmount(currentValue, desiredValue);
        if (adjustments == 0) return;

        Keys keyToSend = determineAdjustmentDirection(currentValue, desiredValue);
        for (int i = 0; i < adjustments; i++) {
            waitForElement(priceScope);
            sliderHandle.sendKeys(keyToSend);
            waitForReload();
            log.info("adjustment number: {}", i + 1);
        }
        log.info("Slider was adjusted {} times in {} direction", adjustments, keyToSend);
    }


    private BigDecimal[] getPriceRangeFromWebsite() {
        waitForElement(priceScope);
        String[] values = priceScope.getText().split("-");
        return Arrays.stream(values)
                .map(p -> p.replaceAll("[^0-9.]", ""))
                .map(BigDecimal::new)
                .toArray(BigDecimal[]::new);
    }

    public BigDecimal getMinPriceFromWebsite() {
        BigDecimal[] priceRangeFromWebsite = getPriceRangeFromWebsite();
        return priceRangeFromWebsite[0];
    }

    public BigDecimal getMaxPriceFromWebsite() {
        BigDecimal[] priceRangeFromWebsite = getPriceRangeFromWebsite();
        return priceRangeFromWebsite[1];
    }

    private Keys determineAdjustmentDirection(BigDecimal currentValue, BigDecimal desiredValue) {
        return currentValue.compareTo(desiredValue) < 0 ? Keys.RIGHT : Keys.LEFT;
    }

    private int determineAdjustmentAmount(BigDecimal currentValue, BigDecimal desiredValue) {
        return currentValue.subtract(desiredValue).abs().intValue();
    }
}