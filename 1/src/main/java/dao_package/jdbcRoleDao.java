package dao_package;

import pacakge_modelet.Role;
import dbpackage.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class jdbcRoleDao implements RoleDao {

    @Override
    public Role create(Role role) throws SQLException {

        String sql = "INSERT INTO roles (name) VALUES (?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, role.getName());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    role.setRole_id(rs.getLong(1));
                }
            }
        }
        return role;
    }

    @Override
    public Optional<Role> findById(Long role_id) throws SQLException {

        String sql = "SELECT role_id, name FROM roles WHERE role_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, role_id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Role role = new Role(
                            rs.getLong("role_id"),
                            rs.getString("name")
                    );
                    return Optional.of(role);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Role> findAll() throws SQLException {

        List<Role> roles = new ArrayList<>();
        String sql = "SELECT role_id, name FROM roles";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                roles.add(new Role(
                        rs.getLong("role_id"),
                        rs.getString("name")
                ));
            }
        }
        return roles;
    }

    @Override
    public Role update(Role role) throws SQLException {

        String sql = "UPDATE roles SET name = ? WHERE role_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, role.getName());
            ps.setLong(2, role.getRole_id());

            ps.executeUpdate();
        }
        return role;
    }

    @Override
    public void delete(Long role_id) throws SQLException {

        String sql = "DELETE FROM roles WHERE role_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, role_id);
            ps.executeUpdate();
        }
    }
}