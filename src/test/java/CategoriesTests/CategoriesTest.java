package CategoriesTests;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.assertFalse;

@Slf4j
public class CategoriesTest extends CategoriesBase {

    @RepeatedTest(1)
    public void shouldValidateCategoryDetailsTest() {
        var categoryNames = headerPage.getCategoryNames();
        assertFalse(categoryNames.isEmpty(), "Couldn't get category names.");

        for (String category : categoryNames){
            validateCategoryDetails(category);
        }
    }


    @RepeatedTest(1)
    public void shouldValidateSubCategoryDetailsTest() {
        var categoryNames = headerPage.getCategoryNames();
        assertFalse(categoryNames.isEmpty(), "Couldn't get category names.");

        for (String category : categoryNames){
            validateSubCategoriesDetailsForCategory(category);
        }
    }
}