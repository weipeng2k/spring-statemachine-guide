package com.murdock.books.spring.statemachine.guide.machine.id.config;

import com.murdock.books.spring.statemachine.guide.machine.id.event.Event;
import com.murdock.books.spring.statemachine.guide.machine.id.state.State;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.transition.Transition;

/**
 * @author weipeng2k 2018年09月04日 下午21:16:31
 */
public class PrintStateMachineListener implements StateMachineListener<State, Event> {

    @Override
    public void stateChanged(org.springframework.statemachine.state.State<State, Event> from, org.springframework.statemachine.state.State<State, Event> to) {
        if (from != null) {
            System.err.println(String.format("stateChanged from %s to %s", from.getId(), to.getId()));
        }
    }

    @Override
    public void stateEntered(org.springframework.statemachine.state.State<State, Event> state) {
        System.err.println(String.format("stateEntered %s", state.getId()));
    }

    @Override
    public void stateExited(org.springframework.statemachine.state.State<State, Event> state) {
        System.err.println(String.format("stateExited %s", state.getId()));
    }

    @Override
    public void eventNotAccepted(Message<Event> event) {
    }

    @Override
    public void transition(Transition<State, Event> transition) {
        if (transition.getSource() != null) {
            System.err.println(String.format("transition %s -> %s", transition.getSource().getId(),
                    transition.getTarget().getId()));
        }
    }

    @Override
    public void transitionStarted(Transition<State, Event> transition) {
        if (transition.getSource() != null) {
            System.err.println(
                    String.format("transitionStarted %s -> %s", transition.getSource().getId(),
                            transition.getTarget().getId()));
        }
    }

    @Override
    public void transitionEnded(Transition<State, Event> transition) {
        if (transition.getSource() != null) {
            System.err.println(String.format("transitionEnded %s -> %s", transition.getSource().getId(),
                    transition.getTarget().getId()));
        }
    }

    @Override
    public void stateMachineStarted(StateMachine<State, Event> stateMachine) {
        System.err.println(String.format("stateMachineStarted %s", stateMachine));
    }

    @Override
    public void stateMachineStopped(StateMachine<State, Event> stateMachine) {
        System.err.println(String.format("stateMachineStopped %s", stateMachine));
    }

    @Override
    public void stateMachineError(StateMachine<State, Event> stateMachine,
                                  Exception exception) {
    }

    @Override
    public void extendedStateChanged(Object key, Object value) {
        System.err.println(String.format("extendedStateChanged key %s value %s", key, value));
    }

    @Override
    public void stateContext(StateContext<State, Event> stateContext) {
        System.err.println(String.format("stateContext %s", stateContext));
    }
}
