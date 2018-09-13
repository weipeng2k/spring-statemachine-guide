package com.murdock.books.spring.statemachine.guide.example.cdplayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.ExtendedState;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author weipeng2k 2018年09月13日 下午21:10:43
 */
@WithStateMachine
public class CdPlayer {
    @Autowired
    private StateMachine<State, Event> stateMachine;

    private String cdStatus = "No CD";
    private String trackStatus = "";

    public void load(Cd cd) {
        stateMachine.sendEvent(MessageBuilder.withPayload(Event.LOAD).setHeader(Variables.CD.toString(), cd).build());
    }

    public void play() {
        stateMachine.sendEvent(Event.PLAY);
    }

    public void stop() {
        stateMachine.sendEvent(Event.STOP);
    }

    public void pause() {
        stateMachine.sendEvent(Event.PAUSE);
    }

    public void close() {
        stateMachine.sendEvent(Event.CLOSE);
    }

    public void resume() {
        stateMachine.sendEvent(Event.RESUME);
    }

    public void eject() {
        stateMachine.sendEvent(Event.EJECT);
    }

    public void forward() {
        stateMachine
                .sendEvent(MessageBuilder
                        .withPayload(Event.FORWARD)
                        .setHeader(Headers.TRACKSHIFT.toString(), 1).build());
    }

    public void back() {
        stateMachine
                .sendEvent(MessageBuilder
                        .withPayload(Event.BACK)
                        .setHeader(Headers.TRACKSHIFT.toString(), 1).build());
    }

    public String getLdcStatus() {
        return cdStatus + " " + trackStatus;
    }

    //tag::snippetA[]
    @OnTransition(target = "BUSY")
    public void busy(ExtendedState extendedState) {
        Object cd = extendedState.getVariables().get(Variables.CD);
        if (cd != null) {
            cdStatus = ((Cd) cd).getName();
        }
    }
//end::snippetA[]

    @StatesOnTransition(target = State.PLAYING)
    public void playing(ExtendedState extendedState) {
        Object elapsed = extendedState.getVariables().get(Variables.ELAPSEDTIME);
        Object cd = extendedState.getVariables().get(Variables.CD);
        Object track = extendedState.getVariables().get(Variables.TRACK);
        if (elapsed instanceof Long && track instanceof Integer && cd instanceof Cd) {
            SimpleDateFormat format = new SimpleDateFormat("mm:ss");
            trackStatus = ((Cd) cd).getTracks()[((Integer) track)]
                    + " " + format.format(new Date((Long) elapsed));
        }
    }

    @StatesOnTransition(target = State.OPEN)
    public void open(ExtendedState extendedState) {
        cdStatus = "Open";
    }

    //tag::snippetB[]
    @StatesOnTransition(target = {State.CLOSED, State.IDLE})
    public void closed(ExtendedState extendedState) {
        Object cd = extendedState.getVariables().get(Variables.CD);
        if (cd != null) {
            cdStatus = ((Cd) cd).getName();
        } else {
            cdStatus = "No CD";
        }
        trackStatus = "";
    }
//end::snippetB[]
}
