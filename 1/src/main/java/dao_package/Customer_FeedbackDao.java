package dao_package;
import pacakge_modelet.Customer_Feedback;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Customer_FeedbackDao {
	
	Customer_Feedback create (Customer_Feedback customer_feedback) throws SQLException;
	Optional<Customer_Feedback> findById(Long customer_feedback_id) throws SQLException;
	List<Customer_Feedback> findAll() throws SQLException;
	Customer_Feedback update (Customer_Feedback customer_feedback) throws SQLException;
	void delete(Long customer_feedback_id) throws SQLException;

}
