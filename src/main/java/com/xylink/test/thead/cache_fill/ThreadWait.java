package com.xylink.test.thead.cache_fill;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName ThreadWait
 * @Description TODO
 * @Author liuqihang
 * @Date 2022/8/30 19:40
 */
public class ThreadWait extends Thread{

    public static void main(String[] args) {
        ThreadWait threadWait = new ThreadWait();
        threadWait.exeMain();
    }

    public synchronized void exeMain(){
        AtomicInteger i = new AtomicInteger(5);
        i.decrementAndGet();
        if(i.get() < 1){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
