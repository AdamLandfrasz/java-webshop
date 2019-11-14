package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
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

@WebServlet(urlPatterns = {"/get-cart"})
public class GetCart extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CartDaoMem cartDataStore = CartDaoMem.getInstance();
        ProductDaoMem productDataStore = ProductDaoMem.getInstance();
        Map<Product, Integer> sessionCart = cartDataStore.find(req.getSession().getId()).getCart();
        List<Map<String, String>> responseList = new ArrayList<>();
        for (Product product : sessionCart.keySet()) {
            HashMap<String, String> productMap = new HashMap<>();
            productMap.put("id", String.valueOf(product.getId()));
            productMap.put("name", product.getName());
            productMap.put("price", String.valueOf(product.getDefaultPrice()));
            productMap.put("amount", String.valueOf(sessionCart.get(product)));

            responseList.add(productMap);
        }
        Gson gson = new Gson();
        String jsonString = gson.toJson(responseList);
        resp.getWriter().println(jsonString);



    }

}