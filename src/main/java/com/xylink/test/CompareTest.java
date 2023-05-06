package com.xylink.test;

import com.alibaba.fastjson.JSON;

import java.text.Collator;
import java.util.*;

/**
 * @ClassName CompareTest
 * @Description TODO
 * @Author liuqihang
 * @Date 2022/8/15 12:36
 */
public class CompareTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("刘启航");
        list.add("刘德华2");
        list.add("刘德华6");
        list.add("张学友");
        list.add("刘德华1");
        list.add("温家宝");

        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                Collator instance = Collator.getInstance(Locale.CHINA);
                int compare = instance.compare(o1, o2);
                System.out.println(o1 + " " + o2 + " " + compare);
                return compare;
            }
        });
        System.out.println(JSON.toJSONString(list));
    }


}
