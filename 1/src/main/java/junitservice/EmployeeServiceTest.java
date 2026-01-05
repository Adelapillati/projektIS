package junitservice;

import dao_package.jdbcEmployeeDao;
import dbpackage.DBConnection;
import pacakge_modelet.Employee;
import service_package.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceTest {

    private EmployeeService employeeService;

    @BeforeEach
    void setUp() throws SQLException {
    	 // 1️⃣ Krijo tabelën employees nëse s’ekziston
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement()) {

            st.execute("""
                CREATE TABLE IF NOT EXISTS employees (
                    employee_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                    role_id BIGINT NOT NULL,
                    zone_id BIGINT NOT NULL,
                    table_id BIGINT NOT NULL,
                    unique_code BIGINT NOT NULL,
                    full_name VARCHAR(255) NOT NULL
                )
            """);

            // 2️⃣ Pastro të dhënat para çdo testi
            st.execute("DELETE FROM employees");
        employeeService = new EmployeeService(new jdbcEmployeeDao());
    }
    }

    @Test
    void testCreateListUpdateDeleteEmployee() throws SQLException {
        Employee e = employeeService.createEmployee(1L, 1L, 1L, 999L, "John Doe");
        assertTrue(e.getEmployee_id() > 0);

        assertEquals(1, employeeService.getAllEmployees().size());
        assertEquals(1, employeeService.getEmployeesByRoleId(1L).size());

        Employee updated = employeeService.updateEmployee(e.getEmployee_id(), 2L, 2L, 2L, 888L, "Jane Doe");
        assertEquals("Jane Doe", updated.getFull_name());

        employeeService.deleteEmployee(e.getEmployee_id());
        assertTrue(employeeService.getEmployeeById(e.getEmployee_id()).isEmpty());
    }
}