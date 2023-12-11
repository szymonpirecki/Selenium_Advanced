package utils.basket;

import lombok.AllArgsConstructor;
import model.basket.AddedToCartPopUpContent;
import model.basket.Basket;
import model.basket.BasketLine;
import model.product.Product;
import org.openqa.selenium.WebDriver;
import pages.basket.AddedToCartPopUpPage;
import pages.basket.BasketLineComponent;
import pages.product.ProductGridPage;
import pages.product.ProductPage;
import providers.UrlProvider;

import java.math.BigDecimal;
import java.util.Random;

@AllArgsConstructor
public class BasketManager {

    private WebDriver driver;
    private Basket basket;
    private Random random;

    public BasketLine addProductToBasket(String productName, int productQuantity) {
        ProductGridPage productGridPage = new ProductGridPage(driver);
        ProductPage productPage = new ProductPage(driver);

        BigDecimal price = productGridPage
                .goToProductPageByName(productName)
                .getPrice();
        productPage
                .setQuantity(productQuantity)
                .clickAddToCart();
        Product product = new Product(productName, price);
        basket.addToBasket(product, productQuantity);
        return new BasketLine(product, productQuantity);
    }

    public void addRandomAmountOfRandomProductToBasket() {
        ProductGridPage productGridPage = new ProductGridPage(driver);
        AddedToCartPopUpPage addedToCartPopUpPage = new AddedToCartPopUpPage(driver);

        var randomProductName = productGridPage.getRandomProductName();
        var randomQuantity = random.nextInt(5) + 1;
        addProductToBasket(randomProductName, randomQuantity);
        addedToCartPopUpPage
                .clickContinueShoppingBtn();
        driver.get(UrlProvider.HOME_URL.getUrl());
    }

    public void removeBasketLineFromBasket(BasketLineComponent basketLineComponent) {
        basket.removeFromBasket(basketLineComponent);
        basketLineComponent.clickRemoveBtn();
    }

    public AddedToCartPopUpContent createExpectedPopUpContent(Product product, int productQuantity, BigDecimal shippingPrice) {
        return new AddedToCartPopUpContent.Builder()
                .productName(product.getName())
                .productPrice(product.getPrice())
                .productQuantity(productQuantity)
                .productCount(basket.getProductCount())
                .shippingValue(shippingPrice)
                .subTotalValue(basket.getProductsValue())
                .productTotalValue(basket.getProductsValue().add(shippingPrice))
                .build();
    }
}