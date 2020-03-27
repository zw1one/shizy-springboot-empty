package com.shizy;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})//不用数据库 排除啦
public class ShizyApplication {
    public static void main(String[] args) {

//        System.out.println("========================");
//        System.out.println("docker作业：石中玉");
//        System.out.println("========================");

        SpringApplication.run(ShizyApplication.class, args);
    }
}
