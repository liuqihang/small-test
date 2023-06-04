package com.xylink.excel.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author 刘启航
 * @date 2023/5/15 11:42
 * @desc
 */
@Data
public class ProjectVo {

    @ExcelProperty(value = "金财子项目编号", index = 0)
    private String PRJ_CODE;

    @ExcelProperty(value = "ITM编号", index = 1)
    private String ITM_PRJ_CODE;
}
