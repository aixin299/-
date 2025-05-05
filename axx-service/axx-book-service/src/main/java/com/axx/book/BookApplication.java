package com.axx.book;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.axx.book.dao")
public class BookApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookApplication.class);
    }
}
