package service_package;

import dao_package.OrderDao;
import pacakge_modelet.Order;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class OrderService {

    private final OrderDao orderDao;

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public Order createOrder(Long tableId, Long waiterId, Boolean status, Float discountPercentage) throws SQLException {
        if (tableId == null || tableId <= 0) throw new IllegalArgumentException("table_id duhet > 0");
        if (waiterId == null || waiterId <= 0) throw new IllegalArgumentException("waiter_id duhet > 0");
        if (status == null) throw new IllegalArgumentException("status nuk mund të jetë null");
        if (discountPercentage == null || discountPercentage < 0 || discountPercentage > 100)
            throw new IllegalArgumentException("discount_percentage duhet 0..100");

        Order o = new Order(0L, tableId, waiterId, status, discountPercentage);
        return orderDao.create(o);
    }

    public Optional<Order> getOrderById(Long orderId) throws SQLException {
        if (orderId == null || orderId <= 0) throw new IllegalArgumentException("order_id duhet > 0");
        return orderDao.findById(orderId);
    }

    public List<Order> getAllOrders() throws SQLException {
        return orderDao.findAll();
    }


    public Order updateOrder(Long orderId, Long tableId, Long waiterId, Boolean status, Float discountPercentage) throws SQLException {
        if (orderId == null || orderId <= 0) throw new IllegalArgumentException("order_id duhet > 0");

        Order o = orderDao.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order me id " + orderId + " nuk u gjet"));

        if (tableId == null || tableId <= 0) throw new IllegalArgumentException("table_id duhet > 0");
        if (waiterId == null || waiterId <= 0) throw new IllegalArgumentException("waiter_id duhet > 0");
        if (status == null) throw new IllegalArgumentException("status nuk mund të jetë null");
        if (discountPercentage == null || discountPercentage < 0 || discountPercentage > 100)
            throw new IllegalArgumentException("discount_percentage duhet 0..100");

        o.setTable_id(tableId);
        o.setWaiter_id(waiterId);
        o.setStatus(status);
        o.setDiscount_percentage(discountPercentage);

        return orderDao.update(o);
    }

    public void deleteOrder(Long orderId) throws SQLException {
        if (orderId == null || orderId <= 0) throw new IllegalArgumentException("order_id duhet > 0");
        orderDao.delete(orderId);
    }
}