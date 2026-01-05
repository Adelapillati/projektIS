package dao_package;

import pacakge_modelet.Table;
import dbpackage.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class jdbcTableDao implements TableDao {

    @Override
    public Table create(Table table) throws SQLException {

        String sql = "INSERT INTO tables (zone_id, status, seats) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setLong(1, table.getZone_id());
            ps.setBoolean(2, table.isStatus());
            ps.setInt(3, table.getSeats());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    table.setTable_id(rs.getLong(1));
                }
            }
        }
        return table;
    }

    @Override
    public Optional<Table> findById(Long table_id) throws SQLException {

        String sql = "SELECT table_id, zone_id, status, seats FROM tables WHERE table_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, table_id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Table table = new Table(
                            rs.getLong("table_id"),
                            rs.getLong("zone_id"),
                            rs.getBoolean("status"),
                            rs.getInt("seats")
                    );
                    return Optional.of(table);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Table> findAll() throws SQLException {

        List<Table> tables = new ArrayList<>();
        String sql = "SELECT table_id, zone_id, status, seats FROM tables";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                tables.add(new Table(
                        rs.getLong("table_id"),
                        rs.getLong("zone_id"),
                        rs.getBoolean("status"),
                        rs.getInt("seats")
                ));
            }
        }
        return tables;
    }

    @Override
    public List<Table> findByZoneId(Long zone_id) throws SQLException {

        List<Table> tables = new ArrayList<>();
        String sql = "SELECT table_id, zone_id, status, seats FROM tables WHERE zone_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, zone_id);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    tables.add(new Table(
                            rs.getLong("table_id"),
                            rs.getLong("zone_id"),
                            rs.getBoolean("status"),
                            rs.getInt("seats")
                    ));
                }
            }
        }
        return tables;
    }

    @Override
    public Table update(Table table) throws SQLException {

        String sql = "UPDATE tables SET zone_id = ?, status = ?, seats = ? WHERE table_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, table.getZone_id());
            ps.setBoolean(2, table.isStatus());
            ps.setInt(3, table.getSeats());
            ps.setLong(4, table.getTable_id());

            ps.executeUpdate();
        }
        return table;
    }

    @Override
    public void delete(Long table_id) throws SQLException {

        String sql = "DELETE FROM tables WHERE table_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, table_id);
            ps.executeUpdate();
        }
    }
}