package model.basket;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Getter
public class Basket {
    private final List<BasketLine> basketContent;

    public Basket() {
        this.basketContent = new ArrayList<>();
    }

    public Basket(List<BasketLine> basketContent) {
        this.basketContent = basketContent;
    }

    public void addToBasket(Product product, int productQuantity) {
        Optional<BasketLine> basketLine = basketContent.stream()
                .filter(bl -> bl.getProduct().getName().equals(product.getName()))
                .findAny();
        basketLine.ifPresentOrElse(
                bl -> updateQuantity(bl, productQuantity),
                () -> basketContent.add(new BasketLine(product, productQuantity))
        );
    }

    private void updateQuantity(BasketLine basketLine, int additionalQuantity) {
        basketLine.setQuantity(basketLine.getQuantity() + additionalQuantity);
    }

    public void removeFromBasket(BasketLine basketLine) {
        basketContent.removeIf(bl ->
                bl.getProduct().getName().equals(basketLine.getProduct().getName()));
    }

    public int getProductCount() {
        return basketContent.stream()
                .mapToInt(BasketLine::getQuantity)
                .sum();
    }

    public BigDecimal getProductsValue() {
        return basketContent.stream()
                .map(BasketLine::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getBasketTotalValue() {
        if (basketContent.isEmpty()) {
            return BigDecimal.ZERO;
        }
        return getProductsValue().add(new BigDecimal(System.getProperty("shippingPrice")));
    }
}