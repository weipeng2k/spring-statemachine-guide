package com.murdock.books.spring.statemachine.guide.example.persist;

import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;

/**
 *
 * The listener interface for receiving persistStateChange events.
 *      * The class that is interested in processing a persistStateChange
 *      * event implements this interface, and the object created
 *      * with that class is registered with a component using the
 *      * component's <code>addPersistStateChangeListener</code> method. When
 *      * the persistStateChange event occurs, that object's appropriate
 *      * method is invoked.
 *
 * @author weipeng2k 2018年10月02日 下午23:31:06
 */
public interface PersistStateChangeListener {
    /**
     * 当状态变更时，会回调这个监听器，来更改状态并持久化
     *
     * @param state the state
     * @param message the message
     * @param transition the transition
     * @param stateMachine the state machine
     */
    void onPersist(State<String, String> state, Message<String> message, Transition<String, String> transition,
                   StateMachine<String, String> stateMachine);
}
