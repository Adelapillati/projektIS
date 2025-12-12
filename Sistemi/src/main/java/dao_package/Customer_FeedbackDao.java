package dao_package;

import models.Customer_feedback;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Customer_FeedbackDao {
	
	Customer_feedback create (Customer_feedback customer_feedback) throws SQLException;
	Optional<Customer_feedback> findById(Long customer_feedback_id) throws SQLException;
	List<Customer_feedback> findAll() throws SQLException;
	Customer_feedback update (Customer_feedback customer_feedback) throws SQLException;
	void delete(Long customer_feedback_id) throws SQLException;

}