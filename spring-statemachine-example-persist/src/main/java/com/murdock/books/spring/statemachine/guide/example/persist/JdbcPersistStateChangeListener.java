package com.murdock.books.spring.statemachine.guide.example.persist;

import com.murdock.books.spring.statemachine.guide.example.persist.dal.OrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;

import java.util.Optional;

/**
 * @author weipeng2k 2018年10月03日 下午22:19:05
 */
public class JdbcPersistStateChangeListener implements PersistStateChangeListener {

    @Autowired
    private OrderDAO orderDAO;

    @Override
    public void onPersist(State<String, String> state, Message<String> message, Transition<String, String> transition,
                          StateMachine<String, String> stateMachine) {

        Optional.ofNullable(message)
                .filter(msg -> msg.getHeaders().containsKey("order"))
                .ifPresent(msg -> {
                    Integer order = message.getHeaders().get("order", Integer.class);
                    orderDAO.updateOrder(order, state.getId());
                });
    }
}
