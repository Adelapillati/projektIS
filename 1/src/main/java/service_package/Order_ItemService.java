package service_package;

import dao_package.Order_ItemDao;
import pacakge_modelet.Order_Item;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class Order_ItemService {

    private final Order_ItemDao orderItemDao;

    public Order_ItemService(Order_ItemDao orderItemDao) {
        this.orderItemDao = orderItemDao;
    }

    public Order_Item createOrderItem(Long itemId, Long orderId, Integer quantity, Long unitPrice) throws SQLException {
        if (itemId == null || itemId <= 0) throw new IllegalArgumentException("item_id duhet > 0");
        if (orderId == null || orderId <= 0) throw new IllegalArgumentException("order_id duhet > 0");
        if (quantity == null || quantity <= 0) throw new IllegalArgumentException("quantity duhet > 0");
        if (unitPrice == null || unitPrice < 0) throw new IllegalArgumentException("unit_price mund te jete negativ");

        Order_Item oi = new Order_Item(0L, itemId, orderId, quantity, unitPrice);
        return orderItemDao.create(oi);
    }

    public Optional<Order_Item> getOrderItemById(Long orderItemId) throws SQLException {
        if (orderItemId == null || orderItemId <= 0) throw new IllegalArgumentException("order_item_id duhet > 0");
        return orderItemDao.findById(orderItemId);
    }

    public List<Order_Item> getAllOrderItems() throws SQLException {
        return orderItemDao.findAll();
    }

    public List<Order_Item> getOrderItemsByOrderId(Long orderId) throws SQLException {
        if (orderId == null || orderId <= 0) throw new IllegalArgumentException("order_id duhet > 0");
        return orderItemDao.findByOrderId(orderId);
    }

    public Order_Item updateOrderItem(Long orderItemId, Long itemId, Long orderId, Integer quantity, Long unitPrice) throws SQLException {
        if (orderItemId == null || orderItemId <= 0) throw new IllegalArgumentException("order_item_id duhet > 0");

        Order_Item oi = orderItemDao.findById(orderItemId)
                .orElseThrow(() -> new IllegalArgumentException("Order_Item me id " + orderItemId + " nuk u gjet"));

        if (itemId == null || itemId <= 0) throw new IllegalArgumentException("item_id duhet > 0");
        if (orderId == null || orderId <= 0) throw new IllegalArgumentException("order_id duhet > 0");
        if (quantity == null || quantity <= 0) throw new IllegalArgumentException("quantity duhet > 0");
        if (unitPrice == null || unitPrice < 0) throw new IllegalArgumentException("unit_price nuk mund te jete negativ");

        oi.setItem_id(itemId);
        oi.setOrder_id(orderId);
        oi.setQuantity(quantity);
        oi.setUnit_price(unitPrice);

        return orderItemDao.update(oi);
    }

    public void deleteOrderItem(Long orderItemId) throws SQLException {
        if (orderItemId == null || orderItemId <= 0) throw new IllegalArgumentException("order_item_id duhet > 0");
        orderItemDao.delete(orderItemId);
    }

}
