package hei.school.restaurant.endpoint.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class CreateIngredientPrice {
    private Double amount;
    private LocalDateTime createdDatetime;
}
