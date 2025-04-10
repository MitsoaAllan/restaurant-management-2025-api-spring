package hei.school.restaurant.endpoint.rest;

import com.fasterxml.jackson.databind.annotation.EnumNaming;
import hei.school.restaurant.model.StockMovementType;
import hei.school.restaurant.model.Unit;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class CreateIngredientStockMovement {
    private StockMovementType moveType;
    private Double quantity;
    private Unit unit;
    private LocalDateTime createdDatetime;
}
