package com.xylink.test.thead.concurrent;

import cn.hutool.core.date.StopWatch;
import org.junit.Test;

import java.util.concurrent.*;

/**
 *@ClassName CountDownLatchTest
 *@Description TODO
 *@Author liuqihang
 *@Date 2022/7/28 10:38
 */
public class CountDownLatchTest {

    private static int time = 5 * 1000;

    public void sendSms(String threadName){
        System.out.println(threadName + " start send sms");
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(threadName + " end send sms");
    }

    @Test
    public void testSend(){
        int count = 4;

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        CountDownLatch countDownLatch = new CountDownLatch(count);
//        模拟发送短信 总耗时:20118

        for (int i = 0; i < count; i++){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        sendSms(Thread.currentThread().getName());
                    } finally {
                        countDownLatch.countDown();
                    }
                }
            }, i+"");
            thread.start();

//            sendSms(Thread.currentThread().getName());
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stopWatch.stop();
        System.out.println("模拟发送短信 总耗时:" + stopWatch.getTotalTimeMillis());

    }

}
