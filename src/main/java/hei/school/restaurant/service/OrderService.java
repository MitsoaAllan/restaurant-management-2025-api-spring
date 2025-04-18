package hei.school.restaurant.service;

import hei.school.restaurant.dao.operations.order.DishOrderCRUDOperations;
import hei.school.restaurant.dao.operations.order.DishOrderStatusCRUDOperations;
import hei.school.restaurant.dao.operations.order.OrderCRUDOperations;
import hei.school.restaurant.dao.operations.order.OrderStatusCRUDOperations;
import hei.school.restaurant.model.order.DishOrder;
import hei.school.restaurant.model.order.DishOrderStatus;
import hei.school.restaurant.model.order.Order;
import hei.school.restaurant.model.order.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired private OrderCRUDOperations orderCRUDOperations;
    @Autowired private DishOrderCRUDOperations dishOrderCRUDOperations;
    @Autowired private DishOrderStatusCRUDOperations dishOrderStatusCRUDOperations;
    @Autowired private OrderStatusCRUDOperations orderStatusCRUDOperations;

    public Order getDishByReference(String reference) {
        return orderCRUDOperations.findByReference(reference);
    }

    public Order updateDishOrder(String reference, List<DishOrder> dishOrdersToUpdate) {
        return orderCRUDOperations.saveDishes(dishOrdersToUpdate,reference);
    }

    public DishOrderStatus updateStatus(String reference,int idDish, DishOrderStatus dishOrderStatus) {
        Order order = orderCRUDOperations.findByReference(reference);
        List<DishOrder> dishOrders = dishOrderCRUDOperations.findByReference(reference);
        DishOrder dishOrder = dishOrders.stream()
                .filter(di-> di.getDish().getId() == idDish)
                .toList().getFirst();
        dishOrder.getStatusList().add(dishOrderStatus);
        return dishOrderStatusCRUDOperations.saveDishOrderStatus(dishOrderStatus,order.getId(),idDish);
    }

    public Order saveOrder(String reference) {
        System.out.println(orderCRUDOperations.findByReference(reference));
        return orderCRUDOperations.save(reference);
    }
}
