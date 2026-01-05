package dao_package;

import pacakge_modelet.Payment;
import dbpackage.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class jdbcPaymentDao implements PaymentDao {

    @Override
    public Payment create(Payment payment) throws SQLException {

        String sql = "INSERT INTO payments (item_id, order_id, quantity, unit_price) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setLong(1, payment.getItem_id());
            ps.setLong(2, payment.getOrder_id());
            ps.setInt(3, payment.getQuantity());
            ps.setLong(4, payment.getUnit_price());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    payment.setPayment_id(rs.getLong(1));
                }
            }
        }
        return payment;
    }

    @Override
    public Optional<Payment> findById(Long payment_id) throws SQLException {

        String sql = "SELECT payment_id, item_id, order_id, quantity, unit_price " +
                     "FROM payments WHERE payment_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, payment_id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Payment payment = new Payment(
                            rs.getLong("payment_id"),
                            rs.getLong("item_id"),
                            rs.getLong("order_id"),
                            rs.getInt("quantity"),
                            rs.getLong("unit_price")
                    );
                    return Optional.of(payment);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Payment> findAll() throws SQLException {

        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT payment_id, item_id, order_id, quantity, unit_price FROM payments";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                payments.add(new Payment(
                        rs.getLong("payment_id"),
                        rs.getLong("item_id"),
                        rs.getLong("order_id"),
                        rs.getInt("quantity"),
                        rs.getLong("unit_price")
                ));
            }
        }
        return payments;
    }

    @Override
    public Payment update(Payment payment) throws SQLException {

        String sql = "UPDATE payments " +
                     "SET item_id = ?, order_id = ?, quantity = ?, unit_price = ? " +
                     "WHERE payment_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, payment.getItem_id());
            ps.setLong(2, payment.getOrder_id());
            ps.setInt(3, payment.getQuantity());
            ps.setLong(4, payment.getUnit_price());
            ps.setLong(5, payment.getPayment_id());

            ps.executeUpdate();
        }
        return payment;
    }

    @Override
    public void delete(Long payment_id) throws SQLException {

        String sql = "DELETE FROM payments WHERE payment_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, payment_id);
            ps.executeUpdate();
        }
    }
}