package com.codecool.shop.dao.implementationWithJDBC;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementationWithList.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementationWithList.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ProductDaoJDBC implements ProductDao {
    private static ProductDaoJDBC instance = null;

    private ProductDaoJDBC() {
    }

    public static ProductDaoJDBC getInstance() {
        if (instance == null) {
            instance = new ProductDaoJDBC();
        }
        return instance;
    }

    @Override
    public void add(Product product) {
    }

    @Override
    public Product find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {
    }

    @Override
    public List<Product> getAll() {
        String query = "SELECT * FROM product";
        return getResults(query);
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        String query = "SELECT * FROM product WHERE supplier=" + supplier.getId();
        return getResults(query);
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        String query = "SELECT * FROM product WHERE product_category =" + productCategory.getId();
        return getResults(query);
    }

    private Connection getConnection() throws SQLException {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream("src/main/resources/connection.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(
                String.format("jdbc:postgresql://%s/%s", properties.getProperty("url"), properties.getProperty("db")),
                properties.getProperty("user"),
                properties.getProperty("pw"));
    }

    private void executeQuery(String query) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Product createNewInstance(ResultSet resultSet) throws SQLException {
        Product product = new Product(
                resultSet.getString("name"),
                resultSet.getFloat("default_price"),
                resultSet.getString("default_currency"),
                resultSet.getString("description"),
                ProductCategoryDaoMem.getInstance().find(resultSet.getInt("product_category")),
                SupplierDaoMem.getInstance().find(resultSet.getInt("supplier")));

        product.setId(resultSet.getInt("id"));
        return product;
    }

    private List<Product> getResults(String query) {
        List<Product> resultList = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                resultList.add(createNewInstance(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultList;
    }
}
