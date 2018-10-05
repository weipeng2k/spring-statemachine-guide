package com.murdock.books.spring.statemachine.guide.basic.extension;

import com.murdock.books.spring.statemachine.guide.basic.event.Events;
import com.murdock.books.spring.statemachine.guide.basic.state.States;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;

/**
 * @author weipeng2k 2018年10月05日 上午10:18:56
 */
public class EnumStateMachineInterceptor extends StateMachineInterceptorAdapter<States, Events> {

    @Override
    public Message<Events> preEvent(Message<Events> message, StateMachine<States, Events> stateMachine) {

        System.out.println("EnumStateMachineInterceptor#preEvent(" + String.join(", ", message.toString(),
                stateMachine.toString()) + ")");
        return message;
    }

    @Override
    public void preStateChange(State<States, Events> state, Message<Events> message,
                               Transition<States, Events> transition, StateMachine<States, Events> stateMachine) {
        System.out.println("target state : " + state.getId());
        System.out.println(
                "EnumStateMachineInterceptor#preStateChange(" + String.join(", ", state.toString(), message.toString(),
                        transition.toString(), stateMachine.toString()) + ")");
    }

    @Override
    public void postStateChange(State<States, Events> state, Message<Events> message,
                                Transition<States, Events> transition, StateMachine<States, Events> stateMachine) {
        System.out.println("target state : " + state.getId());
        System.out.println(Thread.currentThread());
        System.out.println(
                "EnumStateMachineInterceptor#postStateChange(" + String.join(", ", state.toString(), message.toString(),
                        transition.toString(), stateMachine.toString()) + ")");
    }

    @Override
    public StateContext<States, Events> preTransition(StateContext<States, Events> stateContext) {
        System.out.println("EnumStateMachineInterceptor#preTransition(" + stateContext + ")");
        return stateContext;
    }

    @Override
    public StateContext<States, Events> postTransition(StateContext<States, Events> stateContext) {
        System.out.println("EnumStateMachineInterceptor#postTransition(" + stateContext + ")");
        return stateContext;
    }
}
