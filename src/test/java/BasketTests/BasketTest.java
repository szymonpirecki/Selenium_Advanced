package BasketTests;

import configuration.assertJConfig.AssertJConfigHelper;
import lombok.extern.slf4j.Slf4j;
import model.basket.Basket;
import org.junit.jupiter.api.RepeatedTest;
import providers.UrlProvider;

import java.math.BigDecimal;

import static configuration.assertJConfig.AssertJConfigHelper.bigDecimalComparator;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class BasketTest extends BasketBase {

    @RepeatedTest(1)
    public void shouldAddProductToBasketTest() {
        headerPage
                .goToCategoryByName(categoryName);
        var basketLine = basketManager.addProductToBasket(productName, productQuantity);
        var popUpContent = addedToCartPopUpPage.getAddedToCartPopUpContent();
        var expectedPopUpContent = basketManager.createExpectedPopUpContent(basketLine.getProduct(), productQuantity, shippingPrice);

        assertThat(popUpContent)
                .usingRecursiveComparison(AssertJConfigHelper.getBigDecimalComparisonConfig())
                .isEqualTo(expectedPopUpContent);
        addedToCartPopUpPage
                .clickContinueShoppingBtn();
        assertThat(headerPage.getCartProductCount()).isEqualTo(productQuantity);
    }

    @RepeatedTest(1)
    public void shouldAddRandomProductsToBasketTest() {
        for (int i = 0; i < 10; i++) {
            basketManager.addRandomAmountOfRandomProductToBasket();
        }
        driver.get(UrlProvider.BASKET_URL.getUrl());
        var actualBasket = new Basket(basketPage);
        assertThat(actualBasket)
                .usingRecursiveComparison(AssertJConfigHelper.getBigDecimalComparisonConfig())
                .isEqualTo(basket);
    }

    @RepeatedTest(1)
    public void shouldRemoveProductsFromBasketTest() {
        for (int i = 0; i < 2; i++) {
            basketManager.addRandomAmountOfRandomProductToBasket();
        }
        driver.get(UrlProvider.BASKET_URL.getUrl());
        var expectedTotalBasketValue = basket.getBasketTotalValue();
        assertThat(basketPage.getBasketTotalValue()).isEqualTo(expectedTotalBasketValue);

        for (int i = 0; i < 2; i++) {
            basketManager.removeBasketLineFromBasket(basketPage.getBasketLines().get(0));

            var expectedTotalValueAfterRemoving = basket.getBasketTotalValue();
            assertThat(basketPage.getBasketTotalValue())
                    .usingComparator(bigDecimalComparator())
                    .isEqualTo(expectedTotalValueAfterRemoving);

            if (i == 0 && basket.getProductCount() == 0){
                log.debug("In basket was only one basket line - same product was drafted twice and " +
                        "everything was deleted at the first time.");
                break;
            }
        }
        assertThat(headerPage.getCartProductCount()).isEqualTo(0);
        assertThat(basketPage.getBasketTotalValue())
                .usingComparator(bigDecimalComparator())
                .isEqualTo(BigDecimal.ZERO);
    }
}

