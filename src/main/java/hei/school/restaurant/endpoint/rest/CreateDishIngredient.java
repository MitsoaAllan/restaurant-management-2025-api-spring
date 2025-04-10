package hei.school.restaurant.endpoint.rest;

import hei.school.restaurant.model.Ingredient;
import hei.school.restaurant.model.Unit;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateDishIngredient {
    private Ingredient ingredient;
    private Double requiredQuantity;
    private Unit unit;
}
