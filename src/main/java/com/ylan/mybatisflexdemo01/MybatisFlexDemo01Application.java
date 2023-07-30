package com.ylan.mybatisflexdemo01;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Ylan
 * @date 2023-07-29 16:55
 */
@SpringBootApplication
@MapperScan("com.ylan.mybatisflexdemo01.mapper")
public class MybatisFlexDemo01Application {
    public static void main(String[] args) {
        SpringApplication.run(MybatisFlexDemo01Application.class, args);
    }
}