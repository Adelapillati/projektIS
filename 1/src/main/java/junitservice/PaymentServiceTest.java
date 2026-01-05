package junitservice;

import dao_package.jdbcPaymentDao;
import dbpackage.DBConnection;
import pacakge_modelet.Payment;
import service_package.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentServiceTest {

    private PaymentService paymentService;

    @BeforeEach
    void setUp() throws SQLException {

        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement()) {

            st.execute("""
                CREATE TABLE IF NOT EXISTS payments (
                    payment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                    item_id BIGINT NOT NULL,
                    order_id BIGINT NOT NULL,
                    quantity INT NOT NULL,
                    unit_price BIGINT NOT NULL
                )
            """);

            st.execute("DELETE FROM payments");
        }
        
        paymentService = new PaymentService(new jdbcPaymentDao());
    }

    @Test
    void testCreateListUpdateDeletePayment() throws SQLException {
        Payment p = paymentService.createPayment(1L, 1L, 1, 1000L);
        assertTrue(p.getPayment_id() > 0);

        assertEquals(1, paymentService.getAllPayments().size());
        Payment updated = paymentService.updatePayment(p.getPayment_id(), 2L, 2L, 2, 1500L);
        assertEquals(1500L, updated.getUnit_price());

        paymentService.deletePayment(p.getPayment_id());
        assertTrue(paymentService.getPaymentById(p.getPayment_id()).isEmpty());
    }

}
