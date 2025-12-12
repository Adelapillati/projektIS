package dao_package;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import models.Menu;

public interface MenuDao {
	
	Menu create(Menu menu) throws SQLException;
	Optional<Menu> findById(Long menu_id) throws SQLException;
	List<Menu> findAll() throws SQLException;
	Menu update(Menu menu)throws SQLException;
	void delete(Long menu_id) throws SQLException;

}