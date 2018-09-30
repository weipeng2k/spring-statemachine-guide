package com.murdock.books.spring.statemachine.guide.example.cdplayer;

import java.util.Arrays;
import java.util.List;

/**
 * @author weipeng2k 2018年09月13日 下午21:07:29
 */
public class Library {

    private final List<Cd> collection;

    public Library(Cd[] collection) {
        this.collection = Arrays.asList(collection);
    }

    public static Library buildSampleLibrary() {
        Track cd1track1 = new Track("阳明山", 2 * 60 + 32);
        Track cd1track2 = new Track("算什么男人", 4 * 60 + 48);
        Track cd1track3 = new Track("手写的从前", 4 * 60 + 57);
        Track cd1track4 = new Track("听爸爸的话", 4 * 60 + 23);
        Cd cd1 = new Cd("哎呦，不错哦", new Track[]{cd1track1, cd1track2, cd1track3, cd1track4});
        Track cd2track1 = new Track("床边故事", 4 * 60 + 22);
        Track cd2track2 = new Track("说走就走", 4 * 60 + 8);
        Track cd2track3 = new Track("一点点", 4 * 60 + 42);
        Track cd2track4 = new Track("告白气球", 3 * 60 + 36);
        Cd cd2 = new Cd("周杰伦的床边故事", new Track[]{cd2track1, cd2track2, cd2track3, cd2track4});
        return new Library(new Cd[]{cd1, cd2});
    }

    public List<Cd> getCollection() {
        return collection;
    }

}
