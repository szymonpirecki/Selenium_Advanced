package filterTests;

import base.TestBase;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import steps.product.FilterSteps;
import steps.product.ProductSteps;

import java.math.BigDecimal;

@Slf4j
public class FilterBase extends TestBase {
    protected final String category = System.getProperty("filterTests-category");
    protected final BigDecimal minPrice = new BigDecimal(System.getProperty("filterTests-minPrice"));
    protected final BigDecimal maxPrice = new BigDecimal(System.getProperty("filterTests-maxPrice"));
    protected FilterSteps filterSteps;
    protected ProductSteps productSteps;

    @BeforeEach
    public void init(){
        filterSteps = new FilterSteps(driver);
        productSteps = new ProductSteps(driver);
    }
}
