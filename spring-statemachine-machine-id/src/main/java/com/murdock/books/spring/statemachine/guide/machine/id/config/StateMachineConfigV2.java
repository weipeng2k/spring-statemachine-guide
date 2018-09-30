package com.murdock.books.spring.statemachine.guide.machine.id.config;

import com.murdock.books.spring.statemachine.guide.machine.id.config.PrintStateMachineListener;
import com.murdock.books.spring.statemachine.guide.machine.id.event.Event;
import com.murdock.books.spring.statemachine.guide.machine.id.state.State;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

/**
 * 配置基础的状态机
 *
 * @author weipeng2k 2018年08月26日 下午18:50:42
 */
@Configuration
@EnableStateMachineFactory
public class StateMachineConfigV2
        extends EnumStateMachineConfigurerAdapter<State, Event> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<State, Event> config) throws Exception {
        config.withConfiguration()
                .autoStartup(true)
                .machineId("my-machine-v2")
                .listener(new PrintStateMachineListener());
    }

    @Override
    public void configure(StateMachineStateConfigurer<State, Event> states)
            throws Exception {
        states.withStates()
                .initial(State.INIT)
                .states(EnumSet.allOf(State.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<State, Event> transitions)
            throws Exception {
        transitions
                .withExternal()
                    .source(State.INIT).target(State.STARTED)
                    .event(Event.START)
                    .and()
                .withExternal()
                    .source(State.STARTED).target(State.STOPPED)
                    .event(Event.STOP);
    }

}