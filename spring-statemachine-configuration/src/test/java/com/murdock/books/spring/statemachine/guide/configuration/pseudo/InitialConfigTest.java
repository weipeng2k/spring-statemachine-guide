package com.murdock.books.spring.statemachine.guide.configuration.pseudo;

import com.murdock.books.spring.statemachine.guide.configuration.ActionConfig2;
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

import static org.junit.Assert.*;

/**
 * @author weipeng2k 2018年09月09日 上午10:55:15
 */
@ContextConfiguration(classes = {InitialConfig.class})
public class InitialConfigTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private StateMachine<EnumState, EnumEvent> stateMachine;

    @Test
    public void pass() {
        Assert.assertSame(stateMachine.getState().getId(), EnumState.INIT);
        assertEquals(1, InitialConfig.value);
    }

}