package com.codecool.shop.dao.implementationWithJDBC;

import com.codecool.shop.config.ConnectionUtil;
import com.codecool.shop.controller.Payment;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementationWithList.ProductCategoryDaoMem;
import com.codecool.shop.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDaoJDBC {

    private static OrderDaoJDBC instance = null;
    private List<Product> orders = new ArrayList<>();

    private OrderDaoJDBC() {
    }

    public static OrderDaoJDBC getInstance() {
        if (instance == null) {
            instance = new OrderDaoJDBC();
        }
        return instance;
    }

    public void add(String cart) {

        String now = Long.toString(Instant.now().getEpochSecond());

        String query = String.format("INSERT INTO paid_order(cart, date) VALUES (%s, %s)",cart, now);
        System.out.println(query);
        ConnectionUtil.executeQuery(query);
    }


    /*
    public void remove(int id) {
        String query = "";
        ConnectionUtil.executeQuery(query);
    }

    public Product find(int id) {
        return getAll().stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    public List<Product> getBy(Supplier supplier) {
        return getAll().stream().filter(t -> t.getSupplier().equals(supplier)).collect(Collectors.toList());
    }

    public List<Product> getBy(ProductCategory productCategory) {
        return getAll().stream().filter(t -> t.getProductCategory().equals(productCategory)).collect(Collectors.toList());
    }

    public List<Product> getAll() {
        if (products.isEmpty()) {
            products = fetchAll();
        }
        return products;
    }

    private Product createNewInstanceFromDB(ResultSet resultSet) throws SQLException {
        Product product = new Product(
                resultSet.getString("name"),
                resultSet.getFloat("default_price"),
                resultSet.getString("default_currency"),
                resultSet.getString("description"),
                ProductCategoryDaoMem.getInstance().find(resultSet.getInt("product_category")),
                SupplierDaoJDBC.getInstance().find(resultSet.getInt("supplier")));

        product.setId(resultSet.getInt("id"));
        return product;
    }

    private List<Product> fetchAll() {
        List<Product> resultList = new ArrayList<>();

        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM product");
             ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                resultList.add(createNewInstanceFromDB(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultList;
    }
    */
}
