package com.murdock.books.spring.statemachine.guide;

import com.murdock.books.spring.statemachine.guide.configuration.ConfigEnums;
import com.murdock.books.spring.statemachine.guide.configuration.EnumState;
import com.murdock.books.spring.statemachine.guide.configuration.EnumEvent;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * @author weipeng2k 2018年08月31日 下午20:15:30
 */
@ContextConfiguration(classes = {ConfigEnums.class})
public class ConfigEnumsTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private StateMachine<EnumState, EnumEvent> enumConfig;

    @Test
    public void test() {
        Assert.assertNotNull(enumConfig);
    }

    @Test
    public void sendEvent() {
        enumConfig.sendEvent(EnumEvent.E1);
    }
}
