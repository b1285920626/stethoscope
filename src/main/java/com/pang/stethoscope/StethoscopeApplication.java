package com.pang.stethoscope;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.pang.stethoscope.mapper")
public class StethoscopeApplication {

    public static void main(String[] args) {
        SpringApplication.run(StethoscopeApplication.class, args);
    }

}
