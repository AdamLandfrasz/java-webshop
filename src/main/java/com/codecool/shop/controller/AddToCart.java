package com.codecool.shop.controller;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementationWithJDBC.ProductDaoJDBC;
import com.codecool.shop.dao.implementationWithList.CartDaoMem;
import com.codecool.shop.model.Cart;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/add-cart"})
public class AddToCart extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        CartDao cartDataStore = CartDaoMem.getInstance();
        ProductDao productDataStore = ProductDaoJDBC.getInstance();

        Cart sessionCart = cartDataStore.find(req.getSession().getId());
        if (sessionCart != null) {
            sessionCart.addToCart(productDataStore.find(Integer.parseInt(req.getParameter("productId"))));
        } else {
            cartDataStore.add(new Cart(), req.getSession().getId());
            sessionCart = cartDataStore.find(req.getSession().getId());
            sessionCart.addToCart(productDataStore.find(Integer.parseInt(req.getParameter("productId"))));
        }

        Gson gson = new Gson();
        resp.getWriter().println(gson.toJson("OK"));
    }

}