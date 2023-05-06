package com.xylink.test.atomic;

import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {



    public static void main(String[] args) {

        CountTest countTest = new CountTest();
        Vector<Thread> threadVector = new Vector<Thread>();

        for (int i = 0; i < 100000; i ++){
           Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
//                    System.out.println(Thread.currentThread().getName() + "增加前：" + countTest.getCounter());
                    countTest.incrementCounter();
//                    System.out.println(Thread.currentThread().getName() + "增加后：" + countTest.getCounter());
                }
            }, i+"");
            threadVector.add(thread);
            thread.start();
        }
        threadVector.forEach( thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("最后结果:" + countTest.getCounter());

    }


}
