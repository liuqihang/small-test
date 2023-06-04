package com.xylink.excel.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author 刘启航
 * @date 2023/3/30 15:59
 * @desc
 */
@Data
public class DeviceVo {

    @ExcelProperty(value = "员工编号", index = 0)
   private String EMPLOYEE_NO;

    @ExcelProperty(value = "姓名", index = 1)
    private String USERNAME;

    @ExcelProperty(value = "手机号", index = 2)
    private String TELEPHONE;

    @ExcelProperty(value = "accountNo账户", index = 3)
    private String ACCOUNT_NO;

    @ExcelProperty(value = "部门", index = 4)
    private String DEPT;

    @ExcelProperty(value = "系统类别", index = 5)
    private String SYSTEM;

    @ExcelProperty(value = "最后一次登录时间", index = 6)
    private String LAST_LOGIN_TIME;

    @ExcelProperty(value = "职位", index = 7)
    private String POST_NM;
}
