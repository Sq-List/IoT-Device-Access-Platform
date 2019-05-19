package com.sqlist.clientweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = {"com.sqlist.clientweb.mapper"})
public class ClientWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientWebApplication.class, args);
    }

}
