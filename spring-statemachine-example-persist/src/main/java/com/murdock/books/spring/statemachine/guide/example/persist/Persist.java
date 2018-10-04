package com.murdock.books.spring.statemachine.guide.example.persist;

import com.murdock.books.spring.statemachine.guide.example.persist.dal.OrderDAO;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author weipeng2k 2018年10月03日 下午22:23:50
 */
@Component("persist")
public class Persist implements InitializingBean {
    @Autowired
    private PersistStateMachineHandler handler;
    @Autowired
    private PersistStateChangeListener listener;
    @Autowired
    private OrderDAO orderDAO;

    public String listDbEntries() {
        List<Order> orders = orderDAO.getOrderList();
        StringBuilder buf = new StringBuilder();
        for (Order order : orders) {
            buf.append(order);
            buf.append("\n");
        }
        return buf.toString();
    }

    public void change(int order, String event) {
        Order o = orderDAO.getOrder(order);
        handler.handleEventWithState(MessageBuilder.withPayload(event).setHeader("order", order).build(), o.state);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        handler.addPersistStateChangeListener(listener);
    }
}
