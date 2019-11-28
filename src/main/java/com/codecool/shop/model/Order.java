package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {

    private Cart cart;
    private List<String> address = new ArrayList<>();

    public Order(Cart cart, String firstName, String lastName, String email, String address, String country, String state, String zip) {
        this.cart = cart;
        setAddress(firstName, lastName, email, address, country, state, zip);
    }

    private void setAddress(String firstName, String lastName, String email, String address, String country, String state, String zip) {

        this.address.add(firstName);
        this.address.add(lastName);
        this.address.add(email);
        this.address.add(address);
        this.address.add(country);
        this.address.add(state);
        this.address.add(zip);
    }

    public List<String> getAddress() {

        return this.address;
    }


    public String getCartString(){
        return "'" + this.cart.toString() + "'";
    }


}
