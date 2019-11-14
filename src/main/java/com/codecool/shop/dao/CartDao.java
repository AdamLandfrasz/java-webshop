package com.codecool.shop.dao;

import com.codecool.shop.model.Cart;
import java.util.List;
import java.util.Map;

public interface CartDao {

    void add(Cart cart, String sessionId);
    Cart find(String sessionId);
    void remove(String sessionId);

    Map<String, Cart> getAll();

}
