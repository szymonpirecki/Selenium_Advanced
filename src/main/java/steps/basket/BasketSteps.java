package steps.basket;

import configuration.assertJConfig.AssertJConfigHelper;
import lombok.Getter;
import model.basket.Basket;
import model.basket.BasketLine;
import model.basket.BasketPopUp;
import model.basket.Product;
import org.openqa.selenium.WebDriver;
import pages.basket.BasketPage;
import pages.basket.BasketPopUpPage;
import pages.home.HeaderPage;
import pages.product.ProductGridPage;
import pages.product.ProductPage;
import providers.UrlProvider;
import steps.base.BaseSteps;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@Getter
public class BasketSteps extends BaseSteps {

    private final Basket expectedBasket = new Basket();

    public BasketSteps(WebDriver driver) {
        super(driver);
    }

    public Basket getActualBasket() {
        goToBasket();
        return at(BasketPage.class).toBasketModel();
    }

    public BasketSteps goToBasket() {
        driver.get(UrlProvider.BASKET_URL.getUrl());
        return this;
    }

    public BasketSteps addProductToBasket(Product product, int productQuantity) {
        at(ProductGridPage.class)
                .goToProductPage(product.getName());
        at(ProductPage.class)
                .setQuantity(productQuantity)
                .clickAddToCart();
        expectedBasket.addToBasket(product, productQuantity);
        return this;
    }

    public void continueShopping() {
        at(BasketPopUpPage.class)
                .clickContinueShoppingBtn();
        at(HeaderPage.class)
                .goHomePage();
    }

    public void proceedToCheckout(){
        at(BasketPopUpPage.class)
                .clickProceedToCheckoutBtn();
        at(BasketPage.class)
                .clickProceedToCheckoutBtn();
    }

    public BasketSteps removeFirstBasketLineFromBasket() {
        goToBasket();
        BasketLine firstBasketLine = at(BasketPage.class).getFirstBasketLine();
        expectedBasket.removeFromBasket(firstBasketLine);
        at(BasketPage.class)
                .removeProductFromBasket(firstBasketLine.getProduct());
        return this;
    }


    //Assertions
    public BasketSteps checkBasketPopUpContent(Product product, int productQuantity, BigDecimal shippingPrice) {
        at(BasketPopUpPage.class, p -> {
            var popUpContent = p.toBasketPopUpModel();
            var expectedPopUpContent = BasketPopUp.createExpectedPopUpContent(expectedBasket, product, productQuantity, shippingPrice);

            assertThat(popUpContent)
                    .usingRecursiveComparison(AssertJConfigHelper.getBigDecimalComparisonConfig())
                    .isEqualTo(expectedPopUpContent);
        });
        return this;
    }

    public void checkBasketProductCounter(int expectedProductCount) {
        var cartProductCount = at(HeaderPage.class).getCartProductCount();
        assertThat(cartProductCount)
                .isEqualTo(expectedProductCount)
                .withFailMessage("Number of products on the counter does not match");
    }

    public void checkBasketContent() {
        assertThat(getActualBasket())
                .usingRecursiveComparison(AssertJConfigHelper.getBigDecimalComparisonConfig())
                .isEqualTo(expectedBasket);
    }

    public BasketSteps checkBasketTotalValue() {
        BigDecimal expectedBasketValue = getExpectedBasket().getBasketTotalValue();
        BigDecimal actualBasketValue = getActualBasket().getBasketTotalValue();
        assertThat(actualBasketValue).isEqualTo(expectedBasketValue);
        return this;
    }
}