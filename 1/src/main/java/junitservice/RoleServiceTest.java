package junitservice;

import dao_package.jdbcRoleDao;
import dbpackage.DBConnection;
import pacakge_modelet.Role;
import service_package.RoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class RoleServiceTest {

    private RoleService roleService;

    @BeforeEach
    void setUp() throws SQLException {

        // 1️⃣ Krijo tabelën roles nëse s’ekziston
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement()) {

            st.execute("""
                CREATE TABLE IF NOT EXISTS roles (
                    role_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(255) NOT NULL
                )
            """);

            // 2️⃣ Pastro të dhënat para çdo testi
            st.execute("DELETE FROM roles");
        roleService = new RoleService(new jdbcRoleDao());
    }
    }
    @Test
    void testCreateUpdateDeleteRole() throws SQLException {
        Role r = roleService.createRole("WAITER");
        assertTrue(r.getRole_id() > 0);

        Role updated = roleService.updateRole(r.getRole_id(), "MANAGER");
        assertEquals("MANAGER", updated.getName());

        roleService.deleteRole(r.getRole_id());
        Optional<Role> found = roleService.getRoleById(r.getRole_id());
        assertTrue(found.isEmpty());
    }
}