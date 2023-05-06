package com.xylink.test;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author liuqihang
 */
public class ListTest {

    public static List<Employee> initData(){
        List<Employee> employeeList = Lists.newArrayList();
        Employee employee1 = new Employee();
        employee1.setId(1).setEmployeeNo("123456").setName("刘启航1");
        Employee employee2 = new Employee();
        employee2.setId(2).setEmployeeNo("234567").setName("张学友");
        Employee employee3 = new Employee();
        employee3.setId(3).setEmployeeNo("345678").setName("刘德华1");
        Employee employee4 = new Employee();
        employee4.setId(4).setEmployeeNo("123456").setName("刘启航2");
        Employee employee5 = new Employee();
        employee5.setId(5).setEmployeeNo("345678").setName("刘德华2");

        employeeList.add(employee1);
        employeeList.add(employee2);
        employeeList.add(employee3);
        employeeList.add(employee4);
        employeeList.add(employee5);

        return employeeList;
    }

    @Test
    public void listTest() {
        List<Employee> employees = initData();
        ArrayList<Employee> collect = employees.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(
                                () -> new TreeSet<>(
                                        Comparator.comparing(Employee::getEmployeeNo)
                                )
                        ),
                        ArrayList::new
                )
        );
        System.out.println(JSON.toJSONString(collect));
        System.out.println(1 << 4);
//        Collections.binarySearch()
    }

}
