package dao_package;

import pacakge_modelet.Employee;
import dbpackage.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class jdbcEmployeeDao implements EmployeeDao {

    @Override
    public Employee create(Employee employee) throws SQLException {

        String sql = "INSERT INTO employees (role_id, zone_id, table_id, unique_code, full_name) " +
                     "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setLong(1, employee.getRole_id());
            ps.setLong(2, employee.getZone_id());
            ps.setLong(3, employee.getTable_id());
            ps.setLong(4, employee.getUnique_code());
            ps.setString(5, employee.getFull_name());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    employee.setEmployee_id(rs.getLong(1));
                }
            }
        }
        return employee;
    }

    @Override
    public Optional<Employee> findById(Long employee_id) throws SQLException {

        String sql = "SELECT employee_id, role_id, zone_id, table_id, unique_code, full_name " +
                     "FROM employees WHERE employee_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, employee_id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Employee employee = new Employee();
                    employee.setEmployee_id(rs.getLong("employee_id"));
                    employee.setRole_id(rs.getLong("role_id"));
                    employee.setZone_id(rs.getLong("zone_id"));
                    employee.setTable_id(rs.getLong("table_id"));
                    employee.setUnique_code(rs.getLong("unique_code"));
                    employee.setFull_name(rs.getString("full_name"));

                    return Optional.of(employee);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Employee> findAll() throws SQLException {

        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT employee_id, role_id, zone_id, table_id, unique_code, full_name FROM employees";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Employee employee = new Employee();
                employee.setEmployee_id(rs.getLong("employee_id"));
                employee.setRole_id(rs.getLong("role_id"));
                employee.setZone_id(rs.getLong("zone_id"));
                employee.setTable_id(rs.getLong("table_id"));
                employee.setUnique_code(rs.getLong("unique_code"));
                employee.setFull_name(rs.getString("full_name"));

                employees.add(employee);
            }
        }
        return employees;
    }

    @Override
    public List<Employee> findByRoleId(Long role_id) throws SQLException {

        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT employee_id, role_id, zone_id, table_id, unique_code, full_name " +
                     "FROM employees WHERE role_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, role_id);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Employee employee = new Employee();
                    employee.setEmployee_id(rs.getLong("employee_id"));
                    employee.setRole_id(rs.getLong("role_id"));
                    employee.setZone_id(rs.getLong("zone_id"));
                    employee.setTable_id(rs.getLong("table_id"));
                    employee.setUnique_code(rs.getLong("unique_code"));
                    employee.setFull_name(rs.getString("full_name"));

                    employees.add(employee);
                }
            }
        }
        return employees;
    }

    @Override
    public Employee update(Employee employee) throws SQLException {

        String sql = "UPDATE employees " +
                     "SET role_id = ?, zone_id = ?, table_id = ?, unique_code = ?, full_name = ? " +
                     "WHERE employee_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, employee.getRole_id());
            ps.setLong(2, employee.getZone_id());
            ps.setLong(3, employee.getTable_id());
            ps.setLong(4, employee.getUnique_code());
            ps.setString(5, employee.getFull_name());
            ps.setLong(6, employee.getEmployee_id());

            ps.executeUpdate();
        }
        return employee;
    }

    @Override
    public void delete(Long employee_id) throws SQLException {

        String sql = "DELETE FROM employees WHERE employee_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, employee_id);
            ps.executeUpdate();
        }
    }
}