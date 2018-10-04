package com.murdock.books.spring.statemachine.guide.example.persist.dal;

import com.murdock.books.spring.statemachine.guide.example.persist.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author weipeng2k 2018年10月04日 下午16:41:28
 */
@Repository("orderDAO")
public class OrderDAOImpl implements OrderDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void createOrder(int id, String state) {
        jdbcTemplate.update("insert into orders values(?, ?)", id, state);
    }

    @Override
    public Order getOrder(int id) {
        Order order = jdbcTemplate.queryForObject("select * from orders where id = ?", new Object[]{id},
                (rs, rowNum) -> {
                    int pk = rs.getInt("id");
                    String state = rs.getString("state");
                    return new Order(pk, state);
                });

        return order;
    }

    @Override
    public int updateOrder(int id, String state) {
        int update = jdbcTemplate.update("update orders " +
                "set state = ? " +
                "where id = ?", state, id);

        return update;
    }

    @Override
    public List<Order> getOrderList() {
        List<Order> orders = jdbcTemplate.query("select * from orders order by id asc", (rs, rowNum) -> {
            int pk = rs.getInt("id");
            String state = rs.getString("state");
            return new Order(pk, state);
        });
        return orders;
    }
}
