package com.murdock.books.spring.statemachine.guide.example.cdplayer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author weipeng2k 2018年09月13日 下午21:28:15
 */
@Configuration
public class Config {
    @Bean
    public CdPlayer cdPlayer() {
        return new CdPlayer();
    }

    @Bean
    public Library library() {
        return Library.buildSampleLibrary();
    }
}
