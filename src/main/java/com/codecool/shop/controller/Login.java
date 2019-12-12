package com.codecool.shop.controller;

import com.codecool.shop.config.PasswordUtil;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementationWithJDBC.ProductCategoryDaoJDBC;
import com.codecool.shop.dao.implementationWithJDBC.ProductDaoJDBC;
import com.codecool.shop.dao.implementationWithJDBC.UserDaoJDBC;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login"})
public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (UserDaoJDBC.getInstance().checkIfEmailIsAlreadyUsed(email)) {
            String hashedPassword = UserDaoJDBC.getInstance().getPasswordByEmail(email);
            if (PasswordUtil.validatePassword(password, hashedPassword)) {
                req.getSession().setAttribute("user", email);
            }

        }
        System.out.println(req.getSession().getAttribute("user"));
        resp.sendRedirect("/");
    }


}
