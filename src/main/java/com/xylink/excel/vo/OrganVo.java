package com.xylink.excel.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author 刘启航
 * @date 2023/3/29 15:46
 * @desc
 */
@Data
public class OrganVo {

    @ExcelProperty(value = "机构编码", index = 0)
    private String ORG_NO;

    @ExcelProperty(value = "机构名称", index = 1)
    private String ORG_NAME;



//    @ExcelProperty(value = "层级", index = 2)
//    private String organLevel;
}
