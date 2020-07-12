package com.shizy;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})//不用数据库 排除啦
public class ShizyApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShizyApplication.class, args);
    }
}
