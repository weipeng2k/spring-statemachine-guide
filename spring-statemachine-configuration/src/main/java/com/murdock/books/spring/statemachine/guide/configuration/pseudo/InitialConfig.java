package com.murdock.books.spring.statemachine.guide.configuration.pseudo;

import com.murdock.books.spring.statemachine.guide.configuration.EnumEvent;
import com.murdock.books.spring.statemachine.guide.configuration.EnumState;
import com.murdock.books.spring.statemachine.guide.configuration.PrintStateMachineListener;
import org.springframework.context.annotation.Bean;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 配置Action，初始状态
 *
 * @author weipeng2k 2018年09月04日 下午21:21:52
 */
@EnableStateMachine
public class InitialConfig extends EnumStateMachineConfigurerAdapter<EnumState, EnumEvent> {

    private static final AtomicInteger id = new AtomicInteger();

    static int value;

    @Override
    public void configure(StateMachineConfigurationConfigurer<EnumState, EnumEvent> config) throws Exception {
        config.withConfiguration()
                .autoStartup(true)
                .listener(new PrintStateMachineListener());
    }

    @Override
    public void configure(StateMachineStateConfigurer<EnumState, EnumEvent> states) throws Exception {
        states.withStates()
                .initial(EnumState.INIT, action())
                .end(EnumState.END)
                .states(EnumSet.allOf(EnumState.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<EnumState, EnumEvent> transitions) throws Exception {
        transitions.withExternal()
                .source(EnumState.INIT).target(EnumState.S1).event(EnumEvent.E1);
    }

    @Bean
    public Action<EnumState, EnumEvent> action() {
        return new Action<EnumState, EnumEvent>() {

            private int i = id.incrementAndGet();

            {
                System.err.println("Action create!!!!!!!!!!!! " + i);
            }

            @Override
            public void execute(StateContext<EnumState, EnumEvent> context) {
                System.err.println("initial action , target state is " + context.getTarget().getId());
                value = 1;
            }
        };
    }

}
