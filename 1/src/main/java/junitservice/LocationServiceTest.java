package junitservice;

import dao_package.jdbcLocationDao;
import dbpackage.DBConnection;
import pacakge_modelet.Location;
import service_package.Location_Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

public class LocationServiceTest {

    private Location_Service locationService;

    @BeforeEach
    void setUp() throws SQLException {
    	
            // 1️⃣ Krijo tabelën locations nëse s’ekziston
            try (Connection conn = DBConnection.getConnection();
                 Statement st = conn.createStatement()) {

                st.execute("""
                    CREATE TABLE IF NOT EXISTS locations (
                        location_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        location_name VARCHAR(255) NOT NULL,
                        location_address VARCHAR(255) NOT NULL
                    )
                """);

                // 2️⃣ Pastro të dhënat para çdo testi
                st.execute("DELETE FROM locations");
            }
        locationService = new Location_Service(new jdbcLocationDao());
    }

    @Test
    void testCreateUpdateDeleteLocation() throws SQLException {
        Location l = locationService.createLocation("Tirana", "Rruga X");
        assertTrue(l.getLocation_id() > 0);

        Location updated = locationService.updateLocation(l.getLocation_id(), "Durres", "Rruga Y");
        assertEquals("Durres", updated.getLocation_name());

        locationService.deleteLocation(l.getLocation_id());
        assertTrue(locationService.getLocationById(l.getLocation_id()).isEmpty());
    }
}