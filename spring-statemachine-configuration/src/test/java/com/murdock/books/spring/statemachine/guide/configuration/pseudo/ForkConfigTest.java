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
 * @author weipeng2k 2018年09月10日 下午13:57:32
 */
@ContextConfiguration(classes = {ForkConfig.class})
public class ForkConfigTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private StateMachine<EnumState, EnumEvent> stateMachine;

//    @Test
//    public void fork_test() {
//        Message<EnumEvent> message = MessageBuilder.withPayload(EnumEvent.E1).setHeader("ns", "s2").build();
//        stateMachine.sendEvent(message);
//    }

}