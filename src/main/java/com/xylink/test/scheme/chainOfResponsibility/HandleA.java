package com.xylink.test.scheme.chainOfResponsibility;

/**
 * @ClassName HandleA
 * @Description TODO
 * @Author liuqihang
 * @Date 2022/7/22 15:52
 */
public class HandleA extends Handler{
    @Override
    protected void doHandle() {
        System.out.println("HandleA");
    }
}
