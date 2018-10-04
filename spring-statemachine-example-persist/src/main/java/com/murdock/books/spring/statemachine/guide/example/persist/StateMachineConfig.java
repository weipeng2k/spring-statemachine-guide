package com.murdock.books.spring.statemachine.guide.example.persist;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

/**
 * @author weipeng2k 2018年10月04日 下午22:05:52
 */
@Configuration
@EnableStateMachine
public class StateMachineConfig extends StateMachineConfigurerAdapter<String, String> {

    @Override
    public void configure(StateMachineStateConfigurer<String, String> states)
            throws Exception {
        states
                .withStates()
                .initial("PLACED")
                .state("PROCESSING")
                .state("SENT")
                .state("DELIVERED");
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<String, String> transitions)
            throws Exception {
        transitions
                .withExternal()
                    .source("PLACED").target("PROCESSING")
                    .event("PROCESS")
                    .and()
                .withExternal()
                    .source("PROCESSING").target("SENT")
                    .event("SEND")
                    .and()
                .withExternal()
                    .source("SENT").target("DELIVERED")
                    .event("DELIVER");
    }
}
