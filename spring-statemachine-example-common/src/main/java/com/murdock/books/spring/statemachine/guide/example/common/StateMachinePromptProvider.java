package com.murdock.books.spring.statemachine.guide.example.common;

import org.springframework.core.annotation.Order;
import org.springframework.shell.plugin.PromptProvider;
import org.springframework.stereotype.Component;

/**
 * @author weipeng2k 2018年09月11日 下午13:49:42
 */
@Component
@Order(1)
public class StateMachinePromptProvider implements PromptProvider {

    @Override
    public String getPrompt() {
        return "sm>";
    }


    @Override
    public String getProviderName() {
        return "State machine prompt provider";
    }

}
