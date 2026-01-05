package dao_package;
import pacakge_modelet.Menu_Item;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public interface Menu_ItemDao {
	
	Menu_Item create (Menu_Item menu_item) throws SQLException;
	Optional<Menu_Item> findById(Long menu_item_id) throws SQLException;
	List<Menu_Item> findAll() throws SQLException;
	List<Menu_Item> findByMenuId(Long menu_id) throws SQLException;
	Menu_Item update(Menu_Item menu_item) throws SQLException;
	void delete(Long menu_item_id) throws SQLException;
	
}
