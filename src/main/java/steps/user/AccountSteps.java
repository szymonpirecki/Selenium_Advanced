package steps.user;

import model.order.OrderDetails;
import org.openqa.selenium.WebDriver;
import pages.home.HeaderPage;
import pages.user.account.AccountPage;
import pages.user.address.AddressPage;
import pages.user.order.OrderDetailsPage;
import pages.user.order.OrderHistoryPage;
import pages.user.order.OrderLineComponent;
import steps.base.BaseSteps;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountSteps extends BaseSteps {
    public AccountSteps(WebDriver driver) {
        super(driver);
    }

    public void removeRedundantAddressData() {
        goToAddresses();
        at(AddressPage.class)
                .deleteAddressesWithoutFlag("DON'T DELETE");
    }

    public AccountSteps goToAccount() {
        at(HeaderPage.class)
                .goToAccountPage();
        return this;
    }

    public AccountSteps goToOrders() {
        at(AccountPage.class).goToOrderHistory();
        return this;
    }

    public AccountSteps goToOrderDetails(String orderNumber) {
        Optional<OrderLineComponent> orderLine = Optional.ofNullable(at(OrderHistoryPage.class).getSpecificOrderLine(orderNumber));
        assertThat(orderLine)
                .as("Order line should be present for order reference number: " + orderNumber)
                .isPresent();
        orderLine.ifPresent(OrderLineComponent::goToOrderDetailsPage);
        return this;
    }

    private void goToAddresses() {
        at(AccountPage.class).goToUserAddresses();
    }


    private String getExpectedConfirmationTitle(String orderReferenceNumber) {
        var expectedPattern = "Order Reference " + orderReferenceNumber + " - placed on " + new SimpleDateFormat("MM/dd/yyyy").format(new Date());
        return Pattern.quote(expectedPattern);
    }

    //Assertions
    public void checkOrderDetails(String orderNumber, List<String> deliveryAddressContent, List<String> invoiceAddressContent, String expectedPaymentStatus) {
        var orderDetails = at(OrderDetailsPage.class).toOrderDetailsModel();
        checkAddress(orderDetails.getInvoiceAddress(), invoiceAddressContent);
        checkAddress(orderDetails.getDeliveryAddress(), deliveryAddressContent);
        checkConfirmationTitle(orderDetails, orderNumber);
        checkPaymentStatus(orderDetails, expectedPaymentStatus);
        softAssertions.assertAll();
    }

    private void checkConfirmationTitle(OrderDetails orderDetails, String orderNumber) {
        softAssertions.assertThat(orderDetails.getOrderConfirmationTitle()).matches(getExpectedConfirmationTitle(orderNumber));

    }

    private void checkAddress(String address, List<String> expectedContent) {
        for (String addressElement : expectedContent) {
            softAssertions.assertThat(address).contains(addressElement);
        }
    }

    private void checkPaymentStatus(OrderDetails orderDetails, String expectedPaymentStatus) {
        softAssertions.assertThat(orderDetails.getPaymentStatus()).isEqualTo(expectedPaymentStatus);
    }
}
