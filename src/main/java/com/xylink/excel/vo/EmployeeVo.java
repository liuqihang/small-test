package com.xylink.excel.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author 刘启航
 * @date 2023/4/17 17:25
 * @desc
 */
@Data
public class EmployeeVo {

    @ExcelProperty(value = "姓名", index = 0)
    private String userName;

    @ExcelProperty(value = "手机号", index = 1)
    private String telephone;

    @ExcelProperty(value = "职务", index = 2)
    private String postNm;

    @ExcelProperty(value = "机构名称", index = 3)
    private String organName;

    @ExcelProperty(value = "所属机构全路径", index = 4)
    private String path;
}
