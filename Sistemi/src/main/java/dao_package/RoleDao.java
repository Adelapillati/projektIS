package dao_package;
import  models.Role;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface RoleDao {
	
	Role create(Role role) throws SQLException;
	Optional<Role> findById(Long role_id) throws SQLException;
	List<Role> findAll() throws SQLException;
	Role update(Role role) throws SQLException;
	void delete(Long role_id) throws SQLException;

}