package model.basket;

import lombok.ToString;
import model.product.Product;
import pages.basket.BasketLineComponent;
import pages.basket.BasketPage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ToString
public class Basket {
    private final List<BasketLine> basketContent;

    public Basket() {
        this.basketContent = new ArrayList<>();
    }

    public Basket(BasketPage basketPage) {
        List<BasketLineComponent> basketLinesComponents = basketPage.getBasketLines();
        this.basketContent = basketLinesComponents.stream()
                .map(blc -> new BasketLine(new Product(blc.getProductName(), blc.getProductPrice()), blc.getProductQuantity()))
                .toList();
    }

    public void addToBasket(Product product, int productQuantity) {
        long count = basketContent.stream()
                .filter(bl -> bl.getProduct().getName().equals(product.getName()))
                .count();
        switch ((int) count) {
            case 0 -> basketContent.add(new BasketLine(product, productQuantity));
            case 1 -> updateContent(product, productQuantity);
        }
    }

    public void removeFromBasket(BasketLineComponent basketLineComponent) {
        if (!basketContent.isEmpty())
            basketContent.removeIf(bl -> bl.getProduct().getName().equals(basketLineComponent.getProductName()));
    }

    private void updateContent(Product product, int productQuantity) {
        basketContent.forEach(bl -> {
            if (bl.getProduct().getName().equals(product.getName())) {
                bl.setQuantity(bl.getQuantity() + productQuantity);
            }
        });
    }

    public int getProductCount(){
        int sum = 0;
        for (BasketLine basketLine : basketContent){
            sum += basketLine.getQuantity();
        }
        return sum;
    }

    public BigDecimal getProductsValue(){
        BigDecimal sum = new BigDecimal("0");
        for (BasketLine basketLine : basketContent){
            sum = sum.add(basketLine.getTotalPrice());
        }
        return sum;
    }

    public BigDecimal getBasketTotalValue(){
        if (basketContent.isEmpty())
            return BigDecimal.ZERO;
        return getProductsValue().add(new BigDecimal(System.getProperty("shippingPrice")));
    }

}
