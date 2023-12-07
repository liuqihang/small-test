package com.xylink.excel.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.xylink.excel.CityExcelSelectedImpl;
import com.xylink.excel.ExcelSelected;
import lombok.Data;

/**
 * @ClassName HonorVo
 * @Description TODO
 * @Author liuqihang
 * @Date 2022/8/8 10:41
 */
@Data
public class HonorVo {

    @ExcelProperty(value = "年度", index = 0)
    private String years;

    @ExcelProperty(value = "工号", index = 1)
    private String employeeNo;

    @ExcelProperty(value = "姓名", index = 2)
    private String name;

    @ExcelProperty(value = "项目名称", index = 3)
    @ExcelSelected(source = {"男","女"})
    private String projectName;

    @ExcelProperty(value = "奖项名称", index = 4)
    @ExcelSelected(sourceClass = CityExcelSelectedImpl.class)
    private String honorName;

    @ExcelProperty(value = "参评类别", index = 5)
    private String categoryName;

    @ExcelProperty(value = "获奖机构", index = 6)
    private String organName;

    @ExcelProperty(value = "项目摘要", index = 7)
    private String projectSummary;

}
