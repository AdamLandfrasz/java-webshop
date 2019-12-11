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

public class AddressDaoJDBC {
    private static AddressDaoJDBC instance = null;

    private AddressDaoJDBC() {
    }

    public static AddressDaoJDBC getInstance() {
        if (instance == null) {
            instance = new AddressDaoJDBC();
        }
        return instance;
    }

    public void add(Map<String, String> address) {
        List<String> values = new ArrayList<>();
        for (String key : Order.BILLING_KEYS) {
            values.add("'" + address.get(key) + "'");
        }
        String query = String.format("INSERT INTO billing_address(firstname, lastname, email, address, country, state, zip) VALUES (%s, %s, %s, %s, %s, %s, %s)",
                values.get(0), values.get(1), values.get(2), values.get(3), values.get(4), values.get(5), values.get(6));
        ConnectionUtil.executeQuery(query);
    }

    public int getEntryId(Map<String, String> billingAddress) {
        String query = "SELECT * FROM billing_address WHERE firstname = '" + billingAddress.get("firstName") +
                "' AND lastname = '" + billingAddress.get("lastName") +
                "' AND email = '" + billingAddress.get("email") +
                "' AND address = '" + billingAddress.get("address") +
                "' AND country = '" + billingAddress.get("country") +
                "' AND state = '" + billingAddress.get("state") +
                "' AND zip = '" + billingAddress.get("zip") + "'";
        Map<String, String> result = fetchOne(query);
        return result != null ? Integer.parseInt(result.get("id")) : 0;
    }

    private Map<String, String> fetchOne(String query) {
        Map<String, String> result = null;
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()
        ) {
            if (resultSet.next()) {
                result = createNewAddressMap(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Map<String, String> createNewAddressMap(ResultSet resultSet) throws SQLException {
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("id", resultSet.getString("id"));
        for (String key : Order.BILLING_KEYS) {
            resultMap.put(key, resultSet.getString(key));
        }
        return resultMap;
    }
}
