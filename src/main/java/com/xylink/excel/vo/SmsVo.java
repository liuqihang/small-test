package com.xylink.excel.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author 刘启航
 * @date 2023/4/10 9:11
 * @desc
 */
@Data
public class SmsVo {

    @ExcelProperty(value = "总数", index = 0)
    private String totalCount;

    @ExcelProperty(value = "短信内容", index = 1)
    private String smsContent;
}
