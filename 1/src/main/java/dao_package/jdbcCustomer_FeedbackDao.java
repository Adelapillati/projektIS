package dao_package;

import dbpackage.DBConnection;
import pacakge_modelet.Customer_Feedback;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class jdbcCustomer_FeedbackDao implements Customer_FeedbackDao {

    @Override
    public Customer_Feedback create(Customer_Feedback customer_feedback) throws SQLException {

        String sql = "INSERT INTO customer_feedbacks (invoice_id, rate, tip_amount) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setLong(1, customer_feedback.getInvoice_id());
            ps.setInt(2, customer_feedback.getRate());
            ps.setLong(3, customer_feedback.getTip_amount());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    customer_feedback.setFeedback_id(rs.getLong(1));
                }
            }
        }
        return customer_feedback;
    }

    @Override
    public Optional<Customer_Feedback> findById(Long feedback_id) throws SQLException {

        String sql = "SELECT feedback_id, invoice_id, rate, tip_amount " +
                     "FROM customer_feedbacks WHERE feedback_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, feedback_id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Customer_Feedback feedback = new Customer_Feedback(
                            rs.getLong("feedback_id"),
                            rs.getLong("invoice_id"),
                            rs.getInt("rate"),
                            rs.getLong("tip_amount")
                    );
                    return Optional.of(feedback);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Customer_Feedback> findAll() throws SQLException {

        List<Customer_Feedback> feedbacks = new ArrayList<>();
        String sql = "SELECT feedback_id, invoice_id, rate, tip_amount FROM customer_feedbacks";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                feedbacks.add(new Customer_Feedback(
                        rs.getLong("feedback_id"),
                        rs.getLong("invoice_id"),
                        rs.getInt("rate"),
                        rs.getLong("tip_amount")
                ));
            }
        }
        return feedbacks;
    }

    @Override
    public Customer_Feedback update(Customer_Feedback customer_feedback) throws SQLException {

        String sql = "UPDATE customer_feedbacks " +
                     "SET invoice_id = ?, rate = ?, tip_amount = ? " +
                     "WHERE feedback_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, customer_feedback.getInvoice_id());
            ps.setInt(2, customer_feedback.getRate());
            ps.setLong(3, customer_feedback.getTip_amount());
            ps.setLong(4, customer_feedback.getFeedback_id());

            ps.executeUpdate();
        }
        return customer_feedback;
    }

    @Override
    public void delete(Long feedback_id) throws SQLException {

        String sql = "DELETE FROM customer_feedbacks WHERE feedback_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, feedback_id);
            ps.executeUpdate();
        }
    }
}