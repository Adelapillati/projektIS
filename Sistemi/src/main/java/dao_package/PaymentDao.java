package dao_package;
import models.Payment;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface PaymentDao {

	Payment create(Payment payment) throws SQLException;
	Optional<Payment> findById(Long payment_id) throws SQLException;
	List<Payment> findAll() throws SQLException;
	Payment updaye(Payment payment) throws SQLException;
	void delete(Long payment_id) throws SQLException;
	
}