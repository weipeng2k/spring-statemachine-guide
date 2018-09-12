package com.murdock.books.spring.statemachine.guide.example.cdplayer;

/**
 * @author weipeng2k 2018年09月12日 下午21:20:22
 */
public enum State {
    /**
     * 工作中
     */
    BUSY,
    /**
     * 播放中
     */
    PLAYING,
    /**
     * 暂停
     */
    PAUSED,
    /**
     * 待机，空闲
     */
    IDLE,
    /**
     * 仓门关闭
     */
    CLOSED,
    /**
     * 仓门打开
     */
    OPEN

}
