package com.murdock.books.spring.statemachine.guide.machine.id;

import com.murdock.books.spring.statemachine.guide.machine.id.event.Event;
import com.murdock.books.spring.statemachine.guide.machine.id.state.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;

/**
 * @author weipeng2k 2018年08月26日 下午18:47:06
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private StateMachineFactory<State, Event> stateMachineFactory;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    @SuppressWarnings("ALL")
    public void run(String... args) throws Exception {
        StateMachine<State, Event> stateMachine = stateMachineFactory.getStateMachine();
        StateMachine<State, Event> stateMachine1 = stateMachineFactory.getStateMachine("my-machine-v1");
        System.out.println(stateMachine == stateMachine1);
        System.out.println(stateMachine);
        System.out.println(stateMachine1);

        stateMachine.sendEvent(Event.START);

        System.out.println("============================================");

        StateMachine<State, Event> stateMachine2 = stateMachineFactory.getStateMachine("my-machine-v2");

        System.out.println("============================================");

        stateMachine2.sendEvent(Event.START);
    }

}
