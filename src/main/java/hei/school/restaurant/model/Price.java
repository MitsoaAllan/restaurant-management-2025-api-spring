package hei.school.restaurant.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class Price {
    private int id;
    private Ingredient ingredient;
    private Double amount;
    private LocalDateTime createdDateTime;

    public Price(Double amount, LocalDateTime createdDateTime) {
        this.amount = amount;
        this.createdDateTime = createdDateTime;
    }
}
