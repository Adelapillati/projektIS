package junitservice;

import dao_package.jdbcMenuDao;
import dbpackage.DBConnection;
import pacakge_modelet.Menu;
import service_package.MenuService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class MenuServiceTest {

    private MenuService menuService;

    @BeforeEach
    void setUp() throws SQLException {

        // 1️⃣ Krijo tabelën menus nëse s’ekziston
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement()) {

            st.execute("""
                CREATE TABLE IF NOT EXISTS menus (
                    menu_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                    menu_type VARCHAR(255) NOT NULL
                )
            """);

            // 2️⃣ Pastro të dhënat para çdo testi
            st.execute("DELETE FROM menus");
        }
        
        menuService = new MenuService(new jdbcMenuDao());
    }

    @Test
    void testCreateUpdateDeleteMenu() throws SQLException {
        Menu m = menuService.createMenu("DRINKS");
        assertTrue(m.getMenu_id() > 0);

        Menu updated = menuService.updateMenu(m.getMenu_id(), "FOOD");
        assertEquals("FOOD", updated.getMenu_type());

        menuService.deleteMenu(m.getMenu_id());
        assertTrue(menuService.getMenuById(m.getMenu_id()).isEmpty());
    }
}