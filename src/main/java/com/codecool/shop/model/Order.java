package com.codecool.shop.model;

import com.google.gson.Gson;

import java.util.*;

public class Order {

    private Cart cart;
    private Map<String, String> billingDetails = new HashMap<>();
    public static final List<String> BILLING_KEYS = Arrays.asList("firstName", "lastName", "email", "address", "country", "state", "zip");

    public Order(Cart cart, String firstName, String lastName, String email, String address, String country, String state, String zip) {
        this.cart = cart;
        setAddress(Arrays.asList(firstName, lastName, email, address, country, state, zip));
    }

    private void setAddress(List<String> billingDetailsString) {
        for (int i = 0; i < BILLING_KEYS.size(); i++) {
            billingDetails.put(BILLING_KEYS.get(i), billingDetailsString.get(i));
        }
    }

    public Map<String, String> getBillingDetails() {
        return this.billingDetails;
    }

    public String getCartString(){
        List<Map<String, Integer>> dbCart = new ArrayList<>();
        this.cart.getCart().keySet().forEach(key -> {
            Map<String, Integer> productEntry = new HashMap<>();
            productEntry.put("productId", key.id);
            productEntry.put("cartAmount", this.cart.getCart().get(key));
            dbCart.add(productEntry);
        });
        return new Gson().toJson(dbCart);
    }
}
