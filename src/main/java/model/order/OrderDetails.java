package model.order;

import lombok.Getter;
import lombok.ToString;
import pages.user.order.UserOrderDetailsPage;

import java.math.BigDecimal;

@Getter
@ToString
public class OrderDetails {

    private final String orderConfirmationTitle;
    private final String paymentStatus;
    private final String deliveryAddress;
    private final String invoiceAddress;
    private final BigDecimal totalPrice;

    public OrderDetails(UserOrderDetailsPage userOrderDetailsPage) {
        this.orderConfirmationTitle = userOrderDetailsPage.getOrderConfirmationTitle();
        var orderStatusLines = userOrderDetailsPage.getOrderStatusLines();
        this.paymentStatus = orderStatusLines.size() == 1 ? orderStatusLines.get(0).getPaymentStatus() : orderStatusLines.get(orderStatusLines.size()-1).getPaymentStatus();
        this.deliveryAddress = userOrderDetailsPage.getDeliveryAddress();
        this.invoiceAddress = userOrderDetailsPage.getInvoiceAddress();
        this.totalPrice = userOrderDetailsPage.getTotalPrice();
    }
}
