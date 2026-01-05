package dao_package;

import pacakge_modelet.Menu;
import dbpackage.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class jdbcMenuDao implements MenuDao {

    @Override
    public Menu create(Menu menu) throws SQLException {

        String sql = "INSERT INTO menus (menu_type) VALUES (?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, menu.getMenu_type());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    menu.setMenu_id(rs.getLong(1));
                }
            }
        }
        return menu;
    }

    @Override
    public Optional<Menu> findById(Long menu_id) throws SQLException {

        String sql = "SELECT menu_id, menu_type FROM menus WHERE menu_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, menu_id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Menu menu = new Menu(
                            rs.getLong("menu_id"),
                            rs.getString("menu_type")
                    );
                    return Optional.of(menu);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Menu> findAll() throws SQLException {

        List<Menu> menus = new ArrayList<>();
        String sql = "SELECT menu_id, menu_type FROM menus";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                menus.add(new Menu(
                        rs.getLong("menu_id"),
                        rs.getString("menu_type")
                ));
            }
        }
        return menus;
    }

    @Override
    public Menu update(Menu menu) throws SQLException {

        String sql = "UPDATE menus SET menu_type = ? WHERE menu_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, menu.getMenu_type());
            ps.setLong(2, menu.getMenu_id());

            ps.executeUpdate();
        }
        return menu;
    }

    @Override
    public void delete(Long menu_id) throws SQLException {

        String sql = "DELETE FROM menus WHERE menu_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, menu_id);
            ps.executeUpdate();
        }
    }
}