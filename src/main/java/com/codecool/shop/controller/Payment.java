package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.implementationWithJDBC.OrderDaoJDBC;
import com.codecool.shop.dao.implementationWithList.CartDaoMem;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Order;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = {"/cart/payment"})
public class Payment extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Cart cart = CartDaoMem.getInstance().find(req.getSession().getId());

        String firstName = req.getParameter("first-name");
        String lastName = req.getParameter("last-name");
        String email = req.getParameter("email");
        String country = req.getParameter("country");
        String state = req.getParameter("state");
        String address = req.getParameter("address");
        String zip = req.getParameter("zip");

        Order order = new Order(cart, firstName, lastName, email, country, state, address, zip);

        OrderDaoJDBC.getInstance().add(order.getCartString());
        System.out.println("billing address: " + order.getCartString());

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        engine.process("payment.html", context, resp.getWriter());
    }

}
