package dao_package;

import pacakge_modelet.Order_Item;
import dbpackage.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class jdbcOrder_ItemDao implements Order_ItemDao {

    @Override
    public Order_Item create(Order_Item orderItem) throws SQLException {

        String sql = "INSERT INTO order_items (item_id, order_id, quantity, unit_price) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setLong(1, orderItem.getItem_id());
            ps.setLong(2, orderItem.getOrder_id());
            ps.setInt(3, orderItem.getQuantity());
            ps.setLong(4, orderItem.getUnit_price());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    orderItem.setOrder_item_id(rs.getLong(1));
                }
            }
        }
        return orderItem;
    }

    @Override
    public Optional<Order_Item> findById(Long order_item_id) throws SQLException {

        String sql = "SELECT order_item_id, item_id, order_id, quantity, unit_price " +
                     "FROM order_items WHERE order_item_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, order_item_id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Order_Item orderItem = new Order_Item(
                            rs.getLong("order_item_id"),
                            rs.getLong("item_id"),
                            rs.getLong("order_id"),
                            rs.getInt("quantity"),
                            rs.getLong("unit_price")
                    );
                    return Optional.of(orderItem);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Order_Item> findAll() throws SQLException {

        List<Order_Item> list = new ArrayList<>();
        String sql = "SELECT order_item_id, item_id, order_id, quantity, unit_price FROM order_items";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new Order_Item(
                        rs.getLong("order_item_id"),
                        rs.getLong("item_id"),
                        rs.getLong("order_id"),
                        rs.getInt("quantity"),
                        rs.getLong("unit_price")
                ));
            }
        }
        return list;
    }

    @Override
    public List<Order_Item> findByOrderId(Long order_id) throws SQLException {

        List<Order_Item> list = new ArrayList<>();
        String sql = "SELECT order_item_id, item_id, order_id, quantity, unit_price " +
                     "FROM order_items WHERE order_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, order_id);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Order_Item(
                            rs.getLong("order_item_id"),
                            rs.getLong("item_id"),
                            rs.getLong("order_id"),
                            rs.getInt("quantity"),
                            rs.getLong("unit_price")
                    ));
                }
            }
        }
        return list;
    }

    @Override
    public Order_Item update(Order_Item orderItem) throws SQLException {

        String sql = "UPDATE order_items " +
                     "SET item_id = ?, order_id = ?, quantity = ?, unit_price = ? " +
                     "WHERE order_item_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, orderItem.getItem_id());
            ps.setLong(2, orderItem.getOrder_id());
            ps.setInt(3, orderItem.getQuantity());
            ps.setLong(4, orderItem.getUnit_price());
            ps.setLong(5, orderItem.getOrder_item_id());

            ps.executeUpdate();
        }
        return orderItem;
    }

    @Override
    public void delete(Long order_item_id) throws SQLException {

        String sql = "DELETE FROM order_items WHERE order_item_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, order_item_id);
            ps.executeUpdate();
        }
    }
}