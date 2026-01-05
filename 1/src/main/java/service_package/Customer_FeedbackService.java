package service_package;

import dao_package.Customer_FeedbackDao;
import pacakge_modelet.Customer_Feedback;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class Customer_FeedbackService {

    private final Customer_FeedbackDao feedbackDao;

    public Customer_FeedbackService(Customer_FeedbackDao feedbackDao) {
        this.feedbackDao = feedbackDao;
    }

    public Customer_Feedback createFeedback(Long invoiceId, Integer rate, Long tipAmount) throws SQLException {
        if (invoiceId == null || invoiceId <= 0) {
            throw new IllegalArgumentException("Invoice ID duhet te jete > 0");
        }
        if (rate == null || rate < 1 || rate > 5) {
            throw new IllegalArgumentException("Rate duhet te jete nga 1 deri ne 5");
        }
        if (tipAmount == null || tipAmount < 0) {
            throw new IllegalArgumentException("Tip amount nuk mund te jete negativ");
        }

        Customer_Feedback feedback = new Customer_Feedback(0L, invoiceId, rate, tipAmount);
        return feedbackDao.create(feedback);
    }

    public Optional<Customer_Feedback> getFeedbackById(Long feedbackId) throws SQLException {
        if (feedbackId == null || feedbackId <= 0) {
            throw new IllegalArgumentException("Feedback ID duhet te jete > 0");
        }
        return feedbackDao.findById(feedbackId);
    }

    public List<Customer_Feedback> getAllFeedbacks() throws SQLException {
        return feedbackDao.findAll();
    }

    public Customer_Feedback updateFeedback(Long feedbackId, Long invoiceId, Integer rate, Long tipAmount) throws SQLException {
        if (feedbackId == null || feedbackId <= 0) {
            throw new IllegalArgumentException("Feedback ID duhet te jete > 0");
        }

        Optional<Customer_Feedback> existing = feedbackDao.findById(feedbackId);
        if (existing.isEmpty()) {
            throw new IllegalArgumentException("Feedback me id " + feedbackId + " nuk u gjet");
        }

        if (invoiceId == null || invoiceId <= 0) {
            throw new IllegalArgumentException("Invoice ID duhet te jete > 0");
        }
        if (rate == null || rate < 1 || rate > 5) {
            throw new IllegalArgumentException("Rate duhet të jete nga 1 deri ne 5");
        }
        if (tipAmount == null || tipAmount < 0) {
            throw new IllegalArgumentException("Tip amount nuk mund te jete negativ");
        }

        Customer_Feedback feedback = existing.get();
        feedback.setInvoice_id(invoiceId);
        feedback.setRate(rate);
        feedback.setTip_amount(tipAmount);

        return feedbackDao.update(feedback);
    }

    public void deleteFeedback(Long feedbackId) throws SQLException {
        if (feedbackId == null || feedbackId <= 0) {
            throw new IllegalArgumentException("Feedback ID duhet te jete > 0");
        }
        feedbackDao.delete(feedbackId);
    }

}
