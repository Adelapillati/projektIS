package dao_package;
import models.Invoice;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface InvoiceDao {
	
	Invoice create(Invoice invoice) throws SQLException;
	Optional<Invoice> findById(Long invoice_id) throws SQLException;
	List<Invoice> findAll() throws SQLException;
	Invoice update(Invoice invoice) throws SQLException;

}