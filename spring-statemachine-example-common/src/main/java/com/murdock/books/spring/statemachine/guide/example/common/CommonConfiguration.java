package com.murdock.books.spring.statemachine.guide.example.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * @author weipeng2k 2018年09月11日 下午13:48:45
 */
@Configuration
public class CommonConfiguration {

    @Bean
    public TestEventListener testEventListener() {
        return new TestEventListener();
    }

    @Bean
    public String stateChartModel() throws IOException {
        ClassPathResource model = new ClassPathResource("statechartmodel.txt");
        InputStream inputStream = model.getInputStream();
        Scanner scanner = new Scanner(inputStream);
        String content = scanner.useDelimiter("\\Z").next();
        scanner.close();
        return content;
    }

}