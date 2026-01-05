package junitservice;

import dao_package.jdbcZoneDao;
import dbpackage.DBConnection;
import pacakge_modelet.Zone;
import service_package.ZoneService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

public class ZoneServiceTest {

    private ZoneService zoneService;

    @BeforeEach
    void setUp() throws SQLException {
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement()) {

            st.execute("""
                CREATE TABLE IF NOT EXISTS zones (
                    zone_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                    location_id BIGINT NOT NULL,
                    zone_name VARCHAR(255) NOT NULL
                )
            """);
            st.execute("DELETE FROM zones");
        }
        zoneService = new ZoneService(new jdbcZoneDao());
    }

    @Test
    void testCreateListUpdateDeleteZone() throws SQLException {
        Zone z = zoneService.createZone(1L, "Zona A");
        assertTrue(z.getZone_id() > 0);
        assertEquals(1, zoneService.getAllZones().size());
        assertEquals(1, zoneService.getZonesByLocationId(1L).size());

        Zone updated = zoneService.updateZone(z.getZone_id(), 2L, "Zona B");
        assertEquals("Zona B", updated.getZone_name());

        zoneService.deleteZone(z.getZone_id());
        assertTrue(zoneService.getZoneById(z.getZone_id()).isEmpty());
    }

}
