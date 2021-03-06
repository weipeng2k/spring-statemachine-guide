package com.murdock.books.spring.statemachine.guide.configuration;

import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

/**
 * @author weipeng2k 2018年08月31日 下午20:01:59
 */
@EnableStateMachine
public class ConfigEnums extends EnumStateMachineConfigurerAdapter<EnumState, EnumEvent> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<EnumState, EnumEvent> config) throws Exception {
        config.withConfiguration()
                .autoStartup(true)
                .listener(new PrintStateMachineListener());
    }

    @Override
    public void configure(StateMachineStateConfigurer<EnumState, EnumEvent> states) throws Exception {
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
    public void configure(StateMachineTransitionConfigurer<EnumState, EnumEvent> transitions) throws Exception {
        transitions.withExternal()
                .source(EnumState.INIT).target(EnumState.S1).event(EnumEvent.E1)
                .and().withExternal()
                .source(EnumState.S1).target(EnumState.S2).event(EnumEvent.E2)
                .and().withExternal()
                .source(EnumState.S2).target(EnumState.END).event(EnumEvent.E3);
    }
}
