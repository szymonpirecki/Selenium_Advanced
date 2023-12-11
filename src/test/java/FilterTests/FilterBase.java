package FilterTests;

import Base.Pages;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;
import java.util.Arrays;

@Slf4j
public class FilterBase extends Pages {
    protected final String accessories_category = System.getProperty("filterTests-category");
    protected final BigDecimal minPrice = new BigDecimal(System.getProperty("filterTests-minPrice"));
    protected final BigDecimal maxPrice = new BigDecimal(System.getProperty("filterTests-maxPrice"));

    public void setPriceScope(BigDecimal from, BigDecimal to) {
        log.info("Setting price scope from {} to {}", from, to);
        adjustSliderHandle(productFilterPage.getPriceSliderHandleLeft(), getMinPriceFromWebsite(), from);
        adjustSliderHandle(productFilterPage.getPriceSliderHandleRight(), getMaxPriceFromWebsite(), to);
    }

    private void adjustSliderHandle(WebElement sliderHandle, BigDecimal currentValue, BigDecimal desiredValue) {
        int adjustments = determineAdjustmentAmount(currentValue, desiredValue);
        if (adjustments == 0) return;

        Keys keyToSend = determineAdjustmentDirection(currentValue, desiredValue);
        for (int i = 0; i < adjustments; i++) {
            basePage.waitForElement(productFilterPage.getPriceScope());
            sliderHandle.sendKeys(keyToSend);
            productFilterPage.waitForReload();
            log.info("adjustment number: {}", i + 1);
        }
        log.info("Slider was adjusted {} times in {} direction", adjustments, keyToSend);
    }


    private BigDecimal[] getPriceRangeFromWebsite() {
        basePage.waitForElement(productFilterPage.getPriceScope());
        String[] values = productFilterPage.getPriceScope().getText().split("-");
        return Arrays.stream(values)
                .map(p -> p.replaceAll("[^0-9.]", ""))
                .map(BigDecimal::new)
                .toArray(BigDecimal[]::new);
    }

    private BigDecimal getMinPriceFromWebsite() {
        BigDecimal[] priceRangeFromWebsite = getPriceRangeFromWebsite();
        return priceRangeFromWebsite[0];
    }

    private BigDecimal getMaxPriceFromWebsite() {
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
