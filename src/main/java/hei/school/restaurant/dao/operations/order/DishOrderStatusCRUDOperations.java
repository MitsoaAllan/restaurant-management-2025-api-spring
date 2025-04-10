package hei.school.restaurant.dao.operations.order;

import hei.school.restaurant.dao.DataSource;
import hei.school.restaurant.dao.mapper.order.DishOrderStatusMapper;
import hei.school.restaurant.dao.operations.CRUDOperations;
import hei.school.restaurant.model.order.DishOrderStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class DishOrderStatusCRUDOperations implements CRUDOperations<DishOrderStatus> {
    private final DataSource dataSource;
    private final DishOrderStatusMapper dishOrderStatusMapper;

    @Override
    public List<DishOrderStatus> getAll(Integer page, Integer size) {
        return List.of();
    }

    @Override
    public List<DishOrderStatus> saveAll(List<DishOrderStatus> items) {
        return List.of();
    }

    public List<DishOrderStatus> findByIdDishOrder(int idDish,int idOrder) {
        List<DishOrderStatus> statusList = new ArrayList<>();
        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT dos.id_dish, dos.id_order, status, created_datetime " +
                            "FROM dish_order_status dos " +
                            "JOIN dish_order d " +
                            "ON dos.id_dish = d.id_dish " +
                            "AND dos.id_order = d.id_order " +
                            "WHERE dos.id_dish = ? AND dos.id_order = ?");)
        {
            ps.setInt(1, idDish);
            ps.setInt(2, idOrder);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    DishOrderStatus status = dishOrderStatusMapper.apply(rs);
                    statusList.add(status);
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return statusList;
    }
}
