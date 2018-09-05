package com.murdock.books.spring.statemachine.guide.configuration;

import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;

/**
 * @author weipeng2k 2018年09月04日 下午21:16:31
 */
public class PrintStateMachineListener implements StateMachineListener<EnumState, EnumEvent> {

    @Override
    public void stateChanged(State<EnumState, EnumEvent> from, State<EnumState, EnumEvent> to) {
        if (from != null) {
            System.err.println(String.format("stateChanged from %s to %s", from.getId(), to.getId()));
        }
    }

    @Override
    public void stateEntered(State<EnumState, EnumEvent> state) {
        System.err.println(String.format("stateEntered %s", state.getId()));
    }

    @Override
    public void stateExited(State<EnumState, EnumEvent> state) {
        System.err.println(String.format("stateExited %s", state.getId()));
    }

    @Override
    public void eventNotAccepted(Message<EnumEvent> event) {
    }

    @Override
    public void transition(Transition<EnumState, EnumEvent> transition) {
        if (transition.getSource() != null) {
            System.err.println(String.format("transition %s -> %s", transition.getSource().getId(),
                    transition.getTarget().getId()));
        }
    }

    @Override
    public void transitionStarted(Transition<EnumState, EnumEvent> transition) {
        if (transition.getSource() != null) {
            System.err.println(
                    String.format("transitionStarted %s -> %s", transition.getSource().getId(),
                            transition.getTarget().getId()));
        }
    }

    @Override
    public void transitionEnded(Transition<EnumState, EnumEvent> transition) {
        if (transition.getSource() != null) {
            System.err.println(String.format("transitionEnded %s -> %s", transition.getSource().getId(),
                    transition.getTarget().getId()));
        }
    }

    @Override
    public void stateMachineStarted(StateMachine<EnumState, EnumEvent> stateMachine) {
        System.err.println(String.format("stateMachineStarted %s", stateMachine));
    }

    @Override
    public void stateMachineStopped(StateMachine<EnumState, EnumEvent> stateMachine) {
        System.err.println(String.format("stateMachineStopped %s", stateMachine));
    }

    @Override
    public void stateMachineError(StateMachine<EnumState, EnumEvent> stateMachine,
                                  Exception exception) {
    }

    @Override
    public void extendedStateChanged(Object key, Object value) {
        System.err.println(String.format("extendedStateChanged key %s value %s", key, value));
    }

    @Override
    public void stateContext(StateContext<EnumState, EnumEvent> stateContext) {
        System.err.println(String.format("stateContext %s", stateContext));
    }
}
