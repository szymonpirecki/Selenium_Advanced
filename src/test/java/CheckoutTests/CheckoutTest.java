package CheckoutTests;

import model.order.OrderDetails;
import org.junit.jupiter.api.RepeatedTest;
import pages.user.order.UserOrderLineComponent;
import providers.UrlProvider;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckoutTest extends CheckoutBase {


    @RepeatedTest(1)
    public void shouldGoThroughBuyingProcessTest() {
        driver.get(UrlProvider.LOGIN_URL.getUrl());
        logInPage
                .logIn(email, password);
        userAccountPage
                .goToUserAddresses();
        userAddressPage
                .deleteAddressesWithoutFlag("DON'T DELETE");
        headerPage
                .goToCategoryByName(category);
        basketManager
                .addProductToBasket(productName, productQuantity);
        addedToCartPopUpPage
                .clickProceedToCheckoutBtn();
        basketPage
                .clickProceedToCheckoutBtn();
        checkoutAddressPage
                .clickDifferentInvoiceAddressLink()
                .provideAddress(invoiceAddress, invoicePostCode, invoiceCity, invoiceCountry)
                .clickConfirmAddressBtn();
        checkoutShippingPage
                .selectDeliveryOptionByPrice(shippingPrice)
                .clickConfirmShippingBtn();
        checkoutPaymentPage
                .selectPayByCheck()
                .approveConditions()
                .clickPlaceOrderBtn();

        var orderReferenceNumber = orderConfirmationPage.getOrderReferenceNumber();

        headerPage
                .goToAccountPage();
        userAccountPage
                .goToOrderHistory();

        Optional<UserOrderLineComponent> orderLine = userOrdersPage.getSpecificOrderLine(orderReferenceNumber);
        assertThat(orderLine)
                .as("Order line should be present for order reference number: " + orderReferenceNumber)
                .isPresent();
        orderLine.ifPresent(UserOrderLineComponent::goToOrderDetailsPage);

        var expectedOrderConfirmationTitle = getExpectedConfirmationTitle(orderReferenceNumber);
        var orderDetails = new OrderDetails(userOrderDetailsPage);
        checkAddress(orderDetails.getDeliveryAddress(), deliveryAddressContent);
        checkAddress(orderDetails.getInvoiceAddress(), invoiceAddressContent);
        assertThat(orderDetails.getOrderConfirmationTitle()).matches(expectedOrderConfirmationTitle);
        assertThat(orderDetails.getPaymentStatus()).isEqualTo(expectedPaymentStatus);
    }
}