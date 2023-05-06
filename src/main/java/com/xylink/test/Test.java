package com.xylink.test;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class Test {

    @JSONField(ordinal = 1)
    private String name;
    private Integer field1Value;

    @JSONField(ordinal = 2)
    private String field2;
    private Integer field2Value;

    @JSONField(ordinal = 3)
    private String field3;
    private Integer field3Value;

    @JSONField(ordinal = 4)
    private String field4;
    private Integer field4Value;

    @JSONField(ordinal = 5)
    private String mobile;
    private Integer field5Value;

    @JSONField(ordinal = 6)
    private String address;
    private Integer field6Value;

    @JSONField(ordinal = 7)
    private String field7;
    private Integer field7Value;

    private int i = 0;



    public void setMobile(String field2){
        this.mobile = field2;
        this.field5Value = ++i;
    }

    public void setAddress(String field3){
        this.address = field3;
        this.field6Value = ++i;
    }


    public void setName(String field1){
        this.name = field1;
        this.field1Value = ++i;
    }

    public void setField2(String field2){
        this.field2 = field2;
        this.field2Value = ++i;
    }

    public void setField3(String field3){
        this.field3 = field3;
        this.field3Value = ++i;
    }

    public void setField4(String field1){
        this.field4 = field1;
        this.field4Value = ++i;
    }


    public void setField7(String field1){
        this.field7 = field1;
        this.field7Value = ++i;
    }
}
