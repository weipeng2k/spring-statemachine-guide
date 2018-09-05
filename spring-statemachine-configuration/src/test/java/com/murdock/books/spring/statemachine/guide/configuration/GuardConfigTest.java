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
 * @author weipeng2k 2018年09月04日 下午21:34:34
 */
@ContextConfiguration(classes = {GuardConfig.class})
public class GuardConfigTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private StateMachine<EnumState, EnumEvent> stateMachine;

    @Test
    public void test_no() {
        stateMachine.sendEvent(EnumEvent.E1);
    }

    @Test
    public void not_pass() {
        Message<EnumEvent> eventMessage = MessageBuilder.withPayload(EnumEvent.E1).setHeader("g1", "4").build();
        stateMachine.sendEvent(eventMessage);
    }

    @Test
    public void pass() {
        Assert.assertSame(stateMachine.getState().getId(), EnumState.INIT);
        Message<EnumEvent> eventMessage = MessageBuilder.withPayload(EnumEvent.E1).setHeader("g1", "10").build();
        stateMachine.sendEvent(eventMessage);

        Assert.assertSame(stateMachine.getState().getId(), EnumState.S1);
    }


}