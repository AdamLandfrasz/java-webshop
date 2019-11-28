package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementationWithJDBC.ProductCategoryDaoJDBC;
import com.codecool.shop.dao.implementationWithJDBC.ProductDaoJDBC;
import com.codecool.shop.dao.implementationWithJDBC.SupplierDaoJDBC;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/product"})
public class ProductPage extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ProductDao productDataStore = ProductDaoJDBC.getInstance();

        int productID;
        productID = Integer.parseInt(req.getParameter("id"));

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("product", productDataStore.find(productID));
        context.setVariable("productCategory", productDataStore.find(productID).getProductCategory());
        context.setVariable("supplier", productDataStore.find(productID).getSupplier());
        engine.process("product_page.html", context, resp.getWriter());
    }


}
