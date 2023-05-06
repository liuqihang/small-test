package com.xylink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.HashMap;

@SpringBootApplication
@EnableAsync
@EnableRetry
public class XylinkTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(XylinkTestApplication.class, args);
    }

}
