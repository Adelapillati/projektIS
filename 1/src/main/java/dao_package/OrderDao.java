package dao_package;
import pacakge_modelet.Order;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface OrderDao {
	
	Order create(Order order) throws SQLException;
	Optional<Order> findById(Long order_id) throws SQLException;
	List<Order> findAll() throws SQLException;
	Order update(Order order) throws SQLException;
	void delete(Long order_id) throws SQLException;

}
