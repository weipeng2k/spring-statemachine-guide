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
import org.springframework.statemachine.guard.Guard;

import java.util.EnumSet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 配置Action，Choice
 *
 * @author weipeng2k 2018年09月04日 下午21:21:52
 */
@EnableStateMachine
public class Choice1Config extends EnumStateMachineConfigurerAdapter<EnumState, EnumEvent> {

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
        // 状态配置时，需要声明在哪个状态上需要有选择
        states.withStates()
                .initial(EnumState.INIT, action())
                .end(EnumState.END)
                .choice(EnumState.S1)
                .states(EnumSet.allOf(EnumState.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<EnumState, EnumEvent> transitions) throws Exception {
        // 针对选择状态，配置变迁
        // 当进入S1，则开始状态判断
        transitions.withExternal()
                        .source(EnumState.INIT)
                        .target(EnumState.S1)
                        .event(EnumEvent.E1)
                        .and()
                    .withChoice()
                        .source(EnumState.S1)
                        // if guard() else if guard2() else action()
                        .first(EnumState.S2, guard(), action())
                        .then(EnumState.S3, guard2(), action2())
                        .last(EnumState.END, action3());
    }

    @Bean
    public Guard<EnumState, EnumEvent> guard2() {
        return new Guard<EnumState, EnumEvent>() {
            @Override
            public boolean evaluate(StateContext<EnumState, EnumEvent> context) {
                String ns = (String) context.getMessageHeader("ns");
                return "s3".equals(ns);
            }
        };
    }

    @Bean
    public Guard<EnumState, EnumEvent> guard() {
        return new Guard<EnumState, EnumEvent>() {
            @Override
            public boolean evaluate(StateContext<EnumState, EnumEvent> context) {
                String ns = (String) context.getMessageHeader("ns");
                return "s2".equals(ns);
            }
        };
    }

    @Bean
    public Action<EnumState, EnumEvent> action2() {
        return new Action<EnumState, EnumEvent>() {

            private int i = id.incrementAndGet();

            {
                System.err.println("Action create!!!!!!!!!!!! " + i);
            }

            @Override
            public void execute(StateContext<EnumState, EnumEvent> context) {
                System.err.println("then action , target state is " + context.getTarget().getId());
                value = 2;
            }
        };
    }

    @Bean
    public Action<EnumState, EnumEvent> action3() {
        return new Action<EnumState, EnumEvent>() {

            private int i = id.incrementAndGet();

            {
                System.err.println("Action create!!!!!!!!!!!! " + i);
            }

            @Override
            public void execute(StateContext<EnumState, EnumEvent> context) {
                System.err.println("last action , target state is " + context.getTarget().getId());
                value = 3;
            }
        };
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
                System.err.println("first action , target state is " + context.getTarget().getId());
                value = 1;
            }
        };
    }

}
