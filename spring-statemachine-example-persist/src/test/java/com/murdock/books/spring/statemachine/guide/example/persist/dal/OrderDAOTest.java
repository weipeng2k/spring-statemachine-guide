package com.murdock.books.spring.statemachine.guide.example.persist.dal;

import com.murdock.books.spring.statemachine.guide.example.persist.DbInitTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author weipeng2k 2018年10月04日 下午17:36:09
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class OrderDAOTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private OrderDAO orderDAO;

    public void create_order() {
        orderDAO.createOrder(1, "PLACED");
        orderDAO.createOrder(2, "PROCESSING");
        orderDAO.createOrder(3, "DELIVERED");
        orderDAO.createOrder(4, "PLACED");
    }

    @Test
    public void get_order() {
        assertNotNull(orderDAO.getOrder(1));
        System.out.println(orderDAO.getOrder(1));
    }

    @Test
    public void update_order() {
        assertTrue(orderDAO.updateOrder(1, "PROCESSING") > 0);
        System.out.println(orderDAO.getOrder(1));
    }

    @Test
    public void get_order_list() {
        assertTrue(orderDAO.getOrderList().size() > 0);
        orderDAO.getOrderList()
                .forEach(System.out::println);
    }
}