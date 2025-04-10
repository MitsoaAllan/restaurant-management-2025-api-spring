package hei.school.restaurant.model.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Order {
    private int id;
    private String reference;
    private List<DishOrder> dishes;
    private List<OrderStatus> statusList;

    public Status getActualStatus() {
        return statusList.stream()
                .max(Comparator.comparing(OrderStatus::getCreatedDatetime))
                .map(OrderStatus::getStatus)
                .orElse(Status.CREATED);
    }

    public double getTotalAmount(){
        return dishes.stream()
                .map(d->d.getQuantity() * d.getDish().getPrice())
                .reduce(0.0, Double::sum);
    }
}
