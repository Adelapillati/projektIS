package dao_package;

import pacakge_modelet.Zone;
import dbpackage.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class jdbcZoneDao implements ZoneDao {

    @Override
    public Zone create(Zone zone) throws SQLException {

        String sql = "INSERT INTO zones (location_id, zone_name) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setLong(1, zone.getLocation_id());
            ps.setString(2, zone.getZone_name());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    zone.setZone_id(rs.getLong(1));
                }
            }
        }
        return zone;
    }

    @Override
    public Optional<Zone> findById(Long zone_id) throws SQLException {

        String sql = "SELECT zone_id, location_id, zone_name FROM zones WHERE zone_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, zone_id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Zone zone = new Zone(
                            rs.getLong("zone_id"),
                            rs.getLong("location_id"),
                            rs.getString("zone_name")
                    );
                    return Optional.of(zone);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Zone> findAll() throws SQLException {

        List<Zone> zones = new ArrayList<>();
        String sql = "SELECT zone_id, location_id, zone_name FROM zones";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                zones.add(new Zone(
                        rs.getLong("zone_id"),
                        rs.getLong("location_id"),
                        rs.getString("zone_name")
                ));
            }
        }
        return zones;
    }

    @Override
    public List<Zone> findByLocationId(Long location_id) throws SQLException {

        List<Zone> zones = new ArrayList<>();
        String sql = "SELECT zone_id, location_id, zone_name FROM zones WHERE location_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, location_id);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    zones.add(new Zone(
                            rs.getLong("zone_id"),
                            rs.getLong("location_id"),
                            rs.getString("zone_name")
                    ));
                }
            }
        }
        return zones;
    }

    @Override
    public Zone update(Zone zone) throws SQLException {

        String sql = "UPDATE zones SET location_id = ?, zone_name = ? WHERE zone_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, zone.getLocation_id());
            ps.setString(2, zone.getZone_name());
            ps.setLong(3, zone.getZone_id());

            ps.executeUpdate();
        }
        return zone;
    }

    @Override
    public void delete(Long zone_id) throws SQLException {

        String sql = "DELETE FROM zones WHERE zone_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, zone_id);
            ps.executeUpdate();
        }
    }
}