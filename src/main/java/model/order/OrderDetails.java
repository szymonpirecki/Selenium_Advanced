package model.order;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class OrderDetails {

    private final String orderConfirmationTitle;
    private final String paymentStatus;
    private final String deliveryAddress;
    private final String invoiceAddress;
    private final BigDecimal totalPrice;
}