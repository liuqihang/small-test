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
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 刘启航
 * @date 2023/3/29 15:31
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

    public static void main(String[] args) throws IOException {
        String text = Files.asCharSource(new File(INPUT_FILE_PATH), Charsets.UTF_8).read();
        List<List> lists = JSON.parseArray(text, List.class);
        System.out.println("数据总长度：" + lists.size());

        List<Object> dataList = new ArrayList<>();
        Map<Integer, String> objFieldMap = parseObjField(OrganVo.class);

        for (List<JSONObject> childList:
                lists) {
            //需要修改的地方
            OrganVo organVo = new OrganVo();

            for (int i = 0; i < childList.size(); i++) {
                String tempVal = childList.get(i).get(objFieldMap.get(i)) == null ? "" : childList.get(i).get(objFieldMap.get(i)).toString();
                try {
                    String methodName = "set" + objFieldMap.get(i);
                    Method method = OrganVo.class.getDeclaredMethod(methodName, String.class);
                    method.invoke(organVo, tempVal);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            dataList.add(organVo);
        }

        EasyExcel.write(new File(OUTPUT_FILE_PATH), OrganVo.class).sheet(StrUtil.isEmpty(SHEET_NAME)? "Sheet-1" : SHEET_NAME).doWrite(dataList);
        System.out.println("解析完成...");
    }


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
