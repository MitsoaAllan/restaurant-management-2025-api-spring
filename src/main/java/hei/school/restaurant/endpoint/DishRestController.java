package hei.school.restaurant.endpoint;

import hei.school.restaurant.endpoint.rest.CreateDishIngredient;
import hei.school.restaurant.model.Dish;
import hei.school.restaurant.model.DishIngredient;
import hei.school.restaurant.model.Ingredient;
import hei.school.restaurant.service.DishService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class DishRestController {
    private final DishService dishService;

    @GetMapping("/dishes")
    public ResponseEntity<Object> getDishes(
            @RequestParam(required = false,defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(dishService.getAll(page,size));
    }

    @PutMapping("/dishes/{id}/ingredients")
    public ResponseEntity<Object> updateIngredients(@PathVariable int id, @RequestBody List<CreateDishIngredient> dishIngredients){
        List<DishIngredient> ingredients = dishIngredients.stream()
                .map(ingredient->
                        new DishIngredient(
                            new Ingredient(ingredient.getIngredient().getId(),ingredient.getIngredient().getName()),
                            ingredient.getRequiredQuantity(),
                            ingredient.getUnit())
                ).toList();
        Dish ingredient = dishService.addDishes(id,ingredients);
        return ResponseEntity.status(HttpStatus.OK).body(ingredient);
    }
}
