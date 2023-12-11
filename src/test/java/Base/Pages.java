package Base;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import pages.base.BasePage;
import pages.basket.AddedToCartPopUpPage;
import pages.basket.BasketPage;
import pages.checkout.CheckoutAddressPage;
import pages.checkout.CheckoutPaymentPage;
import pages.checkout.CheckoutShippingPage;
import pages.checkout.OrderConfirmationPage;
import pages.home.HeaderPage;
import pages.login.LogInPage;
import pages.product.CategoryPage;
import pages.product.ProductFilterPage;
import pages.product.ProductGridPage;
import pages.product.ProductPage;
import pages.user.account.UserAccountPage;
import pages.user.address.UserAddressPage;
import pages.user.order.UserOrderDetailsPage;
import pages.user.order.UserOrdersPage;

@Slf4j
public class Pages extends TestBase{

    public AddedToCartPopUpPage addedToCartPopUpPage;
    public BasePage basePage;
    public CategoryPage categoryPage;
    public HeaderPage headerPage;
    public ProductFilterPage productFilterPage;
    public ProductGridPage productGridPage;
    public ProductPage productPage;
    public BasketPage basketPage;
    public LogInPage logInPage;
    public CheckoutAddressPage checkoutAddressPage;
    public UserAddressPage userAddressPage;
    public UserAccountPage userAccountPage;
    public CheckoutShippingPage checkoutShippingPage;
    public CheckoutPaymentPage checkoutPaymentPage;
    public OrderConfirmationPage orderConfirmationPage;
    public UserOrdersPage userOrdersPage;
    public UserOrderDetailsPage userOrderDetailsPage;

    @BeforeEach
    public void setUpPages(){
        log.info("####SETING UP PAGES####");
        addedToCartPopUpPage = new AddedToCartPopUpPage(driver);
        basePage = new BasePage(driver);
        categoryPage = new CategoryPage(driver);
        headerPage = new HeaderPage(driver);
        productFilterPage = new ProductFilterPage(driver);
        productGridPage = new ProductGridPage(driver);
        productPage = new ProductPage(driver);
        basketPage = new BasketPage(driver);
        logInPage = new LogInPage(driver);
        checkoutAddressPage = new CheckoutAddressPage(driver);
        userAccountPage = new UserAccountPage(driver);
        userAddressPage = new UserAddressPage(driver);
        checkoutShippingPage = new CheckoutShippingPage(driver);
        checkoutPaymentPage = new CheckoutPaymentPage(driver);
        orderConfirmationPage = new OrderConfirmationPage(driver);
        userOrdersPage = new UserOrdersPage(driver);
        userOrderDetailsPage = new UserOrderDetailsPage(driver);
    }

}
