package com.murdock.books.spring.statemachine.guide.configuration;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * @author weipeng2k 2018年09月06日 上午11:33:03
 */
@ContextConfiguration(classes = {ActionConfig.class})
public class ActionConfigTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    private StateMachine<EnumState, EnumEvent> stateMachine;

    @Test
    public void pass() {
        Assert.assertSame(stateMachine.getState().getId(), EnumState.INIT);

        // 构建一个消息
        Message<EnumEvent> eventMessage = MessageBuilder.withPayload(EnumEvent.E1)
                .setHeader("g1", "10")
                .build();
        stateMachine.sendEvent(eventMessage);

        Assert.assertSame(stateMachine.getState().getId(), EnumState.S1);
    }
}