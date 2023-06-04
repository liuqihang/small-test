package com.xylink.excel.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author 刘启航
 * @date 2023/6/1 14:42
 * @desc
 */
@Data
public class TxlData {

    @ExcelProperty(value = "UASS账户", index = 0)
    private String accountNo;

    @ExcelProperty(value = "人员编码", index = 1)
    private String employeeNo;

    @ExcelProperty(value = "人员名称", index = 2)
    private String userName;

    @ExcelProperty(value = "状态", index = 3)
    private String status;

    @ExcelProperty(value = "邮箱", index = 4)
    private String email;

    @ExcelProperty(value = "手机", index = 5)
    private String telephone;

    @ExcelProperty(value = "岗位", index = 6)
    private String postName;

    @ExcelProperty(value = "性别", index = 7)
    private String gender;

    @ExcelProperty(value = "机构编码", index = 8)
    private String organNo;

    @ExcelProperty(value = "机构名称", index = 9)
    private String organName;

    @ExcelProperty(value = "上级人员编码", index = 10)
    private String parentEmployeeNo;

    @ExcelProperty(value = "上级人员名称", index = 11)
    private String parentEmployeeName;

    @ExcelProperty(value = "离职日期", index = 12)
    private String leaveDate;
}
