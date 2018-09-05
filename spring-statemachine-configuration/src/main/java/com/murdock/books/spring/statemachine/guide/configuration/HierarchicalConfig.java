package com.murdock.books.spring.statemachine.guide.configuration;

import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

/**
 * @author weipeng2k 2018年09月03日 下午21:32:20
 */
@EnableStateMachine
public class HierarchicalConfig extends EnumStateMachineConfigurerAdapter<EnumState, EnumEvent> {

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
                .state(EnumState.INIT)
                .and()
                .withStates().parent(EnumState.INIT)
                .initial(EnumState.S1)
                .state(EnumState.S1);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<EnumState, EnumEvent> transitions) throws Exception {
        transitions.withExternal()
                .source(EnumState.INIT).target(EnumState.S1).event(EnumEvent.E1);
    }
}
