package com.murdock.books.spring.statemachine.guide.configuration;

import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;

import java.util.EnumSet;

/**
 * @author weipeng2k 2018年08月31日 下午20:01:59
 */
@EnableStateMachine
public class ConfigEnums extends EnumStateMachineConfigurerAdapter<EnumState, EventEnums> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<EnumState, EventEnums> config) throws Exception {
        config.withConfiguration()
                .autoStartup(true)
                .listener(new StateMachineListener<EnumState, EventEnums>() {
                    @Override
                    public void stateChanged(State<EnumState, EventEnums> from, State<EnumState, EventEnums> to) {
                        if (from != null) {
                            System.err.println(String.format("stateChanged from %s to %s", from.getId(), to.getId()));
                        }
                    }

                    @Override
                    public void stateEntered(State<EnumState, EventEnums> state) {
                        System.err.println(String.format("stateEntered %s", state.getId()));
                    }

                    @Override
                    public void stateExited(State<EnumState, EventEnums> state) {
                        System.err.println(String.format("stateExited %s", state.getId()));
                    }

                    @Override
                    public void eventNotAccepted(Message<EventEnums> event) {
                    }

                    @Override
                    public void transition(Transition<EnumState, EventEnums> transition) {
                        if (transition.getSource() != null) {
                            System.err.println(String.format("transition %s -> %s", transition.getSource().getId(),
                                    transition.getTarget().getId()));
                        }
                    }

                    @Override
                    public void transitionStarted(Transition<EnumState, EventEnums> transition) {
                        if (transition.getSource() != null) {
                            System.err.println(
                                    String.format("transitionStarted %s -> %s", transition.getSource().getId(),
                                            transition.getTarget().getId()));
                        }
                    }

                    @Override
                    public void transitionEnded(Transition<EnumState, EventEnums> transition) {
                        if (transition.getSource() != null) {
                            System.err.println(String.format("transitionEnded %s -> %s", transition.getSource().getId(),
                                    transition.getTarget().getId()));
                        }
                    }

                    @Override
                    public void stateMachineStarted(StateMachine<EnumState, EventEnums> stateMachine) {
                        System.err.println(String.format("stateMachineStarted %s", stateMachine));
                    }

                    @Override
                    public void stateMachineStopped(StateMachine<EnumState, EventEnums> stateMachine) {
                        System.err.println(String.format("stateMachineStopped %s", stateMachine));
                    }

                    @Override
                    public void stateMachineError(StateMachine<EnumState, EventEnums> stateMachine,
                                                  Exception exception) {
                    }

                    @Override
                    public void extendedStateChanged(Object key, Object value) {
                        System.err.println(String.format("extendedStateChanged key %s value %s", key, value));
                    }

                    @Override
                    public void stateContext(StateContext<EnumState, EventEnums> stateContext) {
                        System.err.println(String.format("stateContext %s", stateContext));
                    }
                });
    }

    @Override
    public void configure(StateMachineStateConfigurer<EnumState, EventEnums> states) throws Exception {
        states.withStates()
                .initial(EnumState.INIT)
                .end(EnumState.END)
                .stateExit(EnumState.INIT, context -> {
                    System.err.println(
                            String.format("stateExit %s and context %s", context.getSource().getId(), context));
                })
                .states(EnumSet.allOf(EnumState.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<EnumState, EventEnums> transitions) throws Exception {
        transitions.withExternal()
                .source(EnumState.INIT).target(EnumState.S1).event(EventEnums.E1)
                .and().withExternal()
                .source(EnumState.S1).target(EnumState.S2).event(EventEnums.E2)
                .and().withExternal()
                .source(EnumState.S2).target(EnumState.END).event(EventEnums.E3);
    }
}
