package com.xylink.test.scheme.chainOfResponsibility;

import cn.hutool.core.date.StopWatch;

/**
 * @ClassName ApplicationMain
 * @Description TODO
 * @Author liuqihang
 * @Date 2022/7/22 15:54
 */
public class ApplicationMain {

    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        HandleChain chain = new HandleChain();
        chain.addHandler(new HandleA());
        chain.addHandler(new HandleB());
        chain.addHandler(new HandleC());
        chain.handle();
        stopWatch.stop();
        System.out.println("耗时：" + stopWatch.getTotalTimeMillis());
    }
}
