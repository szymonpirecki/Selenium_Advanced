package checkoutTests;

import org.junit.jupiter.api.RepeatedTest;

public class CheckoutTest extends CheckoutBase {


    @RepeatedTest(1)
    public void shouldGoThroughBuyingProcessTest() {
        prepSteps
                .goToLoginPage()
                .logInUser(email, password)
                .removeRedundantAddressData();

        var product = productSteps
                .goToCategory(category)
                .getProductDetails(productName);

        basketSteps
                .addProductToBasket(product, productQuantity)
                .proceedToCheckout();

        var orderNumber = checkoutSteps
                .setInvoiceAddress(invoiceAddress, invoicePostCode, invoiceCity, invoiceCountry)
                .setDeliveryOption(shippingPrice)
                .setPaymentOption()
                .placeOrder()
                .getOrderNumber();

        accountSteps
                .goToAccount()
                .goToOrders()
                .goToOrderDetails(orderNumber)
                .checkOrderDetails(orderNumber, deliveryAddressContent, invoiceAddressContent, expectedPaymentStatus);
    }
}