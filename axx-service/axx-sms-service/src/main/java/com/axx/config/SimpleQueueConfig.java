package com.axx.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimpleQueueConfig {
    /**
     * 定义简单队列名
     */
    private String simpleQueue = "axx.sms.queue";
    @Bean
    public Queue simpleQueue() {
        return new Queue(simpleQueue);
    }
}
