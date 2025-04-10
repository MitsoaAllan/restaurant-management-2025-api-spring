package hei.school.restaurant.dao.operations.order;

import hei.school.restaurant.dao.DataSource;
import hei.school.restaurant.dao.mapper.order.OrderStatusMapper;
import hei.school.restaurant.dao.operations.CRUDOperations;
import hei.school.restaurant.model.order.OrderStatus;
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
public class OrderStatusCRUDOperations implements CRUDOperations<OrderStatus> {
    private final DataSource dataSource;
    private final OrderStatusMapper orderStatusMapper;

    @Override
    public List<OrderStatus> getAll(Integer page, Integer size) {
        return List.of();
    }

    @Override
    public List<OrderStatus> saveAll(List<OrderStatus> items) {
        return List.of();
    }

    public List<OrderStatus> findByIdOrder(int idOrder) {
        List<OrderStatus> orderStatusList = new ArrayList<>();
        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT os.id_order, status, created_datetime " +
                            "FROM order_status os " +
                            "JOIN \"order\" o " +
                            "ON os.id_order = o.id "+
                            "WHERE os.id_order=?");)
        {
            ps.setInt(1, idOrder);
            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    OrderStatus orderStatus = orderStatusMapper.apply(rs);
                    orderStatusList.add(orderStatus);
                }
                return orderStatusList;
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
