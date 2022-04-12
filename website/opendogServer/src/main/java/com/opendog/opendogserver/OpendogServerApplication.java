package com.opendog.opendogserver;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication()
@MapperScan("com.opendog.opendogserver.mapper")
public class OpendogServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpendogServerApplication.class, args);
    }

}
