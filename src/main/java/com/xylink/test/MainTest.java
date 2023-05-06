package com.xylink.test;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import java.util.List;

public class MainTest {

    /*public static void main(String[] args) {
        String json = "{\"field4\":\"field4\",\"field2\":\"field2\",\"field3\":\"field3\",\"mobile\":\"field5\",\"address\":\"field6\",\"name\":\"field1\",\"field7\":\"field7\"}";

        Test test = JSON.parseObject(json, Test.class);

//        Test test = new Test();
//        test.setField1("field1");
//        test.setField2("field2");
//        test.setField3("field3");
//        test.setField4("field4");
//        test.setField5("field5");
//        test.setField6("field6");
//        test.setField7("field7");
        System.out.println(JSON.toJSONString(test));
    }*/

    public static void main(String[] args) {
        TestVo testVo = new TestVo();
        List<String> valueList = Lists.newArrayList();
        valueList.add("1");
        valueList.add("2");

        testVo.setTestList(valueList);

        System.out.println(System.identityHashCode(testVo.getTestList()));


        List<String> testList = testVo.getTestList();
        List<String> tempList = testList;
        System.out.println(System.identityHashCode(tempList));


        List<String> tempListNew = Lists.newArrayList();
        tempListNew.addAll(testList);
        System.out.println(System.identityHashCode(tempListNew));

    }
}
