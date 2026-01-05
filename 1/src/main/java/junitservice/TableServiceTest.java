package junitservice;

import dao_package.jdbcTableDao;
import dbpackage.DBConnection;
import pacakge_modelet.Table;
import service_package.TableService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

public class TableServiceTest {

    private TableService tableService;

    @BeforeEach
    void setUp() throws SQLException {

        // 1️⃣ Krijo tabelën tables nëse s’ekziston
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement()) {

            st.execute("""
                CREATE TABLE IF NOT EXISTS tables (
                    table_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                    zone_id BIGINT NOT NULL,
                    status BOOLEAN NOT NULL,
                    seats INT NOT NULL
                )
            """);

            // 2️⃣ Pastro të dhënat para çdo testi
            st.execute("DELETE FROM tables");
        }
        tableService = new TableService(new jdbcTableDao());
    }

    @Test
    void testCreateListUpdateDeleteTable() throws SQLException {
        Table t = tableService.createTable(1L, false, 4);
        assertTrue(t.getTable_id() > 0);

        assertEquals(1, tableService.getAllTables().size());
        assertEquals(1, tableService.getTablesByZoneId(1L).size());

        Table updated = tableService.updateTable(t.getTable_id(), 2L, true, 6);
        assertTrue(updated.isStatus());
        assertEquals(6, updated.getSeats());

        tableService.deleteTable(t.getTable_id());
        assertTrue(tableService.getTableById(t.getTable_id()).isEmpty());
    }
}