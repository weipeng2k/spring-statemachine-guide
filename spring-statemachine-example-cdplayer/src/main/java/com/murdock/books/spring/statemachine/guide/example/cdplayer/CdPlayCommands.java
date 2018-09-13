package com.murdock.books.spring.statemachine.guide.example.cdplayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author weipeng2k 2018年09月13日 下午21:19:22
 */
@Component
public class CdPlayCommands implements CommandMarker {

    @Autowired
    private CdPlayer cdPlayer;

    @Autowired
    private Library library;

    @CliCommand(value = "cd lcd", help = "Prints CD player lcd info")
    public String lcd() {
        return cdPlayer.getLdcStatus();
    }

    @CliCommand(value = "cd library", help = "List user CD library")
    public String library() {
        SimpleDateFormat format = new SimpleDateFormat("mm:ss");
        StringBuilder buf = new StringBuilder();
        int i1 = 0;
        for (Cd cd : library.getCollection()) {
            buf.append(i1++ + ": " + cd.getName() + "\n");
            int i2 = 0;
            for (Track track : cd.getTracks()) {
                buf.append("  " + i2++ + ": " + track.getName() + "  " + format.format(new Date(track.getLength()*1000)) + "\n");
            }
        }
        return buf.toString();
    }

    @CliCommand(value = "cd load", help = "Load CD into player")
    public String load(@CliOption(key = {"", "index"}) int index) {
        StringBuilder buf = new StringBuilder();
        try {
            Cd cd = library.getCollection().get(index);
            cdPlayer.load(cd);
            buf.append("Loading cd " + cd);
        } catch (Exception e) {
            buf.append("Cd with index " + index + " not found, check library");
        }
        return buf.toString();
    }

    @CliCommand(value = "cd play", help = "Press player play button")
    public void play() {
        cdPlayer.play();
    }

    @CliCommand(value = "cd stop", help = "Press player stop button")
    public void stop() {
        cdPlayer.stop();
    }

    @CliCommand(value = "cd close", help = "Press player close button")
    public void close() {
        cdPlayer.close();
    }

    @CliCommand(value = "cd resume", help = "Press player resume button")
    public void resume() {
        cdPlayer.resume();
    }

    @CliCommand(value = "cd pause", help = "Press player pause button")
    public void pause() {
        cdPlayer.pause();
    }

    @CliCommand(value = "cd eject", help = "Press player eject button")
    public void eject() {
        cdPlayer.eject();
    }

    @CliCommand(value = "cd forward", help = "Press player forward button")
    public void forward() {
        cdPlayer.forward();
    }

    @CliCommand(value = "cd back", help = "Press player back button")
    public void back() {
        cdPlayer.back();
    }

}
