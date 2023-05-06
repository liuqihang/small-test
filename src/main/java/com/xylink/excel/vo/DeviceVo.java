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

    @ExcelProperty(value = "设备NO", index = 0)
   private String deviceNo;

    @ExcelProperty(value = "客户端版本", index = 1)
    private String appVersion;

    @ExcelProperty(value = "设备名称", index = 2)
    private String deviceName;

    @ExcelProperty(value = "accountNo账户", index = 3)
    private String employeeNo;

    @ExcelProperty(value = "用户名", index = 4)
    private String userName;

    @ExcelProperty(value = "手机号", index = 5)
    private String telephone;
}
