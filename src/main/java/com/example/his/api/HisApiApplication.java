package com.example.his.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.his.api.db.mapper")
public class HisApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HisApiApplication.class, args);
    }

}
