package com.example.his.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author Ocean_jhl
 */
@SpringBootApplication
@ServletComponentScan // 识别创建的Filter过滤器
@EnableAsync
@MapperScan("com.example.his.api.db.mapper")
public class HisApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HisApiApplication.class, args);
    }

}
