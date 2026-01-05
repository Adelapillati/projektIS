package service_package;

import dao_package.PaymentDao;
import pacakge_modelet.Payment;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PaymentService {

    private final PaymentDao paymentDao;

    public PaymentService(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }

    public Payment createPayment(Long itemId, Long orderId, Integer quantity, Long unitPrice) throws SQLException {
        if (itemId == null || itemId <= 0) throw new IllegalArgumentException("item_id duhet > 0");
        if (orderId == null || orderId <= 0) throw new IllegalArgumentException("order_id duhet > 0");
        if (quantity == null || quantity <= 0) throw new IllegalArgumentException("quantity duhet > 0");
        if (unitPrice == null || unitPrice < 0) throw new IllegalArgumentException("unit_price nuk mund te jete negativ");

        Payment p = new Payment(0L, itemId, orderId, quantity, unitPrice);
        return paymentDao.create(p);
    }

    public Optional<Payment> getPaymentById(Long paymentId) throws SQLException {
        if (paymentId == null || paymentId <= 0) throw new IllegalArgumentException("payment_id duhet > 0");
        return paymentDao.findById(paymentId);
    }

    public List<Payment> getAllPayments() throws SQLException {
        return paymentDao.findAll();
    }

    public Payment updatePayment(Long paymentId, Long itemId, Long orderId, Integer quantity, Long unitPrice) throws SQLException {
        if (paymentId == null || paymentId <= 0) throw new IllegalArgumentException("payment_id duhet > 0");

        Payment p = paymentDao.findById(paymentId)
                .orElseThrow(() -> new IllegalArgumentException("Payment me id " + paymentId + " nuk u gjet"));

        if (itemId == null || itemId <= 0) throw new IllegalArgumentException("item_id duhet > 0");
        if (orderId == null || orderId <= 0) throw new IllegalArgumentException("order_id duhet > 0");
        if (quantity == null || quantity <= 0) throw new IllegalArgumentException("quantity duhet > 0");
        if (unitPrice == null || unitPrice < 0) throw new IllegalArgumentException("unit_price nuk mund te jete negativ");

        p.setItem_id(itemId);
        p.setOrder_id(orderId);
        p.setQuantity(quantity);
        p.setUnit_price(unitPrice);

        return paymentDao.update(p);
    }

    public void deletePayment(Long paymentId) throws SQLException {
        if (paymentId == null || paymentId <= 0) throw new IllegalArgumentException("payment_id duhet > 0");
        paymentDao.delete(paymentId);
    }

}
