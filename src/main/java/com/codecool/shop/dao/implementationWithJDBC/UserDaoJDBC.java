package com.codecool.shop.dao.implementationWithJDBC;

import com.codecool.shop.config.ConnectionUtil;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoJDBC {
    private static UserDaoJDBC instance = null;

    private UserDaoJDBC() {
    }

    public static UserDaoJDBC getInstance() {
        if (instance == null) {
            instance = new UserDaoJDBC();
        }
        return instance;
    }

    public void add(String firstName, String lastName, String email, String password) {

        String query = String.format("INSERT INTO users(first_name, last_name, email, hashed_password) VALUES ('%s', '%s', '%s', '%s')",
                firstName, lastName, email, password);
        ConnectionUtil.executeQuery(query);
    }

    public boolean checkIfEmailIsAlreadyUsed(String email) {
        String query = String.format("SELECT email FROM users WHERE email='%s'", email);
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()
        ) {
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getPasswordByEmail(String email) {
        String query = String.format("SELECT hashed_password FROM users WHERE email='%s'", email);
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()
        ) {
            if (resultSet.next()) {
                return resultSet.getString("hashed_password");
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
