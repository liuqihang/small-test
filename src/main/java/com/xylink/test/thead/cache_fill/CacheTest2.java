package com.xylink.test.thead.cache_fill;

import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;
import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName CacheTest2
 * @Description TODO
 * @Author liuqihang
 * @Date 2022/8/31 10:41
 */
public class CacheTest2 extends Thread{

    static OperatingSystemMXBean mem = null;

    static {
        mem = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
    }

    public static String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
            +
            "是懂法守法手里的开发库山东矿机分汉口极地山东矿机分汉口极地" +
            "发山东矿机分海口市将阿道夫是肯定烽火科技瘦的风上水电费就好" +
            "时代峰峻撒旦法上看见待会反馈决胜巅峰已入口即溶获取接口胃口好山东矿机分和罗威尔" +
            "水淀粉分会计师给对方个就会就一4 hiUI㔿会计核算的接口返回开会说的解放后可见非开会时间的" +
            "打开了风景好岛咖啡恒生科技待会飞山东矿机分恒生科技安顿好副科级上岛咖啡花生壳多久恢复";

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        while(true){
            executorService.execute(()->{
                eatMemory();
                viewPercentage();
            });
            Thread.sleep(1500);
        }
    }

    public static void eatMemory(){
        CopyOnWriteArrayList list = new CopyOnWriteArrayList();
        long limit = 200000;
        for(int i = 0; i < limit; i++){
            list.add(randomChar(200));
        }
    }


    public static String randomChar(int length){
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(str.length());
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public static String transformation(long size){
        return size / 1024 / 1024 + "MB"+"   ";
    }

    /**
     * 获取内存总容量
     * @return
     */
    public static long getTotalMemory(){
        long totalMemorySize = mem.getTotalPhysicalMemorySize();
        return totalMemorySize;
    }

    /**
     * 获取可用内存容量
     * @return
     */
    public static long getFreeMemory(){
        long freeMemorySize = mem.getFreePhysicalMemorySize();
        return freeMemorySize;
    }

    public static void viewPercentage(){
        long freeMemory = getFreeMemory();
        long totalMemory = getTotalMemory();
        Double v = (new BigDecimal((float) freeMemory / totalMemory).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()) * 100;
        System.out.println("可用内存百分比:" + v + "%" + "   " + System.currentTimeMillis());
    }

}
