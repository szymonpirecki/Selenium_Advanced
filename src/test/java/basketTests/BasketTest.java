package basketTests;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.RepeatedTest;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class BasketTest extends BasketBase {

    @RepeatedTest(1)
    public void shouldAddProductToBasketTest() {
        var product = productSteps
                .goToCategory(categoryName)
                .getProductDetails(productName);

        basketSteps
                .addProductToBasket(product, productQuantity)
                .checkBasketPopUpContent(product, productQuantity, shippingPrice)
                .checkBasketProductCounter(productQuantity);
    }

    @RepeatedTest(1)
    public void shouldAddRandomProductsToBasketTest() {
        for (int i = 0; i < 10; i++) {
            basketSteps
                    .addProductToBasket(productSteps.getRandomProduct(), productSteps.getRandomQuantity())
                    .continueShopping();
        }
        basketSteps
                .checkBasketContent();
    }

    @RepeatedTest(1)
    public void shouldRemoveProductsFromBasketTest() {
        for (int i = 0; i < 2; i++) {
            basketSteps
                    .addProductToBasket(productSteps.getRandomProduct(), productSteps.getRandomQuantity())
                    .continueShopping();
        }
        var numberOfBasketLines = basketSteps
                .goToBasket()
                .getActualBasket().getBasketContent().size();

        assertThat(numberOfBasketLines)
                .isGreaterThan(0)
                .withFailMessage("No products in the basket");

        for (int i = 0; i < numberOfBasketLines; i++) {
            basketSteps
                    .checkBasketTotalValue()
                    .removeFirstBasketLineFromBasket()
                    .checkBasketTotalValue();
        }
        basketSteps
                .checkBasketProductCounter(0);
    }
}