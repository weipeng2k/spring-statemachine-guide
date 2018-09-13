package com.murdock.books.spring.statemachine.guide.example.cdplayer;

import org.springframework.statemachine.annotation.OnTransition;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author weipeng2k 2018年09月13日 下午21:12:10
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@OnTransition
public @interface StatesOnTransition {
    State[] source() default {};

    State[] target() default {};
}
