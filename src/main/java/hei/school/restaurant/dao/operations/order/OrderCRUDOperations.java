package hei.school.restaurant.dao.operations.order;

import hei.school.restaurant.dao.DataSource;
import hei.school.restaurant.dao.mapper.order.OrderMapper;
import hei.school.restaurant.dao.operations.CRUDOperations;
import hei.school.restaurant.model.order.DishOrder;
import hei.school.restaurant.model.order.Order;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
@AllArgsConstructor
public class OrderCRUDOperations implements CRUDOperations<Order> {
    private final DataSource dataSource;
    private final OrderMapper orderMapper;


    @Override
    public List<Order> getAll(Integer page, Integer size) {
        return List.of();
    }

    @Override
    public List<Order> saveAll(List<Order> items) {
        return List.of();
    }

    @SneakyThrows
    public Order findByReference(String reference) {
        String sql = """
                select o.id, o.reference
                from "order" o
                join order_status os on o.id = os.id_order
                where o.reference ilike ?
                """;
        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setString(1, "%"+reference+"%");
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return orderMapper.apply(rs);
                }
                throw new Exception("Order: " + reference + " not found");
            }
        }
    }

    public List<DishOrder> confirmDishOrders(List<DishOrder> dishOrders, int idOrder) {
        String sql = """
                INSERT INTO dish_order_status (id_order, id_dish, status, created_datetime)
                VALUES (?,?,'CONFIRMED'::status,now()) ON CONFLICT (status) DO NOTHING
                """;
        dishOrders.forEach(dishOrder->{
            try(Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql))
            {
                ps.setInt(1,idOrder);
                ps.setInt(2,dishOrder.getDish().getId());
                ps.executeUpdate();
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        });
        return dishOrders;
    }



    public void confirmOrder(int idOrder){
        String sql = """
                INSERT INTO order_status (id_order, status, created_datetime)
                VALUES
                (?,'CONFIRMED'::status,now())
                ON CONFLICT (status)
                DO NOTHING
                """;
        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setInt(1,idOrder);
            ps.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}
