package model.basket;

import lombok.Getter;
import lombok.ToString;
import pages.basket.AddedToCartPopUpPage;

import java.math.BigDecimal;

@ToString
@Getter
public class AddedToCartPopUpContent {
    private final String productName;
    private final BigDecimal productPrice;
    private final int productQuantity;
    private final int productCount;
    private final BigDecimal subTotalValue;
    private final BigDecimal shippingValue;
    private final BigDecimal productTotalValue;

    public AddedToCartPopUpContent(AddedToCartPopUpPage addedToCartPopUpPage) {
        this.productName = addedToCartPopUpPage.getProductName();
        this.productPrice = addedToCartPopUpPage.getProductPrice();
        this.productQuantity = addedToCartPopUpPage.getProductQuantity();
        this.productCount = addedToCartPopUpPage.getProductCount();
        this.subTotalValue = addedToCartPopUpPage.getSubTotalValue();
        this.shippingValue = addedToCartPopUpPage.getShippingValue();
        this.productTotalValue = addedToCartPopUpPage.getProductTotalValue();
    }

    private AddedToCartPopUpContent(Builder builder) {
        this.productName = builder.productName;
        this.productPrice = builder.productPrice;
        this.productQuantity = builder.productQuantity;
        this.productCount = builder.productCount;
        this.subTotalValue = builder.subTotalValue;
        this.shippingValue = builder.shippingValue;
        this.productTotalValue = builder.productTotalValue;
    }

    public static class Builder {
        private String productName;
        private BigDecimal productPrice;
        private int productQuantity;
        private int productCount;
        private BigDecimal subTotalValue;
        private BigDecimal shippingValue;
        private BigDecimal productTotalValue;

        public Builder productName(String productName) {
            this.productName = productName;
            return this;
        }

        public Builder productPrice(BigDecimal productPrice) {
            this.productPrice = productPrice;
            return this;
        }

        public Builder productQuantity(int productQuantity) {
            this.productQuantity = productQuantity;
            return this;
        }

        public Builder productCount(int productCount) {
            this.productCount = productCount;
            return this;
        }

        public Builder subTotalValue(BigDecimal subTotalValue) {
            this.subTotalValue = subTotalValue;
            return this;
        }

        public Builder shippingValue(BigDecimal shippingValue) {
            this.shippingValue = shippingValue;
            return this;
        }

        public Builder productTotalValue(BigDecimal productTotalValue) {
            this.productTotalValue = productTotalValue;
            return this;
        }

        public AddedToCartPopUpContent build() {
            return new AddedToCartPopUpContent(this);
        }
    }
}

