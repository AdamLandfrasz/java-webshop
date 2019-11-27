package com.codecool.shop.dao.implementationWithJDBC;

import com.codecool.shop.config.ConnectionUtil;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementationWithList.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementationWithList.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Supplier;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SupplierDaoJDBC implements SupplierDao {
    private static SupplierDaoJDBC instance = null;

    private SupplierDaoJDBC() {
    }

    public static SupplierDaoJDBC getInstance() {
        if (instance == null) {
            instance = new SupplierDaoJDBC();
        }
        return instance;
    }

    @Override
    public void add(Supplier supplier) {

    }

    @Override
    public Supplier find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Supplier> getAll() {
        ;
    }

    private Supplier createNewInstanceFromDB(ResultSet resultSet) throws SQLException {
        Supplier supplier = new Supplier(
                resultSet.getString("name"),
                resultSet.getString("description"));

        supplier.setId(resultSet.getInt("id"));
        return supplier;
    }

    private List<Supplier> getResults(String query) {
        List<Supplier> resultList = new ArrayList<>();

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
