package com.murdock.books.spring.statemachine.guide.configuration.pseudo;

import com.murdock.books.spring.statemachine.guide.configuration.EnumEvent;
import com.murdock.books.spring.statemachine.guide.configuration.EnumState;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * @author weipeng2k 2018年09月09日 下午15:14:18
 */
@ContextConfiguration(classes = {Choice1Config.class})
public class Choice1ConfigTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private StateMachine<EnumState, EnumEvent> stateMachine;


    @Test
    public void s2() {
        Message<EnumEvent> message = MessageBuilder.withPayload(EnumEvent.E1).setHeader("ns", "s2").build();
        stateMachine.sendEvent(message);
        Assert.assertEquals(1, Choice1Config.value);
        Assert.assertSame(stateMachine.getState().getId(), EnumState.S2);
    }


}