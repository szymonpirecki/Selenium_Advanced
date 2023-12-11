package CategoriesTests;

import Base.Pages;
import lombok.extern.slf4j.Slf4j;
import pages.product.CategoryPage;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class CategoriesBase extends Pages {

    protected void validateSubCategoriesDetailsForCategory(String categoryName) {
        CategoryPage categoryPage = headerPage.goToCategoryByName(categoryName);

        if (!categoryPage.checkIfHasSubCategories()) {
            return;
        }

        categoryPage.getSubCategoryNames()
                .forEach(subCategoryName -> {
                    categoryPage.goToSubCategoryByName(subCategoryName);
                    validateCategoryDetails(subCategoryName);
                    driver.navigate().back();
                });
    }

    protected void validateCategoryDetails(String categoryName) {
        CategoryPage categoryPage = headerPage.goToCategoryByName(categoryName);

        assertThat(checkCategoryNameMatch(categoryName)).isTrue();
        assertThat(checkTotalProductLabel(categoryPage)).isTrue();
        assertThat(checkIfFilterTableIsDisplayed()).isTrue();
    }


    private boolean checkCategoryNameMatch(String categoryName) {
        log.info("Checking if header match for category: {}", categoryName);
        return categoryPage.getCategoryHeader().getText().equalsIgnoreCase(categoryName);
    }

    private boolean checkTotalProductLabel(CategoryPage categoryPage) {
        int numberDeclaredInLabel = Integer.parseInt(categoryPage.getTotalProductsLbl().getText().replaceAll("[^0-9]", ""));
        int numberOfDisplayedProducts = productGridPage.getNumberOfDisplayedProducts();
        log.info("Comparing number of products declared in label: {}, and number of displayed products: {}",
                numberDeclaredInLabel, numberOfDisplayedProducts);
        return numberDeclaredInLabel == numberOfDisplayedProducts;
    }

    private boolean checkIfFilterTableIsDisplayed() {
        log.info("Checking if filter table is displayed");
        return productFilterPage.getProductFilterTable().isDisplayed();
    }
}
