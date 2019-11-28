package com.codecool.shop.model;

import java.util.HashMap;
import java.util.Map;

public class Order {

    private Cart cart;
    private Map<String, String> address = new HashMap<>();

    public Order(Cart cart, String firstName, String lastName, String email, String address, String country, String state, String zip) {
        this.cart = cart;
        setAddress(firstName, lastName, email, address, country, state, zip);
    }

    private void setAddress(String firstName, String lastName, String email, String address, String country, String state, String zip) {

        this.address.put("firstName", firstName);
        this.address.put("lastName", lastName);
        this.address.put("email", email);
        this.address.put("address", address);
        this.address.put("country", country);
        this.address.put("state", state);
        this.address.put("zip", zip);
    }


    public String getCartString(){
        return "'" + this.address.toString() + "'";
    }


}
