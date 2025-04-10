package hei.school.restaurant.model;

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
public class Ingredient {
    private int id;
    private String name;
    private List<Price> prices;
    private List<StockMovement> stockMovements;

    public Ingredient(int id,String name,List<Price> prices) {
        this.id = id;
        this.name = name;
        this.prices = prices;
    }

    public Double getActualPrice(){
        return prices.stream()
                .max(Comparator.comparing(Price::getCreatedDateTime))
                .map(Price::getAmount)
                .orElse(null);
    }
    public List<Price> addPrices(List<Price> priceList) {
        if (getPrices() == null || getPrices().isEmpty()){
            return prices;
        }
        priceList.forEach(price -> price.setIngredient(this));
        getPrices().addAll(priceList);
        return getPrices();
    }
    public List<StockMovement> addStockMovements(List<StockMovement> stockMovementList) {
        stockMovementList.forEach(stockMovement -> stockMovement.setIngredient(this));
        getStockMovements().addAll(stockMovementList);
        return getStockMovements();
    }
}
