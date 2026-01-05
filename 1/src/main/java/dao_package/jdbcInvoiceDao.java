package dao_package;

import pacakge_modelet.Invoice;
import dbpackage.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class jdbcInvoiceDao implements InvoiceDao {

    @Override
    public Invoice create(Invoice invoice) throws SQLException {

        String sql = "INSERT INTO invoices (order_id, fiscal_code, date) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setLong(1, invoice.getOrder_id());
            ps.setString(2, invoice.getFiscal_code());
            ps.setString(3, invoice.getDate());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    invoice.setInvoice_id(rs.getLong(1));
                }
            }
        }
        return invoice;
    }

    @Override
    public Optional<Invoice> findById(Long invoice_id) throws SQLException {

        String sql = "SELECT invoice_id, order_id, fiscal_code, date " +
                     "FROM invoices WHERE invoice_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, invoice_id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Invoice invoice = new Invoice(
                            rs.getLong("invoice_id"),
                            rs.getLong("order_id"),
                            rs.getString("fiscal_code"),
                            rs.getString("date")
                    );
                    return Optional.of(invoice);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Invoice> findAll() throws SQLException {

        List<Invoice> invoices = new ArrayList<>();
        String sql = "SELECT invoice_id, order_id, fiscal_code, date FROM invoices";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                invoices.add(new Invoice(
                        rs.getLong("invoice_id"),
                        rs.getLong("order_id"),
                        rs.getString("fiscal_code"),
                        rs.getString("date")
                ));
            }
        }
        return invoices;
    }

    @Override
    public Invoice update(Invoice invoice) throws SQLException {

        String sql = "UPDATE invoices " +
                     "SET order_id = ?, fiscal_code = ?, date = ? " +
                     "WHERE invoice_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, invoice.getOrder_id());
            ps.setString(2, invoice.getFiscal_code());
            ps.setString(3, invoice.getDate());
            ps.setLong(4, invoice.getInvoice_id());

            ps.executeUpdate();
        }
        return invoice;
    }
    
    @Override
    public void delete(Long invoice_id) throws SQLException {

        String sql = "DELETE FROM invoices WHERE invoice_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, invoice_id);
            ps.executeUpdate();
        }
    }
}

