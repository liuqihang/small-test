package com.xylink.excel.vo;

import lombok.Data;

import java.util.List;

/**
 * @author liuqihang
 */
@Data
public class ExportVo<T> {

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * sheet名称
     */
    private String sheetName;

    /**
     * 标题
     */
    private Class<T> head;

    /**
     * 数据
     */
    private List<T> data;

}
