package service_package;

import dao_package.InvoiceDao;
import pacakge_modelet.Invoice;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class InvoiceService {

    private final InvoiceDao invoiceDao;

    public InvoiceService(InvoiceDao invoiceDao) {
        this.invoiceDao = invoiceDao;
    }

    public Invoice createInvoice(Long orderId, String fiscalCode, String date) throws SQLException {
        if (orderId == null || orderId <= 0) throw new IllegalArgumentException("order_id duhet > 0");
        if (fiscalCode == null || fiscalCode.isBlank()) throw new IllegalArgumentException("fiscal_code nuk mund të jetë bosh");
        if (date == null || date.isBlank()) throw new IllegalArgumentException("date nuk mund të jetë bosh");

        Invoice inv = new Invoice(0L, orderId, fiscalCode, date);
        return invoiceDao.create(inv);
    }

    public Optional<Invoice> getInvoiceById(Long invoiceId) throws SQLException {
        if (invoiceId == null || invoiceId <= 0) throw new IllegalArgumentException("invoice_id duhet > 0");
        return invoiceDao.findById(invoiceId);
    }

    public List<Invoice> getAllInvoices() throws SQLException {
        return invoiceDao.findAll();
    }

    public Invoice updateInvoice(Long invoiceId, Long orderId, String fiscalCode, String date) throws SQLException {
        if (invoiceId == null || invoiceId <= 0) throw new IllegalArgumentException("invoice_id duhet > 0");

        Invoice inv = invoiceDao.findById(invoiceId)
                .orElseThrow(() -> new IllegalArgumentException("Invoice me id " + invoiceId + " nuk u gjet"));

        if (orderId == null || orderId <= 0) throw new IllegalArgumentException("order_id duhet > 0");
        if (fiscalCode == null || fiscalCode.isBlank()) throw new IllegalArgumentException("fiscal_code nuk mund të jetë bosh");
        if (date == null || date.isBlank()) throw new IllegalArgumentException("date nuk mund të jetë bosh");

        inv.setOrder_id(orderId);
        inv.setFiscal_code(fiscalCode);
        inv.setDate(date);

        return invoiceDao.update(inv);
    }

    public void deleteInvoice(Long invoiceId) throws SQLException {
        if (invoiceId == null || invoiceId <= 0) throw new IllegalArgumentException("invoice_id duhet > 0");
        invoiceDao.delete(invoiceId);
    }
}