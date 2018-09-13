package com.murdock.books.spring.statemachine.guide.example.cdplayer;

/**
 * @author weipeng2k 2018年09月13日 下午21:06:46
 */
public class Track {

    private final String name;
    private final long length;

    public Track(String name, long length) {
        this.name = name;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public long getLength() {
        return length;
    }

    @Override
    public String toString() {
        return name;
    }

}
