package model.basket;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import model.product.Product;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class BasketLine {

    private Product product;
    private BigDecimal totalPrice;
    private int quantity;

    public BasketLine(Product product, int quantity) {
        this.product = product;
        setQuantity(quantity);
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.totalPrice = product.getPrice().multiply(new BigDecimal(String.valueOf(quantity)));
    }
}