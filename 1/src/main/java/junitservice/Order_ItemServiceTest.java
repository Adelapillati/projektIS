package junitservice;

import dao_package.jdbcOrder_ItemDao; 
import dbpackage.DBConnection;
import pacakge_modelet.Order_Item;
import service_package.Order_ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

public class Order_ItemServiceTest {

    private Order_ItemService orderItemService;

    @BeforeEach
    void setUp() throws SQLException {

        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement()) {

            st.execute("""
                CREATE TABLE IF NOT EXISTS order_items (
                    order_item_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                    item_id BIGINT NOT NULL,
                    order_id BIGINT NOT NULL,
                    quantity INT NOT NULL,
                    unit_price BIGINT NOT NULL
                )
            """);

            st.execute("DELETE FROM order_items");
        }
 
        orderItemService = new Order_ItemService(new jdbcOrder_ItemDao());
    }

    @Test
    void testCreateListUpdateDeleteOrderItem() throws SQLException {
        Order_Item oi = orderItemService.createOrderItem(1L, 1L, 2, 500L);
        assertTrue(oi.getOrder_item_id() > 0);

        assertEquals(1, orderItemService.getAllOrderItems().size());
        assertEquals(1, orderItemService.getOrderItemsByOrderId(1L).size());

        Order_Item updated = orderItemService.updateOrderItem(oi.getOrder_item_id(), 2L, 2L, 3, 600L);
        assertEquals(3, updated.getQuantity());

        orderItemService.deleteOrderItem(oi.getOrder_item_id());
        assertTrue(orderItemService.getOrderItemById(oi.getOrder_item_id()).isEmpty());
    }

}
