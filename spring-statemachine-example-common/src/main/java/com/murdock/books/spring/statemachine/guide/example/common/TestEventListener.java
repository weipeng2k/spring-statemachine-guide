package com.murdock.books.spring.statemachine.guide.example.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.statemachine.event.OnStateEntryEvent;
import org.springframework.statemachine.event.OnStateExitEvent;
import org.springframework.statemachine.event.OnTransitionEvent;
import org.springframework.statemachine.event.StateMachineEvent;
import org.springframework.statemachine.transition.TransitionKind;

/**
 * @author weipeng2k 2018年09月11日 下午15:45:12
 */
class TestEventListener implements ApplicationListener<StateMachineEvent> {
    private final static Log log = LogFactory.getLog(CommonConfiguration.class);

    @Override
    public void onApplicationEvent(StateMachineEvent event) {
        if (event instanceof OnStateEntryEvent) {
            OnStateEntryEvent e = (OnStateEntryEvent) event;
            log.info("Entry state " + e.getState().getId());
        } else if (event instanceof OnStateExitEvent) {
            OnStateExitEvent e = (OnStateExitEvent) event;
            log.info("Exit state " + e.getState().getId());
        } else if (event instanceof OnTransitionEvent) {
            OnTransitionEvent e = (OnTransitionEvent) event;
            if (e.getTransition().getKind() == TransitionKind.INTERNAL) {
                log.info("Internal transition source=" + e.getTransition().getSource().getId());
            }
        }
    }

}
