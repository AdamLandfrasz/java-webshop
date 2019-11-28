package com.codecool.shop.dao.implementationWithJDBC;

import com.codecool.shop.config.ConnectionUtil;
import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressDaoJDBC {

    private static AddressDaoJDBC instance = null;
    private List<Product> orders = new ArrayList<>();
    private AddressDaoJDBC() {
    }

    public static AddressDaoJDBC getInstance() {
        if (instance == null) {
            instance = new AddressDaoJDBC();
        }
        return instance;
    }

    public void add(List<String> address) {

        List<String> values = new ArrayList<>();

        for (String s : address) {
            values.add("'" + s + "'");
        }

        String query = String.format("INSERT INTO billing_address(firstname, lastname, email, address, country, state, zip) VALUES (%s, %s, %s, %s, %s, %s, %s)", values.get(0), values.get(1), values.get(2), values.get(3), values.get(4), values.get(5), values.get(6));
        System.out.println(query);
        ConnectionUtil.executeQuery(query);
    }
}
