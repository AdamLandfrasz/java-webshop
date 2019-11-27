package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementationWithList.CartDaoMem;
import com.codecool.shop.model.Cart;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/cart"})
public class CartHandler extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        CartDaoMem cartDataStore = CartDaoMem.getInstance();

        if (cartDataStore.find(req.getSession().getId()) == null) {
            cartDataStore.add(new Cart(), req.getSession().getId());
        }
        Cart cart = cartDataStore.find(req.getSession().getId());

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("cart", cart.getCart());
        engine.process("cart.html", context, resp.getWriter());
    }

}