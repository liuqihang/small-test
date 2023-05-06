package com.xylink.dto;

import lombok.Data;

@Data
public class CallBackStopDTO {

    private String callerNumber;
    private String calleeNumber;
    private String callerType;
    private String calleeType;
    private String callStatus;
    private Long time;
    private Boolean caller;
    private Integer userCountOfSameConf;
    private String callerId;
    private String calleeId;
    private String callerName;
    private String calleeName;
    private String callerExternalUserId;
    private String calleeExternalUserId;
    private Integer callerDeviceType;
    private Integer calleeDeviceType;
    private String meetingId;


}
