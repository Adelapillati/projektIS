package junitservice;

import dao_package.jdbcInvoiceDao;
import dbpackage.DBConnection;
import pacakge_modelet.Invoice;
import service_package.InvoiceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

public class InvoiceServiceTest {

    private InvoiceService invoiceService;

    @BeforeEach
    void setUp() throws SQLException {

//Krijimi i tabeles invoice nqs nuk ekziston
        try (Connection conn = DBConnection.getConnection();
                 Statement st = conn.createStatement()) {

                st.execute("""
                    CREATE TABLE IF NOT EXISTS invoices (
                        invoice_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        order_id BIGINT NOT NULL,
                        fiscal_code VARCHAR(255) NOT NULL,
                        date VARCHAR(50) NOT NULL
                    )
                """);
                st.execute("DELETE FROM invoices");
            }
        invoiceService = new InvoiceService(new jdbcInvoiceDao());
    }

    @Test
    void testCreateUpdateDeleteInvoice() throws SQLException {
        Invoice inv = invoiceService.createInvoice(1L, "FISC123", "2026-01-05");
        assertTrue(inv.getInvoice_id() > 0);

        Invoice updated = invoiceService.updateInvoice(inv.getInvoice_id(), 2L, "FISC999", "2026-01-06");
        assertEquals("FISC999", updated.getFiscal_code());

        invoiceService.deleteInvoice(inv.getInvoice_id());
        assertTrue(invoiceService.getInvoiceById(inv.getInvoice_id()).isEmpty());
    }

}
