package com.murdock.books.spring.statemachine.guide.example.persist;

import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;

/**
 * <pre>
 * 实现了StateMachineInterceptor
 * 能够从宏观上拦截状态机变迁的数据
 * </pre>
 *
 * @author weipeng2k 2018年10月02日 下午23:32:56
 */
class PersistingStateChangeInterceptor extends StateMachineInterceptorAdapter<String, String> {

    private PersistStateChangeListener listener;

    PersistingStateChangeInterceptor(PersistStateChangeListener listener) {
        this.listener = listener;
    }

    @Override
    public void preStateChange(State<String, String> state, Message<String> message,
                               Transition<String, String> transition, StateMachine<String, String> stateMachine) {
        // 回调注册的listener
        listener.onPersist(state, message, transition, stateMachine);
    }
}
