package com.xylink.test.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName CountTest
 * @Description TODO
 * @Author liuqihang
 * @Date 2022/7/27 17:14
 */
public class CountTest {

    private AtomicInteger atomicInteger = new AtomicInteger();
//    private int atomicInteger = 0;

    public int incrementCounter() {
        return atomicInteger.incrementAndGet();
//        return atomicInteger += 1;
    }

    public int getCounter() {
        return atomicInteger.get();
//        return atomicInteger;
    }
}
