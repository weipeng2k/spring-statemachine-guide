package com.murdock.books.spring.statemachine.guide.basic.configure;

import com.murdock.books.spring.statemachine.guide.basic.event.Events;
import com.murdock.books.spring.statemachine.guide.basic.state.States;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;

import java.util.EnumSet;

/**
 * @author weipeng2k 2018年08月26日 下午18:50:42
 */
@Configuration
@EnableStateMachine(name = "stateMachine2")
public class StateMachineConfig2
        extends EnumStateMachineConfigurerAdapter<States, Events> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<States, Events> config) throws Exception {
        config.withConfiguration()
                .autoStartup(true)
                .listener(listener());
    }

    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states)
            throws Exception {
        states.withStates()
                .initial(States.SI)
                .states(EnumSet.allOf(States.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions)
            throws Exception {
        transitions.withExternal()
                .source(States.SI).target(States.S1).event(Events.E2)
                .and().withExternal()
                .source(States.S1).target(States.S2).event(Events.E1);
    }

    @Bean
    public StateMachineListener<States, Events> listener() {
        return new StateMachineListenerAdapter<States, Events>() {
            @Override
            public void stateChanged(State<States, Events> from, State<States, Events> to) {
                if (from != null) {
                    System.err.println(String.format("stateChanged from %s to %s", from.getId(), to.getId()));
                }
            }

            @Override
            public void stateEntered(State<States, Events> state) {
                System.err.println(String.format("stateEntered %s", state.getId()));
            }

            @Override
            public void stateExited(State<States, Events> state) {
                System.err.println(String.format("stateExited %s", state.getId()));
            }

            @Override
            public void eventNotAccepted(Message<Events> event) {
            }

            @Override
            public void transition(Transition<States, Events> transition) {
                if (transition.getSource() != null) {
                    System.err.println(String.format("transition %s -> %s", transition.getSource().getId(),
                            transition.getTarget().getId()));
                }
            }

            @Override
            public void transitionStarted(Transition<States, Events> transition) {
                System.out.println(Thread.currentThread());
                if (transition.getSource() != null) {
                    System.err.println(
                            String.format("transitionStarted %s -> %s", transition.getSource().getId(),
                                    transition.getTarget().getId()));
                }
            }

            @Override
            public void transitionEnded(Transition<States, Events> transition) {
                if (transition.getSource() != null) {
                    System.err.println(String.format("transitionEnded %s -> %s", transition.getSource().getId(),
                            transition.getTarget().getId()));
                }
            }

            @Override
            public void stateMachineStarted(StateMachine<States, Events> stateMachine) {
                System.err.println(String.format("stateMachineStarted %s", stateMachine));
            }

            @Override
            public void stateMachineStopped(StateMachine<States, Events> stateMachine) {
                System.err.println(String.format("stateMachineStopped %s", stateMachine));
            }

            @Override
            public void stateMachineError(StateMachine<States, Events> stateMachine,
                                          Exception exception) {
            }

            @Override
            public void extendedStateChanged(Object key, Object value) {
                System.err.println(String.format("extendedStateChanged key %s value %s", key, value));
            }

            @Override
            public void stateContext(StateContext<States, Events> stateContext) {
                System.err.println(String.format("stateContext %s", stateContext));
            }
        };
    }
}