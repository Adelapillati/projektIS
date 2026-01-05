package service_package;

import dao_package.EmployeeDao;
import pacakge_modelet.Employee;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class EmployeeService {

    private final EmployeeDao employeeDao;

    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public Employee createEmployee(Long roleId, Long zoneId, Long tableId, Long uniqueCode, String fullName) throws SQLException {
        if (roleId == null || roleId <= 0) throw new IllegalArgumentException("role_id duhet > 0");
        if (zoneId == null || zoneId <= 0) throw new IllegalArgumentException("zone_id duhet > 0");
        if (tableId == null || tableId <= 0) throw new IllegalArgumentException("table_id duhet > 0");
        if (uniqueCode == null || uniqueCode <= 0) throw new IllegalArgumentException("unique_code duhet > 0");
        if (fullName == null || fullName.isBlank()) throw new IllegalArgumentException("full_name nuk mund të jetë bosh");

        Employee e = new Employee();
        e.setRole_id(roleId);
        e.setZone_id(zoneId);
        e.setTable_id(tableId);
        e.setUnique_code(uniqueCode);
        e.setFull_name(fullName);

        return employeeDao.create(e);
    }

    public Optional<Employee> getEmployeeById(Long employeeId) throws SQLException {
        if (employeeId == null || employeeId <= 0) throw new IllegalArgumentException("employee_id duhet > 0");
        return employeeDao.findById(employeeId);
    }

    public List<Employee> getAllEmployees() throws SQLException {
        return employeeDao.findAll();
    }

    public List<Employee> getEmployeesByRoleId(Long roleId) throws SQLException {
        if (roleId == null || roleId <= 0) throw new IllegalArgumentException("role_id duhet > 0");
        return employeeDao.findByRoleId(roleId);
    }

    public Employee updateEmployee(Long employeeId, Long roleId, Long zoneId, Long tableId, Long uniqueCode, String fullName) throws SQLException {
        if (employeeId == null || employeeId <= 0) throw new IllegalArgumentException("employee_id duhet > 0");

        Employee e = employeeDao.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("Employee me id " + employeeId + " nuk u gjet"));

        if (roleId == null || roleId <= 0) throw new IllegalArgumentException("role_id duhet > 0");
        if (zoneId == null || zoneId <= 0) throw new IllegalArgumentException("zone_id duhet > 0");
        if (tableId == null || tableId <= 0) throw new IllegalArgumentException("table_id duhet > 0");
        if (uniqueCode == null || uniqueCode <= 0) throw new IllegalArgumentException("unique_code duhet > 0");
        if (fullName == null || fullName.isBlank()) throw new IllegalArgumentException("full_name nuk mund të jetë bosh");

        e.setRole_id(roleId);
        e.setZone_id(zoneId);
        e.setTable_id(tableId);
        e.setUnique_code(uniqueCode);
        e.setFull_name(fullName);

        return employeeDao.update(e);
    }

    public void deleteEmployee(Long employeeId) throws SQLException {
        if (employeeId == null || employeeId <= 0) throw new IllegalArgumentException("employee_id duhet > 0");
        employeeDao.delete(employeeId);
    }
}