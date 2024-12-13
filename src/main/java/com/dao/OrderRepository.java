package com.dao;

import com.models.Order;
import com.utils.ConnectionProvider;
import java.sql.SQLException;
import com.utils.DbException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {

    private final ConnectionProvider connectionProvider;

    public OrderRepository(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    public List<Order> findAll() throws DbException {
        String query = "SELECT * FROM orders";
        try (Connection connection = connectionProvider.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            List<Order> orders = new ArrayList<>();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setOrderNumber(rs.getString("order_number"));
                order.setItems(rs.getString("items"));
                order.setTableNumber(rs.getInt("table_number"));
                order.setWaiterName(rs.getString("waiter_name"));
                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            throw new DbException("Error while fetching all orders", e);
        }
    }

    public Order findById(int id) throws DbException {
        String query = "SELECT * FROM orders WHERE id = ?";
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Order order = new Order();
                    order.setId(rs.getInt("id"));
                    order.setOrderNumber(rs.getString("order_number"));
                    order.setItems(rs.getString("items"));
                    order.setTableNumber(rs.getInt("table_number"));
                    order.setWaiterName(rs.getString("waiter_name"));
                    return order;
                }
            }
        } catch (SQLException e) {
            throw new DbException("Error while fetching order by ID", e);
        }
        return null;
    }
}

