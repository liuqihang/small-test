package com.xylink.excel.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author 刘启航
 * @date 2023/5/28 21:06
 * @desc
 */
@Data
public class TaskInfoVo {

    @ExcelProperty(value = "任务名称", index = 0)
    private String TASK_NAME;

    @ExcelProperty(value = "任务编码", index = 1)
    private String TASK_NO;

    @ExcelProperty(value = "类型编码", index = 2)
    private String TASK_TYPE_CODE;
}
