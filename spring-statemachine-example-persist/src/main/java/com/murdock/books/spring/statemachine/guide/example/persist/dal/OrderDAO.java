package com.murdock.books.spring.statemachine.guide.example.persist.dal;

import com.murdock.books.spring.statemachine.guide.example.persist.Order;

import java.util.List;

/**
 * <pre>
 * OrderDAO
 * </pre>
 *
 * @author weipeng2k 2018年10月04日 下午16:20:59
 */
public interface OrderDAO {
    /**
     * 创建一笔订单
     *
     * @param id pk
     * @param state 状态
     * @return pk
     */
    void createOrder(int id, String state);

    /**
     * 根据id获取Order
     *
     * @param id pk
     * @return Order
     */
    Order getOrder(int id);

    /**
     * 根据id修改order状态
     *
     * @param id    pk
     * @param state 状态
     * @return 修改行数
     */
    int updateOrder(int id, String state);

    /**
     * 获取订单列表
     *
     * @return 订单列表
     */
    List<Order> getOrderList();
}
