package hei.school.restaurant.endpoint.mapper;

import hei.school.restaurant.endpoint.rest.DishOrderRest;
import hei.school.restaurant.endpoint.rest.OrderRest;
import hei.school.restaurant.model.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderRestMapper {
    @Autowired private DishOrderRestMapper dishOrderRestMapper;

    public OrderRest toRest(Order order) {
        List<DishOrderRest> dishOrders = order.getDishes().stream()
                .map(dishOrderRestMapper::apply).toList();

        OrderRest orderRest = new OrderRest();
        orderRest.setId(order.getId());
        orderRest.setPrice(order.getTotalAmount());
        orderRest.setReference(order.getReference());
        orderRest.setActualStatus(order.getActualStatus());
        orderRest.setDishOrders(dishOrders);
        return orderRest;
    }
}
