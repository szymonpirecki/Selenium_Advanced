package steps.checkout;

import org.openqa.selenium.WebDriver;
import pages.checkout.CheckoutAddressPage;
import pages.checkout.CheckoutPaymentPage;
import pages.checkout.CheckoutShippingPage;
import pages.checkout.OrderConfirmationPage;
import steps.base.BaseSteps;

import java.math.BigDecimal;

public class CheckoutSteps extends BaseSteps {
    public CheckoutSteps(WebDriver driver) {
        super(driver);
    }

    public CheckoutSteps setInvoiceAddress(String address, String postCode, String city, String country) {
        at(CheckoutAddressPage.class, checkoutAddressPage -> {
            checkoutAddressPage.clickDifferentInvoiceAddressLink();
            checkoutAddressPage.provideAddress(address, postCode, city, country);
            checkoutAddressPage.clickConfirmAddressBtn();
        });
        return this;
    }

    public CheckoutSteps setDeliveryOption(BigDecimal shippingPrice) {
        at(CheckoutShippingPage.class, checkoutShippingPage -> {
            checkoutShippingPage.selectDeliveryOptionByPrice(shippingPrice);
            checkoutShippingPage.clickConfirmShippingBtn();
        });
        return this;
    }

    public CheckoutSteps setPaymentOption() {
        at(CheckoutPaymentPage.class).selectPayByCheck();
        return this;
    }


    public CheckoutSteps placeOrder() {
        at(CheckoutPaymentPage.class, checkoutPaymentPage -> {
            checkoutPaymentPage.approveConditions();
            checkoutPaymentPage.clickPlaceOrderBtn();
        });
        return this;
    }

    public String getOrderNumber(){
        return at(OrderConfirmationPage.class).getOrderReferenceNumber();
    }


}
