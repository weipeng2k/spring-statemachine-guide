package com.murdock.books.spring.statemachine.guide.example.cdplayer;

/**
 * @author weipeng2k 2018年09月13日 下午21:07:53
 */
public class Cd {

    private final String name;
    private final Track[] tracks;

    public Cd(String name, Track[] tracks) {
        this.name = name;
        this.tracks = tracks;
    }

    public String getName() {
        return name;
    }

    public Track[] getTracks() {
        return tracks;
    }

    @Override
    public String toString() {
        return name;
    }

}
