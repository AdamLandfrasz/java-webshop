package com.codecool.shop.model;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart extends BaseModel {
    private Map<Product, Integer> cart = new LinkedHashMap<>();

    public Cart() {
        super("cart");
    }

    public Map<Product, Integer> getCart() {
        return cart;
    }

    public void setProductAmount(Product product, Integer amount) {
        this.cart.put(product, amount);
        if (this.cart.get(product) <= 0) {
            this.cart.remove(product);
        }
    }

    public void addToCart(Product product) {
        this.setProductAmount(product, this.cart.getOrDefault(product, 0) + 1);
    }

    public void removeFromCart(Product product) {
        this.setProductAmount(product, this.cart.get(product) - 1);
    }

    @Override
    public String toString() {
        return "Cart{" + cart + "}";
    }
    

    public float getCartPrice (){
        float price = 0;
        for (Product product : cart.keySet()) {
            price += product.getDefaultPrice()*cart.get(product);
        }

        return price;
    }
}
