package com.murdock.books.spring.statemachine.guide.example.persist;

import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.listener.AbstractCompositeListener;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;

import java.util.Iterator;

/**
 * @author weipeng2k 2018年10月03日 下午17:21:03
 */
class CompositePersistStateChangeListener extends AbstractCompositeListener<PersistStateChangeListener>
        implements PersistStateChangeListener {

    @Override
    public void onPersist(State<String, String> state, Message<String> message,
                          Transition<String, String> transition, StateMachine<String, String> stateMachine) {
        for (Iterator<PersistStateChangeListener> iterator = getListeners().reverse(); iterator.hasNext(); ) {
            PersistStateChangeListener listener = iterator.next();
            listener.onPersist(state, message, transition, stateMachine);
        }
    }
}