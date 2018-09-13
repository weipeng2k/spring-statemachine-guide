package com.murdock.books.spring.statemachine.guide.example.cdplayer;

/**
 * @author weipeng2k 2018年09月13日 下午19:51:18
 */
public enum Event {
    /**
     * 弹出，一般是退出CD
     */
    EJECT,
    /**
     * 关闭仓门
     */
    CLOSE,
    /**
     * 更换碟片
     */
    LOAD,
    /**
     * 播放
     */
    PLAY,
    /**
     * 停止
     */
    STOP,
    /**
     * 暂停
     */
    PAUSE,
    /**
     * 恢复
     */
    RESUME,
    /**
     * 后退
     */
    BACK,
    /**
     * 前进
     */
    FORWARD
}
