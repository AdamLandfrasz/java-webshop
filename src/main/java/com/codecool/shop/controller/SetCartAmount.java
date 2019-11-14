package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;
import com.google.gson.Gson;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.http.Cookie;
import java.util.*;

@WebServlet(urlPatterns = {"/set-cart"})
public class SetCartAmount extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CartDaoMem cartDataStore = CartDaoMem.getInstance();
        ProductDaoMem productDataStore = ProductDaoMem.getInstance();
        Gson gson = new Gson();

        Cart sessionCart = cartDataStore.find(req.getSession().getId());
        Product product = productDataStore.find(Integer.parseInt(req.getParameter("productId")));
        Integer newAmount = Integer.parseInt(req.getParameter("newAmount"));
        sessionCart.setProductAmount(product, newAmount);

        // Accessing cart map that has Product objects as keys and Integers as values
        Map<Product, Integer> sessionCartMap = sessionCart.getCart();
        // Creating collection to be sent as response
        List<Map<String, String>> responseList = new ArrayList<>();
        // Converting each product in cart into a map with String keys and String values
        // So it is possible to later convert it to a JS object and read the field
        sessionCartMap.keySet().forEach(mapProduct -> {
            HashMap<String, String> productMap = new HashMap<>();

            productMap.put("id", String.valueOf(mapProduct.getId()));
            productMap.put("name", mapProduct.getName());
            productMap.put("price", String.valueOf(mapProduct.getDefaultPrice()));
            productMap.put("amount", String.valueOf(sessionCartMap.get(mapProduct)));

            responseList.add(productMap);
        });
        // Converting response list into JSON string and sending response
        String jsonString = gson.toJson(responseList);
        resp.getWriter().println(jsonString);
    }

}