package com.xylink.test;

import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.xylink.excel.vo.OrganVo;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 刘启航
 * @date 2023/5/8 09:14
 * @desc
 */
public class SqlQueryResultParse {

    /**
     * 文件输入路径
     */
    private static String INPUT_FILE_PATH = "C:\\Users\\Vicky\\Downloads\\prj_info_org.txt";

    /**
     * 文件输出路径
     */
    private static String OUTPUT_FILE_PATH = "C:\\Users\\Vicky\\Documents\\运维中心导出\\所属事业群数据.xlsx";

    /**
     * Sheet名称
     */
    private static String SHEET_NAME = "所属事业群数据";

    public static void main(String[] args) {
        //待变 坐标，只需替换Class即可, 替换对象的属性值需要和解析文件内一致（这里偷个懒,就不按照规范命名了）
        parseAndWriteData(OrganVo.class);
    }

    private static void parseAndWriteData(Class clazz){
        if(clazz== null){
            return ;
        }

        String text = null;
        try {
            text = Files.asCharSource(new File(INPUT_FILE_PATH), Charsets.UTF_8).read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<List> lists = JSON.parseArray(text, List.class);
        System.out.println("数据总长度：" + lists.size());

        List<Object> dataList = new ArrayList<>();
        Map<Integer, String> objFieldMap = parseObjField(clazz);

        for (List<JSONObject> childList:
                lists) {
            try {
                Constructor declaredConstructor = clazz.getDeclaredConstructor();
                declaredConstructor.setAccessible(true);
                Object obj = declaredConstructor.newInstance();

                for (int i = 0; i < childList.size(); i++) {
                    String tempVal = childList.get(i).get(objFieldMap.get(i)) == null ? "" : childList.get(i).get(objFieldMap.get(i)).toString();
                    String methodName = "set" + objFieldMap.get(i);
                    Method method = clazz.getDeclaredMethod(methodName, String.class);
                    method.invoke(obj, tempVal);
                }
                dataList.add(obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        EasyExcel.write(new File(OUTPUT_FILE_PATH), clazz).sheet(StrUtil.isEmpty(SHEET_NAME)? "Sheet-1" : SHEET_NAME).doWrite(dataList);
        System.out.println("解析完成...");
    }

    /**
     * 解析指定类 属性上ExcelProperty注解的index值和属性名称  转换为键值对
     * @param clazz
     * @param <T>
     * @return
     */
    private static <T> Map<Integer, String> parseObjField(Class clazz){
        System.out.println(clazz.getCanonicalName());
        Map<Integer, String> resultMap = new HashMap<>(16);
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            ExcelProperty[] excelProperties = field.getAnnotationsByType(ExcelProperty.class);
            for (ExcelProperty excelProperty : excelProperties) {
                resultMap.put(excelProperty.index(), field.getName());
            }
        }
        return resultMap;
    }

}
