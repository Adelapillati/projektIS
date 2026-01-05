package dao_package;

import pacakge_modelet.Menu_Item;
import dbpackage.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class jdbcMenu_ItemDao implements Menu_ItemDao {

    @Override
    public Menu_Item create(Menu_Item item) throws SQLException {

        String sql = "INSERT INTO menu_items (menu_id, item_name, item_price) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setLong(1, item.getMenu_id());
            ps.setString(2, item.getItem_name());
            ps.setLong(3, item.getItem_price());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    item.setItem_id(rs.getLong(1));
                }
            }
        }
        return item;
    }

    @Override
    public Optional<Menu_Item> findById(Long item_id) throws SQLException {

        String sql = "SELECT item_id, menu_id, item_name, item_price " +
                     "FROM menu_items WHERE item_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, item_id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Menu_Item item = new Menu_Item(
                            rs.getLong("item_id"),
                            rs.getLong("menu_id"),
                            rs.getString("item_name"),
                            rs.getLong("item_price")
                    );
                    return Optional.of(item);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Menu_Item> findAll() throws SQLException {

        List<Menu_Item> items = new ArrayList<>();
        String sql = "SELECT item_id, menu_id, item_name, item_price FROM menu_items";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                items.add(new Menu_Item(
                        rs.getLong("item_id"),
                        rs.getLong("menu_id"),
                        rs.getString("item_name"),
                        rs.getLong("item_price")
                ));
            }
        }
        return items;
    }

    @Override
    public List<Menu_Item> findByMenuId(Long menu_id) throws SQLException {

        List<Menu_Item> items = new ArrayList<>();
        String sql = "SELECT item_id, menu_id, item_name, item_price " +
                     "FROM menu_items WHERE menu_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, menu_id);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    items.add(new Menu_Item(
                            rs.getLong("item_id"),
                            rs.getLong("menu_id"),
                            rs.getString("item_name"),
                            rs.getLong("item_price")
                    ));
                }
            }
        }
        return items;
    }

    @Override
    public Menu_Item update(Menu_Item item) throws SQLException {

        String sql = "UPDATE menu_items " +
                     "SET menu_id = ?, item_name = ?, item_price = ? " +
                     "WHERE item_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, item.getMenu_id());
            ps.setString(2, item.getItem_name());
            ps.setLong(3, item.getItem_price());
            ps.setLong(4, item.getItem_id());

            ps.executeUpdate();
        }
        return item;
    }

    @Override
    public void delete(Long item_id) throws SQLException {

        String sql = "DELETE FROM menu_items WHERE item_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, item_id);
            ps.executeUpdate();
        }
    }
}