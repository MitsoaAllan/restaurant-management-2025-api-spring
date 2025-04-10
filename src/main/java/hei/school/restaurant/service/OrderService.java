package hei.school.restaurant.service;

import hei.school.restaurant.dao.operations.order.OrderCRUDOperations;
import hei.school.restaurant.model.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired private OrderCRUDOperations orderCRUDOperations;

    public Order getDishByReference(String reference) {
        return orderCRUDOperations.findByReference(reference);
    }
}
