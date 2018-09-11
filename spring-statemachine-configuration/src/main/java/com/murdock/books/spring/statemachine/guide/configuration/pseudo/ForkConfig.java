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
 * 配置Action，Fork
 *
 * @author weipeng2k 2018年09月04日 下午21:21:52
 */
@EnableStateMachine
public class ForkConfig extends EnumStateMachineConfigurerAdapter<EnumState, EnumEvent> {

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
        // 状态配置时，需要声明在哪个状态上需要有Fork
        // Fork设置在S1上
        // parent在S2上
        states.withStates()
                .initial(EnumState.INIT)
                .end(EnumState.END)
                .fork(EnumState.S1)
                .state(EnumState.S2)
                .and()
                    .withStates()
                    .parent(EnumState.S2)
                    .initial(EnumState.S2_1, action())
                    .state(EnumState.S2_2, action())
                    .end(EnumState.S2_E)
                .and()
                    .withStates()
                    .parent(EnumState.S2)
                    .initial(EnumState.S3_1, action())
                    .state(EnumState.S3_2, action())
                    .end(EnumState.S3_E);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<EnumState, EnumEvent> transitions) throws Exception {
        // 针对选择状态，配置变迁
        // 当进入S1，则开始状态判断
        transitions
                .withExternal()
                    .source(EnumState.INIT)
                    .target(EnumState.S2)
                    .event(EnumEvent.E1)
                    .and()
                .withFork()
                    .source(EnumState.S2)
                    .target(EnumState.S2_2)
                    .target(EnumState.S3_2);
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
                System.err.println("action , target state is " + context.getTarget().getId());
                value = 1;
            }
        };
    }

}
