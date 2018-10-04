package com.murdock.books.spring.statemachine.guide.example.persist;

import com.murdock.books.spring.statemachine.guide.example.common.AbstractStateMachineCommands;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

/**
 * @author weipeng2k 2018年10月04日 下午22:03:39
 */
@Component
public class StateMachineCommands extends AbstractStateMachineCommands<String, String> {

    @CliCommand(value = "sm event", help = "Sends an event to a state machine")
    public String event(@CliOption(key = { "", "event" }, mandatory = true, help = "The event") final String event) {
        getStateMachine().sendEvent(event);
        return "Event " + event + " send";
    }

}
