package com.murdock.books.spring.statemachine.guide.example.persist;

import com.alibaba.druid.pool.DruidDataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;

import java.sql.SQLException;
import javax.sql.DataSource;

/**
 * @author weipeng2k 2018年10月04日 下午22:07:33
 */
@Configuration
public class ApplicationConfig {

    @Bean
    public PersistStateMachineHandler persistStateMachineHandler(StateMachine<String, String> stateMachine) {
        PersistStateMachineHandler persistStateMachineHandler = new PersistStateMachineHandler(stateMachine);
        return persistStateMachineHandler;
    }

    @Bean
    public PersistStateChangeListener persistStateChangeListener() {
        return new JdbcPersistStateChangeListener();
    }

    @Bean
    public DataSource dataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://192.168.50.102:13306/orders");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("root");
        try {
            druidDataSource.init();
            return druidDataSource;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
