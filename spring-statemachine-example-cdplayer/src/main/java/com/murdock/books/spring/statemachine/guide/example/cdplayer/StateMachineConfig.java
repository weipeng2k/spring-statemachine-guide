package com.murdock.books.spring.statemachine.guide.example.cdplayer;

import org.springframework.context.annotation.Bean;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.ExtendedState;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.guard.Guard;

import java.util.Map;

/**
 * @author weipeng2k 2018年09月13日 下午19:59:38
 */
@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<State, Event> {

    @Override
    public void configure(StateMachineStateConfigurer<State, Event> states) throws Exception {
        states.withStates()
                .initial(State.IDLE)
                .state(State.IDLE).and()
                    .withStates()
                        .parent(State.IDLE)
                        .initial(State.CLOSED)
                        .state(State.CLOSED, closeAction())
                        .state(State.OPEN).and()
                .withStates()
                    .state(State.BUSY).and()
                    .withStates()
                        .parent(State.BUSY)
                        .initial(State.PLAYING)
                        .state(State.PLAYING)
                        .state(State.PAUSED);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<State, Event> transitions) throws Exception {
        transitions
                .withExternal()
                    .source(State.CLOSED).target(State.OPEN)
                    .event(Event.EJECT)
                    .and()
                .withExternal()
                    .source(State.OPEN).target(State.CLOSED)
                    .event(Event.CLOSE)
                    .and()
                .withExternal()
                    .source(State.OPEN).target(State.CLOSED)
                    .event(Event.PLAY)
                    .and()
                .withExternal()
                    .source(State.PLAYING).target(State.PAUSED)
                    .event(Event.PAUSE)
                    .and()
                .withExternal()
                    .source(State.PAUSED).target(State.PLAYING)
                    .event(Event.RESUME)
                    .and()
                .withExternal()
                    .source(State.IDLE).target(State.BUSY)
                    .event(Event.PLAY)
                    .action(playAction())
                    .guard(playGuard())
                    .and()
                .withExternal()
                    .source(State.BUSY).target(State.IDLE)
                    .event(Event.STOP)
                    .and()
                .withInternal()
                    .source(State.PLAYING)
                    .action(playingAction())
                    .timer(1000)
                    .and()
                .withInternal()
                    .source(State.PLAYING).event(Event.BACK)
                    .action(backAction())
                    .and()
                .withInternal()
                    .source(State.PLAYING).event(Event.FORWARD)
                    .action(forwardAction())
                    .and()
                .withInternal()
                    .source(State.OPEN).event(Event.LOAD)
                    .action(loadAction());
    }

    @Bean
    public Guard<State, Event> playGuard() {
        return context -> {
            ExtendedState extendedState = context.getExtendedState();
            return extendedState.getVariables().get(Variables.CD) != null;
        };
    }

    @Bean
    public Action<State, Event> playAction() {
        return context -> {
            context.getExtendedState().getVariables().put(Variables.ELAPSEDTIME, 0l);
            context.getExtendedState().getVariables().put(Variables.TRACK, 0);
        };
    }

    @Bean
    public Action<State, Event> loadAction() {
        return context -> {
            Object cd = context.getMessageHeader(Variables.CD);
            context.getExtendedState().getVariables().put(Variables.CD, cd);
        };
    }

    @Bean
    public Action<State, Event> forwardAction() {
        return context -> {
            Map<Object, Object> variables = context.getExtendedState().getVariables();
            Object trackshift = context.getMessageHeader(Headers.TRACKSHIFT.toString());
            Object track = variables.get(Variables.TRACK);
            Object cd = variables.get(Variables.CD);
            if (trackshift instanceof Integer && track instanceof Integer && cd instanceof Cd) {
                int next = ((Integer)track) + ((Integer)trackshift);
                if (next >= 0 &&  ((Cd)cd).getTracks().length > next) {
                    variables.put(Variables.ELAPSEDTIME, 0l);
                    variables.put(Variables.TRACK, next);
                } else if (((Cd)cd).getTracks().length <= next) {
                    context.getStateMachine().sendEvent(Event.STOP);
                }
            }
        };
    }


    @Bean
    public Action<State, Event> backAction() {
        return context -> {
            Map<Object, Object> variables = context.getExtendedState().getVariables();
            Object trackshift = context.getMessageHeader(Headers.TRACKSHIFT.toString());
            Object track = variables.get(Variables.TRACK);
            Object cd = variables.get(Variables.CD);
            if (trackshift instanceof Integer && track instanceof Integer && cd instanceof Cd) {
                int next = ((Integer)track) - ((Integer)trackshift);
                if (next >= 0 &&  ((Cd)cd).getTracks().length > next) {
                    variables.put(Variables.ELAPSEDTIME, 0l);
                    variables.put(Variables.TRACK, next);
                } else if (((Cd)cd).getTracks().length <= next) {
                    context.getStateMachine().sendEvent(Event.STOP);
                }
            }
        };
    }

    @Bean
    public Action<State, Event> closeAction() {
        return context -> {
            if (context.getTransition() != null
                    && context.getEvent() == Event.PLAY
                    && context.getTransition().getTarget().getId() == State.CLOSED
                    && context.getExtendedState().getVariables().get(Variables.CD) != null) {
                context.getStateMachine().sendEvent(Event.PLAY);
            }
        };
    }

    @Bean
    public Action<State, Event> playingAction() {
        return context -> {
            Map<Object, Object> variables = context.getExtendedState().getVariables();
            Object elapsed = variables.get(Variables.ELAPSEDTIME);
            Object cd = variables.get(Variables.CD);
            Object track = variables.get(Variables.TRACK);
            if (elapsed instanceof Long) {
                long e = ((Long)elapsed) + 1000l;
                if (e > ((Cd) cd).getTracks()[((Integer) track)].getLength()*1000) {
                    context.getStateMachine().sendEvent(MessageBuilder
                            .withPayload(Event.FORWARD)
                            .setHeader(Headers.TRACKSHIFT.toString(), 1).build());
                } else {
                    variables.put(Variables.ELAPSEDTIME, e);
                }
            }
        };
    }
}
