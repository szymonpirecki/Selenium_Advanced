package categoryTests;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.assertFalse;

@Slf4j
public class CategoryTest extends CategoryBase {

    @RepeatedTest(1)
    public void shouldValidateCategoryDetailsTest() {
        var categoryNames = testSteps.getCategoryNames();
        assertFalse(categoryNames.isEmpty(), "Couldn't get category names.");

        for (String category : categoryNames) {
            testSteps.validateCategoryDetails(category);
        }
    }


    @RepeatedTest(1)
    public void shouldValidateSubCategoryDetailsTest() {
        var categoryNames = testSteps.getCategoryNames();
        assertFalse(categoryNames.isEmpty(), "Couldn't get category names.");

        for (String category : categoryNames) {
            testSteps.validateSubCategoriesDetailsForCategory(category);
        }
    }
}