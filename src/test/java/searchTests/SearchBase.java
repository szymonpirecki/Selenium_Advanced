package searchTests;

import base.TestBase;
import org.junit.jupiter.api.BeforeEach;
import steps.product.ProductSteps;
import steps.product.SearchSteps;

public class SearchBase extends TestBase {

    protected final String keyword = System.getProperty("searchTests-keyword");
    SearchSteps searchSteps;
    ProductSteps productSteps;

    @BeforeEach
    public void init(){
        searchSteps = new SearchSteps(driver);
        productSteps = new ProductSteps(driver);
    }


}
