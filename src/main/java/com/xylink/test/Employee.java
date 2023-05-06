package com.xylink.test;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Data
@Accessors(chain = true)
public class Employee {

    private Integer id;

    private String name;

    private String employeeNo;

    public static void main(String[] args) throws Exception {
        Employee employee = new Employee();
        employee.setId(12);
        employee.setEmployeeNo("70091");
        employee.setName("刘启航");

        Field[] declaredFields = Employee.class.getDeclaredFields();
        for (int i =0; i< declaredFields.length; i++){
//            System.out.println(declaredFields[i].getName());

            Object fieldValueByObject = getFieldValueByObject(employee, declaredFields[i].getName());
            System.out.println(fieldValueByObject);

        }

    }


    public static Object getFieldValueByObject(Object object, String attributes) throws Exception {
        attributes =  StrUtil.upperFirst(attributes);

        Method m = object.getClass().getMethod("get" + attributes);
        Object val = m.invoke(object);
        return val;
    }

}
