package com.murdock.books.spring.statemachine.guide.example.persist;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author weipeng2k 2018年10月03日 下午23:27:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DbInitTest.Config.class)
@TestConfiguration("classpath:test-application.properties")
@EnableAutoConfiguration
public class DbInitTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void init() {
        Assert.assertNotNull(jdbcTemplate);
    }

    @Configuration
    static class Config {

    }
}
