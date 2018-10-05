package com.murdock.books.spring.statemachine.guide.example.persist;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.access.StateMachineAccess;
import org.springframework.statemachine.access.StateMachineFunction;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.util.Assert;

import java.util.List;

/**
 * <pre>
 * 完成的工作：
 * 1. 一个状态机与之绑定，状态机的类型处理的状态和事件都是String类型
 * 2. {@link PersistStateChangeListener}被注册，并且由它来完成持久化状态的改变
 * 3. {@link #handleEventWithState(Message, String)}用来编排状态的变迁
 * </pre>
 *
 * @author weipeng2k 2018年10月02日 下午23:03:01
 */
public class PersistStateMachineHandler implements InitializingBean {

    private final StateMachine<String, String> stateMachine;
    private final PersistingStateChangeInterceptor persistingStateChangeInterceptor;
    private final CompositePersistStateChangeListener compositePersistStateChangeListener;

    /**
     * Instantiates a new persist state machine handler.
     *
     * @param stateMachine the state machine
     */
    public PersistStateMachineHandler(StateMachine<String, String> stateMachine) {
        Assert.notNull(stateMachine, "State machine must be set");
        this.stateMachine = stateMachine;
        compositePersistStateChangeListener = new CompositePersistStateChangeListener();
        persistingStateChangeInterceptor = new PersistingStateChangeInterceptor(compositePersistStateChangeListener);
    }

    /**
     * 用来编排状态的变迁
     *
     * @param event 当前发生的事件
     * @param state 现在所处的状态
     * @return true if event was accepted
     */
    public boolean handleEventWithState(Message<String> event, String state) {
        stateMachine.stop();
        List<StateMachineAccess<String, String>> withAllRegions = stateMachine.getStateMachineAccessor().withAllRegions();
        for (StateMachineAccess<String, String> a : withAllRegions) {
            a.resetStateMachine(new DefaultStateMachineContext<String, String>(state, null, null, null));
        }
        stateMachine.start();
        return stateMachine.sendEvent(event);
    }

    /**
     * 添加一个持久状态监听器
     *
     * @param listener 持久状态监听器
     */
    public void addPersistStateChangeListener(PersistStateChangeListener listener) {
        compositePersistStateChangeListener.register(listener);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        stateMachine.getStateMachineAccessor().doWithAllRegions(
                new StateMachineFunction<StateMachineAccess<String, String>>() {

                    @Override
                    public void apply(StateMachineAccess<String, String> function) {
                        function.addStateMachineInterceptor(persistingStateChangeInterceptor);
                    }
                });
    }
}