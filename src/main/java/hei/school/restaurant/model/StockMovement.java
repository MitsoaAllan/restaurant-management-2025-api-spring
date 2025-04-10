package hei.school.restaurant.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class StockMovement {
    private int id;
    private Ingredient ingredient;
    private StockMovementType moveType;
    private Double quantity;
    private Unit unit;
    private LocalDateTime createdDatetime;

    public StockMovement(StockMovementType moveType,Double quantity, Unit unit, LocalDateTime createdDatetime) {
        this.moveType = moveType;
        this.quantity = quantity;
        this.unit = unit;
        this.createdDatetime = createdDatetime;
    }
}
