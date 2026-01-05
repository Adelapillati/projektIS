package junitservice;

import dao_package.jdbcMenu_ItemDao;  
import dbpackage.DBConnection;
import pacakge_modelet.Menu_Item;
import service_package.Menu_ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

public class Menu_ItemServiceTest {

    private Menu_ItemService itemService;

    @BeforeEach
    void setUp() throws SQLException {

        // 1️⃣ Krijo tabelën menu_items (ose menu_items / menu_items - sipas DB tënde)
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement()) {

            st.execute("""
                CREATE TABLE IF NOT EXISTS menu_items (
                    item_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                    menu_id BIGINT NOT NULL,
                    item_name VARCHAR(255) NOT NULL,
                    item_price BIGINT NOT NULL
                )
            """);

            // 2️⃣ Pastro të dhënat para çdo testi
            st.execute("DELETE FROM menu_items");
        }
        itemService = new Menu_ItemService(new jdbcMenu_ItemDao());
    }

    @Test
    void testCreateListUpdateDeleteMenuItem() throws SQLException {
        Menu_Item it = itemService.createMenuItem(1L, "Coffee", 200L);
        assertTrue(it.getItem_id() > 0);

        assertEquals(1, itemService.getAllMenuItems().size());
        assertEquals(1, itemService.getMenuItemById(1L));

        Menu_Item updated = itemService.updateMenuItem(it.getItem_id(), 2L, "Tea", 150L);
        assertEquals("Tea", updated.getItem_name());
        assertEquals(2L, updated.getMenu_id());

        itemService.deleteMenuItem(it.getItem_id());
        assertTrue(itemService.getMenuItemById(it.getItem_id()).isEmpty());
    }
}