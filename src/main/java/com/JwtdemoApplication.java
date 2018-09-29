package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import tk.mybatis.spring.annotation.MapperScan;

@EnableCaching
@MapperScan("com.mapper")
@SpringBootApplication
public class JwtdemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(JwtdemoApplication.class, args);
    }

}
