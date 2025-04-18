package hei.school.restaurant.endpoint;

import hei.school.restaurant.endpoint.mapper.DishIngredientRestMapper;
import hei.school.restaurant.endpoint.mapper.DishRestMapper;
import hei.school.restaurant.endpoint.rest.CreateDishIngredient;
import hei.school.restaurant.endpoint.rest.DishIngredientRest;
import hei.school.restaurant.endpoint.rest.DishRest;
import hei.school.restaurant.model.Dish;
import hei.school.restaurant.model.DishIngredient;
import hei.school.restaurant.model.Ingredient;
import hei.school.restaurant.service.DishService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class DishRestController {
    private final DishService dishService;
    private final DishRestMapper dishRestMapper;

    @GetMapping("/dishes")
    public ResponseEntity<Object> getDishes(
            @RequestParam(required = false,defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        List<Dish> dishes = dishService.getAll(page, size);
        List<DishRest> dishRests = dishes.stream().map(
                dish -> {
                    DishRest dishRest = dishRestMapper.toRest(dish);
                    return dishRest;
                }
        ).toList();
        return ResponseEntity.status(HttpStatus.OK).body(dishRests);
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
