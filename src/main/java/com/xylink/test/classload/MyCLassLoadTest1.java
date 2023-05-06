package com.xylink.test.classload;

/**
 * @author 刘启航
 * @date 2023/4/11 13:30
 * @desc
 */
public class MyCLassLoadTest1 extends ClassLoader{

    public Class load(String name) throws ClassNotFoundException {
        Class clazz = this.loadClass(name);
        return clazz;
    }

    public static void main(String[] args) {
        MyCLassLoadTest1 m = new MyCLassLoadTest1();

        try {
            Class load1 = m.load("com.xylink.dto.CallBackStopDTO");
            Class load2 = m.load("com.xylink.dto.CallBackStopDTO");
            System.out.println(load1 == load2);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
