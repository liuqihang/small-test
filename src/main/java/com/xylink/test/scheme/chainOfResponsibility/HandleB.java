package com.xylink.test.scheme.chainOfResponsibility;

/**
 * @ClassName HandleB
 * @Description TODO
 * @Author liuqihang
 * @Date 2022/7/22 15:53
 */
public class HandleB extends Handler{
    @Override
    protected void doHandle() {
        System.out.println("HandleB");
    }
}
