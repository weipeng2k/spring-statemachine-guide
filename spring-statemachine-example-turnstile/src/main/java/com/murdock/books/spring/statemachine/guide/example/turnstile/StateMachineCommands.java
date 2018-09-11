package com.murdock.books.spring.statemachine.guide.example.turnstile;

import com.murdock.books.spring.statemachine.guide.example.common.AbstractStateMachineCommands;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

/**
 * @author weipeng2k 2018年09月11日 下午13:51:32
 */
@Component
public class StateMachineCommands extends AbstractStateMachineCommands<State, Event> {

    @CliCommand(value = "sm event", help = "Sends an event to a state machine")
    public String event(@CliOption(key = { "", "event" }, mandatory = true, help = "The event") final Event event) {
        getStateMachine().sendEvent(event);
        return "Event " + event + " send";
    }

}