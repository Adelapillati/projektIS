package junitservice;

import dao_package.jdbcOrderDao;
import dbpackage.DBConnection;
import pacakge_modelet.Order;
import service_package.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

public class OrderServiceTest {

    private OrderService orderService;

    @BeforeEach
    void setUp() throws SQLException {

        // 1️⃣ Krijo tabelën orders nëse s’ekziston
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement()) {

            st.execute("""
                CREATE TABLE IF NOT EXISTS orders (
                    order_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                    table_id BIGINT NOT NULL,
                    waiter_id BIGINT NOT NULL,
                    status BOOLEAN NOT NULL,
                    discount_percentage REAL NOT NULL
                )
            """);

            // 2️⃣ Pastro të dhënat para çdo testi
            st.execute("DELETE FROM orders");
        }
        orderService = new OrderService(new jdbcOrderDao());
    }

    @Test
    void testCreateListUpdateDeleteOrder() throws SQLException {
        Order o = orderService.createOrder(1L, 10L, false, 0f);
        assertTrue(o.getOrder_id() > 0);

        assertEquals(1, orderService.getAllOrders().size());

        Order updated = orderService.updateOrder(o.getOrder_id(), 2L, 11L, true, 10f);
        assertTrue(updated.isStatus());
        assertEquals(10f, updated.getDiscount_percentage());

        orderService.deleteOrder(o.getOrder_id());
        assertTrue(orderService.getOrderById(o.getOrder_id()).isEmpty());
    }
}