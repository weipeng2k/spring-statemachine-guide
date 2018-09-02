package com.murdock.books.spring.statemachine.guide.basic;

import com.murdock.books.spring.statemachine.guide.basic.event.Events;
import com.murdock.books.spring.statemachine.guide.basic.state.States;
import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.statemachine.StateMachine;

/**
 * @author weipeng2k 2018年08月26日 下午18:47:06
 */
@SpringBootApplication
public class Application implements CommandLineRunner, ApplicationContextAware {

    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    @SuppressWarnings("ALL")
    public void run(String... args) throws Exception {
        StateMachine<States, Events> stateMachine = (StateMachine<States, Events>) applicationContext.getBean(
                "stateMachine", StateMachine.class);
        StateMachine<States, Events> stateMachine2 = (StateMachine<States, Events>) applicationContext.getBean(
                "stateMachine2");
        System.out.println(stateMachine == stateMachine2);
        stateMachine.sendEvent(Events.E1);
        stateMachine.sendEvent(Events.E2);

        stateMachine2.sendEvent(Events.E2);
        stateMachine2.sendEvent(Events.E1);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
