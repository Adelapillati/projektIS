package dao_package;

import pacakge_modelet.Location;
import dbpackage.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class jdbcLocationDao implements LocationDao {

    @Override
    public Location create(Location location) throws SQLException {

        String sql = "INSERT INTO locations (location_name, location_address) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, location.getLocation_name());
            ps.setString(2, location.getLocation_address());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    location.setLocation_id(rs.getLong(1));
                }
            }
        }
        return location;
    }

    @Override
    public Optional<Location> findById(Long location_id) throws SQLException {

        String sql = "SELECT location_id, location_name, location_address " +
                     "FROM locations WHERE location_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, location_id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Location location = new Location(
                            rs.getLong("location_id"),
                            rs.getString("location_name"),
                            rs.getString("location_address")
                    );
                    return Optional.of(location);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Location> findAll() throws SQLException {

        List<Location> locations = new ArrayList<>();
        String sql = "SELECT location_id, location_name, location_address FROM locations";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                locations.add(new Location(
                        rs.getLong("location_id"),
                        rs.getString("location_name"),
                        rs.getString("location_address")
                ));
            }
        }
        return locations;
    }

    @Override
    public Location update(Location location) throws SQLException {

        String sql = "UPDATE locations SET location_name = ?, location_address = ? WHERE location_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, location.getLocation_name());
            ps.setString(2, location.getLocation_address());
            ps.setLong(3, location.getLocation_id());

            ps.executeUpdate();
        }
        return location;
    }

    @Override
    public void delete(Long location_id) throws SQLException {

        String sql = "DELETE FROM locations WHERE location_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, location_id);
            ps.executeUpdate();
        }
    }
}