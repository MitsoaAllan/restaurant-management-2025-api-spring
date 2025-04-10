package hei.school.restaurant.endpoint;


import hei.school.restaurant.endpoint.mapper.OrderRestMapper;
import hei.school.restaurant.endpoint.rest.OrderRest;
import hei.school.restaurant.model.order.Order;
import hei.school.restaurant.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}
