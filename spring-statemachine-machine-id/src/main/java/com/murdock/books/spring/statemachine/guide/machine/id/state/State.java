package com.murdock.books.spring.statemachine.guide.machine.id.state;

/**
 * <pre>
 * 简单状态
 *
 * INIT -- #START --> STARTED -- #STOP --> STOPPED
 *
 * </pre>
 *
 * @author weipeng2k 2018年09月30日 下午14:30:24
 */
public enum State {

    INIT,

    STARTED,

    STOPPED
}
