package hei.school.restaurant.dao.operations.order;

import hei.school.restaurant.dao.DataSource;
import hei.school.restaurant.dao.mapper.order.DishOrderMapper;
import hei.school.restaurant.dao.operations.CRUDOperations;
import hei.school.restaurant.model.order.DishOrder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Repository
public class DishOrderCRUDOperations implements CRUDOperations<DishOrder> {
    private final DataSource dataSource;
    private final DishOrderMapper dishOrderMapper;

    @Override
    public List<DishOrder> getAll(Integer page, Integer size) {
        return List.of();
    }

    @Override
    public List<DishOrder> saveAll(List<DishOrder> items) {
        return List.of();
    }

    public List<DishOrder> findByReference(String reference) {
        List<DishOrder> dishOrders = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT d.id_dish, d.id_order, quantity " +
                            "FROM dish_order d " +
                            "JOIN dish di " +
                            "ON d.id_dish = di.id " +
                            "JOIN \"order\" o " +
                            "ON d.id_order = o.id " +
                            "WHERE reference ILIKE ? "))
        {
            ps.setString(1,"%"+reference+"%");
            try(ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    DishOrder dishOrder = dishOrderMapper.apply(rs);
                    dishOrders.add(dishOrder);
                }
                return dishOrders;
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
