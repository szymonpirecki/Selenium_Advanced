package categoryTests;

import base.TestBase;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import steps.product.ProductSteps;

@Slf4j
public class CategoryBase extends TestBase {

    ProductSteps testSteps;

    @BeforeEach
    public void init() {
        testSteps = new ProductSteps(driver);
    }
}
