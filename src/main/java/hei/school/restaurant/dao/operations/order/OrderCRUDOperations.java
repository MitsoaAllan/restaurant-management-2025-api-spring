package hei.school.restaurant.dao.operations.order;

import hei.school.restaurant.dao.DataSource;
import hei.school.restaurant.dao.mapper.order.OrderMapper;
import hei.school.restaurant.dao.operations.CRUDOperations;
import hei.school.restaurant.model.order.Order;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;


@Repository
@RequiredArgsConstructor
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
}
