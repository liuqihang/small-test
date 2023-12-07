package com.xylink.excel;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.IterUtil;
import cn.hutool.core.util.CharsetUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.util.StringUtils;
import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.springframework.http.HttpHeaders;
import com.xylink.excel.vo.ExportVo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author liuqihang
 */
@Slf4j
public class EasyExcelUtil {

    public static void exportExcel(HttpServletResponse response, ExportVo exportVo) throws IOException {

        String name = URLEncoder.encode(exportVo.getFileName(), CharsetUtil.UTF_8).replace("\\+", "%20");
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding(CharsetUtil.UTF_8);
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + name + ExcelTypeEnum.XLSX.getValue());
        EasyExcel.write(
                response.getOutputStream(),
                exportVo.getHead()
        ).sheet(exportVo.getSheetName())
                .doWrite(exportVo.getData());
    }

    public static <T> List<T> readExcel(InputStream inputStream, Class<T> clazz){
        return EasyExcel.read(inputStream).head(clazz).autoTrim(true).sheet().doReadSync();
    }

    /**
     * 导出单sheet页且sheet页中含有下拉框的excel文件
     *
     * @param response  HttpServletResponse
     * @param fileName  文件名
     * @param sheetName sheet页名
     * @param data      要导出的数据
     */
    public static <T> void writeExcelBySelect(HttpServletResponse response, String fileName, String sheetName, List<T> data, Class clazz) {
        try {
            encodeFileName(response, fileName);
            Map<Integer, ExcelSelected> selectedMap = resolveSelectedAnnotation(clazz);
            EasyExcel.write(response.getOutputStream(), clazz)
                    .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                    .registerWriteHandler(selectedSheetWriteHandler(selectedMap))
                    .sheet(StringUtils.isEmpty(sheetName) ? "Sheet1" : sheetName)
                    .doWrite(data);
        } catch (IOException e) {
            log.error("导出excel文件异常", e);
        }
    }

    /**
     * 设置文件名
     *
     * @param response HttpServletResponse
     * @param fileName 文件名
     */
    private static void encodeFileName(HttpServletResponse response, String fileName) throws UnsupportedEncodingException {
        fileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.name());
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment;filename=\"%s\"", fileName + ".xlsx"));
        response.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache");
        response.setHeader(HttpHeaders.PRAGMA, "no-cache");
        response.setDateHeader(HttpHeaders.EXPIRES, -1);
    }

    /**
     * 解析表头类中的下拉注解
     *
     * @param head 表头类
     * @return Map<下拉框列索引, 下拉框内容> map
     */
    private static <T> Map<Integer, ExcelSelected> resolveSelectedAnnotation(Class<T> head) {
        Map<Integer, ExcelSelected> selectedMap = new HashMap<>(16);
        Field[] fields = head.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            ExcelSelected selected = field.getAnnotation(ExcelSelected.class);
            ExcelProperty property = field.getAnnotation(ExcelProperty.class);
            if (selected != null) {
                if (property != null && property.index() >= 0) {
                    selectedMap.put(property.index(), selected);
                }
            }
        }
        return selectedMap;
    }

    /**
     * 为excel创建下拉框
     *
     * @param selectedMap 下拉框配置数据 Map<下拉框列索引, 下拉框内容>
     * @return intercepts handle sheet creation
     */
    private static SheetWriteHandler selectedSheetWriteHandler(Map<Integer, ExcelSelected> selectedMap) {
        return new SheetWriteHandler() {
            @Override
            public void beforeSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {

            }

            @Override
            public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
                Sheet sheet = writeSheetHolder.getSheet();
                DataValidationHelper helper = sheet.getDataValidationHelper();
                selectedMap.forEach((k, v) -> {
                    // 获取固定下拉框的内容
                    List<String> source = new ArrayList<>();
                    if (v.source().length > 0) {
                        source.addAll(Arrays.asList(v.source()));
                    }
                    // 获取动态下拉框的内容
                    Class<? extends ExcelDynamicSelect> sourceClass = v.sourceClass();
                    try {
                        ExcelDynamicSelect excelDynamicSelect = sourceClass.newInstance();
                        String[] dynamicSelectSource = excelDynamicSelect.getSource();
                        if (dynamicSelectSource != null && dynamicSelectSource.length > 0) {
                            source.addAll(Arrays.asList(dynamicSelectSource));
                        }
                    } catch (InstantiationException | IllegalAccessException e) {
                        log.error("解析动态下拉框数据异常", e);
                    }
                    if (CollUtil.isNotEmpty(source)) {
                        CellRangeAddressList rangeList = new CellRangeAddressList(v.firstRow(), v.lastRow(), k, k);
                        DataValidationConstraint constraint = helper.createExplicitListConstraint(source.toArray(new String[0]));
                        DataValidation validation = helper.createValidation(constraint, rangeList);
                        validation.setErrorStyle(DataValidation.ErrorStyle.STOP);
                        validation.setShowErrorBox(true);
                        validation.setSuppressDropDownArrow(true);
                        validation.createErrorBox("提示", "请输入下拉选项中的内容");
                        sheet.addValidationData(validation);
                    }
                });
            }
        };
    }

}
