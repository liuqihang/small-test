package com.xylink.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TestService {

    synchronized
    @Async
    public void test1(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("test1 thread name is:{}", Thread.currentThread().getName());
    }


    public void test2(){
        log.info("test2 thread name is:{}", Thread.currentThread().getName());
        test1();
        test3();
        test4();
    }

    @Async
    public void test3(){
        log.info("test3 thread name is:{}", Thread.currentThread().getName());
    }

    @Async
    public void test4(){
        log.info("test4 thread name is:{}", Thread.currentThread().getName());
    }
}
