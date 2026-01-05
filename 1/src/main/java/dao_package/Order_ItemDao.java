package dao_package;
import pacakge_modelet.Order_Item;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Order_ItemDao {
	
	Order_Item create (Order_Item order_item) throws SQLException;
	Optional<Order_Item> findById(Long order_item_id) throws SQLException;
	List<Order_Item> findAll() throws SQLException;
	List<Order_Item> findByOrderId(Long order_id) throws SQLException;
	Order_Item update(Order_Item order_item) throws SQLException;
	void delete(Long order_item_id) throws SQLException;
	
}

