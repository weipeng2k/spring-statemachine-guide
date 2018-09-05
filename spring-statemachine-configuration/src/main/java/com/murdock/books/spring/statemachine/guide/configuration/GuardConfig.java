package com.murdock.books.spring.statemachine.guide.configuration;

import org.springframework.messaging.Message;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.guard.Guard;

import java.util.EnumSet;
import java.util.Optional;

/**
 * @author weipeng2k 2018年09月04日 下午21:21:52
 */
@EnableStateMachine
public class GuardConfig extends EnumStateMachineConfigurerAdapter<EnumState, EnumEvent> {

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
                .states(EnumSet.allOf(EnumState.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<EnumState, EnumEvent> transitions) throws Exception {
        transitions.withExternal()
                .source(EnumState.INIT).target(EnumState.S1).event(EnumEvent.E1).guard(g1());
    }

    public Guard<EnumState, EnumEvent> g1() {
        return context -> {
            Message<EnumEvent> message = context.getMessage();
            Object g1 = message.getHeaders().get("g1");
            boolean result =  Optional.ofNullable(g1)
                    .map(Object::toString)
                    .map(Integer::parseInt)
                    .map(value -> value >= 5)
                    .orElse(false);
            return result;
        };
    }
}
