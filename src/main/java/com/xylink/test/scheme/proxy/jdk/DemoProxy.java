package com.xylink.test.scheme.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author 刘启航
 * @date 2023/4/8 16:48
 * @desc
 */
public class DemoProxy {

    public static Demo getProxy(Demo demo){
        Class<?>[] interfaces = demo.getClass().getInterfaces();
        ClassLoader loader = demo.getClass().getClassLoader();
        InvocationHandler h = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object result = null;
                try {
                    System.out.println(2);
                    result = method.invoke(demo, args);
                    System.out.println(3);
                } catch (Exception e) {
                } finally {
                }
                return result;
            }
        };
        System.out.println(1);
        Object proxy = Proxy.newProxyInstance(loader, interfaces, h);
        System.out.println(4);
        return (Demo) proxy;
    }

    public static void main(String[] args) {
        Demo proxy = DemoProxy.getProxy(new DemoImpl());
        System.out.println(5);
        System.out.println(proxy.appendStr("Liu", "QiHang"));
    }
}
