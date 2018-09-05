package com.murdock.books.spring.statemachine.guide.configuration;

import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

/**
 * @author weipeng2k 2018年09月04日 下午21:07:36
 */
@EnableStateMachine
public class TransitionConfig extends EnumStateMachineConfigurerAdapter<EnumState, EnumEvent> {

    @Override
    public void configure(StateMachineStateConfigurer<EnumState, EnumEvent> states)
            throws Exception {
        states.withStates()
                .initial(EnumState.S1)
                .states(EnumSet.allOf(EnumState.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<EnumState, EnumEvent> transitions)
            throws Exception {
        transitions
                .withExternal()
                .source(EnumState.S1).target(EnumState.S2).event(EnumEvent.E1)
                .and()
                .withInternal()
                .source(EnumState.S2).event(EnumEvent.E2)
                .and()
                .withLocal()
                .source(EnumState.S2).target(EnumState.S3).event(EnumEvent.E3);
    }
}
