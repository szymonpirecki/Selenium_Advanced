package model.basket;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class BasketPopUp {
    private final String productName;
    private final BigDecimal productPrice;
    private final int productQuantity;
    private final int productCount;
    private final BigDecimal subTotalValue;
    private final BigDecimal shippingValue;
    private final BigDecimal productTotalValue;


    public static BasketPopUp createExpectedPopUpContent(Basket basket, Product product, int productQuantity, BigDecimal shippingPrice) {
        return BasketPopUp.builder()
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