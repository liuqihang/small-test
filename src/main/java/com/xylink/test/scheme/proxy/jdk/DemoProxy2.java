package com.xylink.test.scheme.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author 刘启航
 * @date 2023/4/13 10:38
 * @desc
 */
public class DemoProxy2 implements InvocationHandler{

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        try {
            System.out.println(2);
//            result = method.invoke(demo, args);
            System.out.println(3);
        } catch (Exception e) {
        } finally {
        }
        return result;
    }


}
