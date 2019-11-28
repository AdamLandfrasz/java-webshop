package com.codecool.shop.dao.implementationWithJDBC;

import com.codecool.shop.config.ConnectionUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoJDBC implements ProductCategoryDao {

    private static ProductCategoryDaoJDBC instance = null;
    private List<ProductCategory> productCategory = new ArrayList<>();

    public static ProductCategoryDaoJDBC getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDaoJDBC();
        }
        return instance;
    }

    @Override
    public void add(ProductCategory category) {
        String query = "";
        ConnectionUtil.executeQuery(query);
    }

    @Override
    public void remove(int id) {
        String query = "";
        ConnectionUtil.executeQuery(query);
    }

    @Override
    public ProductCategory find(int id) {
        return getAll().stream().filter(productCategory -> productCategory.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<ProductCategory> getAll() {
        if (productCategory.isEmpty()) {
            productCategory = fetchAll();
        }
        return productCategory;
    }

    private ProductCategory createNewInstanceFromDB(ResultSet resultSet) throws SQLException {
        ProductCategory productCategory = new ProductCategory(
                resultSet.getString("name"),
                resultSet.getString("department"),
                resultSet.getString("description"));

        productCategory.setId(resultSet.getInt("id"));
        return productCategory;
    }

    private List<ProductCategory> fetchAll() {
        List<ProductCategory> resultList = new ArrayList<>();

        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM product_category");
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
