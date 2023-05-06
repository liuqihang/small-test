package com.xylink.excel;

import cn.hutool.core.util.CharsetUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.xylink.excel.vo.ExportVo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author liuqihang
 */
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
}
