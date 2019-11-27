package com.codecool.shop.dao.implementationWithJDBC;

import com.codecool.shop.config.ConnectionUtil;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoJDBC implements SupplierDao {
    private static SupplierDaoJDBC instance = null;
    private List<Supplier> suppliers = new ArrayList<>();

    private SupplierDaoJDBC() {
    }

    public static SupplierDaoJDBC getInstance() {
        if (instance == null) {
            instance = new SupplierDaoJDBC();
        }
        return instance;
    }

    private List<Supplier> getSuppliers() {
        if (suppliers.isEmpty()) {
            suppliers = getAll();
        }
        return suppliers;
    }

    @Override
    public void add(Supplier supplier) {
        String query = "";
        ConnectionUtil.executeQuery(query);
    }

    @Override
    public void remove(int id) {
        String query = "";
        ConnectionUtil.executeQuery(query);
    }

    @Override
    public Supplier find(int id) {
        return getSuppliers().stream().filter(supplier -> supplier.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Supplier> getAll() {
        String query = "SELECT * FROM supplier";
        return fetchAll(query);
    }

    private Supplier createNewInstanceFromDB(ResultSet resultSet) throws SQLException {
        Supplier supplier = new Supplier(
                resultSet.getString("name"),
                resultSet.getString("description"));

        supplier.setId(resultSet.getInt("id"));
        return supplier;
    }

    private List<Supplier> fetchAll(String query) {
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
