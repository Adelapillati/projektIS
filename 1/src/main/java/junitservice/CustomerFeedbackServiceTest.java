package junitservice;

import dao_package.Customer_FeedbackDao;
import dbpackage.DBConnection;
import pacakge_modelet.Customer_Feedback;
import service_package.Customer_FeedbackService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerFeedbackServiceTest {

    private Customer_FeedbackService feedbackService;

    @BeforeEach
    void setUp() throws SQLException {
        // 1) krijo tabelën nëse s’është krijuar
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement()) {

            st.execute("""
                CREATE TABLE IF NOT EXISTS customer_feedbacks (
                    feedback_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                    invoice_id BIGINT NOT NULL,
                    rate INT NOT NULL,
                    tip_amount BIGINT NOT NULL
                )
            """);

            // 2) pastro të dhënat para çdo testi
            st.execute("DELETE FROM customer_feedbacks");
        }

        // 3) inicializo DAO + Service
        // ⚠️ NËSE klasa jote quhet ndryshe, ndrysho vetëm këtë rresht:
        // p.sh. new JdbcCustomer_FeedbackDao() ose new jdbcCustomer_FeedbackDao()
        Customer_FeedbackDao dao = new dao_package.jdbcCustomer_FeedbackDao();
        // nëse ti e ke me j të vogël:
        // Customer_FeedbackDao dao = new dao_package.jdbcCustomer_FeedbackDao();

        feedbackService = new Customer_FeedbackService(dao);
    }

    @Test
    void testCreateAndFindById() throws SQLException {
        Customer_Feedback created = feedbackService.createFeedback(100L, 5, 200L);
        assertTrue(created.getFeedback_id() > 0);

        Optional<Customer_Feedback> found = feedbackService.getFeedbackById(created.getFeedback_id());
        assertTrue(found.isPresent());
        assertEquals(100L, found.get().getInvoice_id());
        assertEquals(5, found.get().getRate());
        assertEquals(200L, found.get().getTip_amount());
    }

    @Test
    void testFindAll() throws SQLException {
        feedbackService.createFeedback(1L, 4, 0L);
        feedbackService.createFeedback(2L, 5, 50L);

        List<Customer_Feedback> all = feedbackService.getAllFeedbacks();
        assertEquals(2, all.size());
    }

    @Test
    void testUpdate() throws SQLException {
        Customer_Feedback created = feedbackService.createFeedback(10L, 3, 0L);

        Customer_Feedback updated = feedbackService.updateFeedback(
                created.getFeedback_id(),
                20L,
                4,
                100L
        );

        assertEquals(created.getFeedback_id(), updated.getFeedback_id());
        assertEquals(20L, updated.getInvoice_id());
        assertEquals(4, updated.getRate());
        assertEquals(100L, updated.getTip_amount());

        Optional<Customer_Feedback> found = feedbackService.getFeedbackById(created.getFeedback_id());
        assertTrue(found.isPresent());
        assertEquals(20L, found.get().getInvoice_id());
        assertEquals(4, found.get().getRate());
        assertEquals(100L, found.get().getTip_amount());
    }

    @Test
    void testDelete() throws SQLException {
        Customer_Feedback created = feedbackService.createFeedback(99L, 5, 10L);

        feedbackService.deleteFeedback(created.getFeedback_id());

        Optional<Customer_Feedback> found = feedbackService.getFeedbackById(created.getFeedback_id());
        assertTrue(found.isEmpty());
    }
}