package com.xylink.test.thead.cache_fill;

import com.sun.management.OperatingSystemMXBean;
import org.checkerframework.checker.units.qual.A;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName CacheTest
 * @Description TODO
 * @Author liuqihang
 * @Date 2022/8/30 16:06
 */
public class CacheTest {
    static OperatingSystemMXBean mem = null;

    static {
        mem = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
    }

    public static String file_path = "C:\\Users\\Vicky\\Desktop\\软通备忘\\PMP资料.zip";
    public static Double limit = 40.0;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("内存总容量：" + transformation(getTotalMemory()));

        ExecutorService executorService = Executors.newFixedThreadPool(20);
        int i = 0;
        while(true){
            executorService.execute(()->{
                try {
                    readFileToBufferV2(file_path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                eatMemoryStop();
            });
            Thread.sleep(6000);
            System.out.println("i=" + i++ + ", 可用内存容量：" + transformation(getFreeMemory()));
        }
//        System.gc();
//        System.out.println("回收完毕");
    }

    public static String transformation(long size){
        return size / 1024 / 1024 + "MB"+"   ";
    }

    public static boolean eatMemoryStop(){
        long freeMemory = getFreeMemory();
        long totalMemory = getTotalMemory();
        Double v = (new BigDecimal((float) freeMemory / totalMemory).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()) * 100;
        System.out.println("可用内存百分比:" + v + "%" + "   " + System.currentTimeMillis());
        if(v.compareTo(limit) < 1){
            return true;
        }else {
            return false;
        }
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

    public static void readFileToBufferV2(String filePath) throws IOException {
//        CopyOnWriteArrayList list = new CopyOnWriteArrayList();
        ArrayList list = new ArrayList();

        FileInputStream inputstream = new FileInputStream(filePath);
        StringBuffer buffer = new StringBuffer();
        String line;
        BufferedReader bufferreader = new BufferedReader(new InputStreamReader(
                inputstream));
        line = bufferreader.readLine();
        while (line != null) {
            buffer.append(line);
            line = bufferreader.readLine();
            list.add(line);
        }
    }


    private static void readFileToBuffer(String filePath) {
        FileReader fr = null;
        BufferedReader br = null;
        try {
            File source = new File(filePath);
            fr = new FileReader(source);
            br = new BufferedReader(fr);
            // 死循环
            while (true) {
                Object[] objects = br.lines().limit(10000).toArray();
                // 死循环的结束条件
                if (objects.length == 0) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            /*if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }*/
        }
    }

}
