package com.xylink.test;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;

public class Lqh0720Test {

    @Test
    public void copyArrayTest(){
        String[] elements = {"lqh1", "lqh2", "lqh3"};
        int len = elements.length;
        String[] newElements = Arrays.copyOf(elements, len + 1);
        newElements[len] = "lqh4";
        System.out.println(JSON.toJSONString(newElements));
    }


    @Test
    public void calculateTes(){
        BigDecimal[] num = new BigDecimal[]{
                new BigDecimal("35301954667"),
                new BigDecimal("12222822209"),
                new BigDecimal("979648407"),
                new BigDecimal("5572695536"),
                new BigDecimal("2281708118"),
                new BigDecimal("4377808728"),
                new BigDecimal("4203231754"),
                new BigDecimal("4328547836"),
                new BigDecimal("1335492079"),
        };
        List<BigDecimal> bigDecimals = Arrays.asList(num);
        bigDecimals.forEach(e -> {
            BigDecimal factor = new BigDecimal("1024");
            BigDecimal result = e.divide(factor)
                    .divide(factor)
                    .divide(factor)
                    .setScale(2, BigDecimal.ROUND_HALF_UP);
            System.out.println(result.toString());
        });
    }

    @Test
    public void addTest() {
        //33666.57
//        33666.56

        BigDecimal[] num = new BigDecimal[]{
                new BigDecimal("11656.59"),
                new BigDecimal("934.27"),
                new BigDecimal("5314.54"),
                new BigDecimal("2176.01"),
                new BigDecimal("4175.00"),
                new BigDecimal("4008.51"),
                new BigDecimal("4128.02"),
                new BigDecimal("1273.62")
        };
        List<BigDecimal> bigDecimals = Arrays.asList(num);
        BigDecimal decimal = bigDecimals.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println(decimal);


        OptionalDouble average = bigDecimals.stream().mapToDouble(BigDecimal::doubleValue).average();
        double asDouble = average.getAsDouble();
        System.out.println(asDouble);
    }


}
