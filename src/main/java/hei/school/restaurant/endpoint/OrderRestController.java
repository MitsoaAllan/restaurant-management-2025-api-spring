package hei.school.restaurant.endpoint;


import hei.school.restaurant.endpoint.mapper.OrderRestMapper;
import hei.school.restaurant.endpoint.rest.OrderRest;
import hei.school.restaurant.model.order.DishOrder;
import hei.school.restaurant.model.order.DishOrderStatus;
import hei.school.restaurant.model.order.Order;
import hei.school.restaurant.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class OrderRestController {
    private final OrderService orderService;
    private final OrderRestMapper orderRestMapper;

    @GetMapping("/orders/{reference}")
    public ResponseEntity<OrderRest> getOrder(@PathVariable String reference){
        Order order = orderService.getDishByReference(reference);
        OrderRest orderRest = orderRestMapper.toRest(order);
        return ResponseEntity.status(HttpStatus.OK).body(orderRest);
    }

    @PutMapping("/orders/{reference}/dishes")
    public ResponseEntity<Object> updateOrder(@PathVariable String reference, @RequestBody List<DishOrder> dishOrdersToUpdate){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.updateDishOrder(reference,dishOrdersToUpdate));
    }

    @PutMapping("/orders/{reference}/dishes/{dishId}")
    public ResponseEntity<Object> updateDishOrderStatus(@PathVariable String reference, @PathVariable int dishId, @RequestBody DishOrderStatus dishOrderStatus){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.updateStatus(reference,dishId,dishOrderStatus));
    }
}
