package com.opendog.opendogserver;


import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication()
@MapperScan("com.opendog.opendogserver.mapper")
public class OpendogServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpendogServerApplication.class, args);
    }

}
