package dao_package;
import pacakge_modelet.Employee;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface EmployeeDao {
	
	Employee create(Employee employee) throws SQLException;
	Optional<Employee> findById(Long employee_id) throws SQLException;
	List<Employee> findAll() throws SQLException;
	List<Employee> findByRoleId(Long role_id) throws SQLException;
	Employee update(Employee employee) throws SQLException;
	void delete(Long employee_id) throws SQLException;

}
