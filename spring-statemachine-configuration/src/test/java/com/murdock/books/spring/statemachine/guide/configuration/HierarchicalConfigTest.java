package com.murdock.books.spring.statemachine.guide.configuration;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * @author weipeng2k 2018年09月03日 下午21:45:13
 */
@ContextConfiguration(classes = {HierarchicalConfig.class})
public class HierarchicalConfigTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private StateMachine<EnumState, EnumEvent> hierarchicalConfig;

    @Test
    public void send_event() {
        hierarchicalConfig.sendEvent(EnumEvent.E1);
    }

}