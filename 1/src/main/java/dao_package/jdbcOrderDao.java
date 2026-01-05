package dao_package;

import pacakge_modelet.Order;
import dbpackage.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class jdbcOrderDao implements OrderDao {

    @Override
    public Order create(Order order) throws SQLException {

        String sql = "INSERT INTO orders (table_id, waiter_id, status, discount_percentage) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setLong(1, order.getTable_id());
            ps.setLong(2, order.getWaiter_id());
            ps.setBoolean(3, order.isStatus());
            ps.setFloat(4, order.getDiscount_percentage());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    order.setOrder_id(rs.getLong(1));
                }
            }
        }
        return order;
    }

    @Override
    public Optional<Order> findById(Long order_id) throws SQLException {

        String sql = "SELECT order_id, table_id, waiter_id, status, discount_percentage " +
                     "FROM orders WHERE order_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, order_id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Order order = new Order(
                            rs.getLong("order_id"),
                            rs.getLong("table_id"),
                            rs.getLong("waiter_id"),
                            rs.getBoolean("status"),
                            rs.getFloat("discount_percentage")
                    );
                    return Optional.of(order);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Order> findAll() throws SQLException {

        List<Order> orders = new ArrayList<>();
        String sql = "SELECT order_id, table_id, waiter_id, status, discount_percentage FROM orders";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                orders.add(new Order(
                        rs.getLong("order_id"),
                        rs.getLong("table_id"),
                        rs.getLong("waiter_id"),
                        rs.getBoolean("status"),
                        rs.getFloat("discount_percentage")
                ));
            }
        }
        return orders;
    }

   
    @Override
    public Order update(Order order) throws SQLException {

        String sql = "UPDATE orders " +
                     "SET table_id = ?, waiter_id = ?, status = ?, discount_percentage = ? " +
                     "WHERE order_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, order.getTable_id());
            ps.setLong(2, order.getWaiter_id());
            ps.setBoolean(3, order.isStatus());
            ps.setFloat(4, order.getDiscount_percentage());
            ps.setLong(5, order.getOrder_id());

            ps.executeUpdate();
        }
        return order;
    }

    @Override
    public void delete(Long order_id) throws SQLException {

        String sql = "DELETE FROM orders WHERE order_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, order_id);
            ps.executeUpdate();
        }
    }
}