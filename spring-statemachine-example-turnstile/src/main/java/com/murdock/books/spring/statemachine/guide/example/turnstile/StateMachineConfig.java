package com.murdock.books.spring.statemachine.guide.example.turnstile;

import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

/**
 * @author weipeng2k 2018年09月11日 下午13:35:28
 */
@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<State, Event> {

    @Override
    public void configure(StateMachineStateConfigurer<State, Event> states) throws Exception {
        states.withStates()
                .initial(State.LOCKED)
                .states(EnumSet.allOf(State.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<State, Event> transitions) throws Exception {
        transitions
                .withExternal()
                    .source(State.LOCKED)
                    .target(State.UNLOCKED)
                    .event(Event.COIN)
                    .and()
                .withExternal()
                    .source(State.UNLOCKED)
                    .target(State.LOCKED)
                    .event(Event.PUSH);

    }
}
