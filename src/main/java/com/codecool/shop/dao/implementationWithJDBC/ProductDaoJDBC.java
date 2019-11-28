package com.codecool.shop.dao.implementationWithJDBC;

import com.codecool.shop.config.ConnectionUtil;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        String query = "";
        ConnectionUtil.executeQuery(query);
    }

    @Override
    public void remove(int id) {
        String query = "";
        ConnectionUtil.executeQuery(query);
    }

    @Override
    public Product find(int id) {
        String query = "SELECT * FROM product WHERE id = " + id;
        return fetchAll(query).get(0);
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        String query = "SELECT * FROM product WHERE supplier =" + supplier.getId();
        return fetchAll(query);
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        String query = "SELECT * FROM product WHERE product_category =" + productCategory.getId();
        return fetchAll(query);
    }

    @Override
    public List<Product> getAll() {
        String query = "SELECT * FROM product";
        return fetchAll(query);
    }

    private Product createNewInstanceFromDB(ResultSet resultSet) throws SQLException {
        Product product = new Product(
                resultSet.getString("name"),
                resultSet.getFloat("default_price"),
                resultSet.getString("default_currency"),
                resultSet.getString("description"),
                ProductCategoryDaoJDBC.getInstance().find(resultSet.getInt("product_category")),
                SupplierDaoJDBC.getInstance().find(resultSet.getInt("supplier")));

        product.setId(resultSet.getInt("id"));
        return product;
    }

    private List<Product> fetchAll(String query) {
        List<Product> resultList = new ArrayList<>();

        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
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
}
