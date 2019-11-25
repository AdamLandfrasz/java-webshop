package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Cart;

import java.util.HashMap;
import java.util.Map;

public class CartDaoMem implements CartDao {

    private Map<String, Cart> data = new HashMap<>();
    private static CartDaoMem instance = null;

    private CartDaoMem() {
    }

    public static CartDaoMem getInstance() {
        if (instance == null) {
            instance = new CartDaoMem();
        }
        return instance;
    }

    @Override
    public void add(Cart cart, String sessionId) {
        cart.setId(data.size() + 1);
        data.put(sessionId, cart);
    }

    @Override
    public Cart find(String sessionId) {
        return data.getOrDefault(sessionId, null);
    }

    @Override
    public void remove(String sessionId) {
        data.remove(sessionId);
    }

    @Override
    public Map<String, Cart> getAll() {
        return data;
    }
}
