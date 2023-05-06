package com.xylink.controller;

import com.alibaba.fastjson.JSON;
import com.xylink.model.ReminderMeeting;
import com.xylink.sdk.dating.ScheduleMeetingApi;
import com.xylink.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;


@RestController
@RequestMapping("/test")
@Slf4j
public class RemindMeetingController {


    //1、对企业id,clientId,clientSecret等参数进行赋值
    static final String ENTERPRISE_ID = "28617ab154b37d33c23ed5139b57a6dc2d8f5dc2";
    static final String CLIENT_ID = "MaOH94G6kxffqg7hHEQXhZQc";
    static final String CLIENT_SECRET = "xvUR0FyCTLVSbenosoBh8SDaVOQ5VQcG";
    static final String API_HOST = "https://sdkapi.xylink.com";

    @PostMapping("sendRemindMeeting")
    public String sendRemindMeeting(@RequestBody Map map) throws IOException {
        log.info("sendRemindMeeting param={}", JSON.toJSONString(map));
        Result result = remindMeeting();
        System.out.println("预约：" + result);
        if (!result.isSuccess()) {
            System.out.println("预约会议失败");
            return  null;
        }
        //预约成功的返回
        Map data = (Map) result.getData();
        String meetingId = data.get("meetingId").toString();
        String meetingRoomNumber = data.get("meetingRoomNumber").toString();
        return "success";
    }

    @PostMapping("callbackVideo")
//    public String callbackVideo(@RequestBody CallBackStopDTO callBackStopDTO) throws IOException {
    public String callbackVideo(@RequestBody Map map) throws IOException {
        log.info("callbackVideo param={}", JSON.toJSONString(map));

        return "callbackVideo success";
    }

    @PostMapping("convertCode")
    public String convertCode(@RequestBody Map map) throws IOException {
        log.info("convertCode param={}", JSON.toJSONString(map));

        return "convertCode success";
    }

    Result remindMeeting() throws IOException {
        ScheduleMeetingApi sma = new ScheduleMeetingApi();
        ReminderMeeting reminderMeeting = new ReminderMeeting();
        reminderMeeting.setTitle("我预约的会议");
        // 开始时间，2个min之后
        reminderMeeting.setStartTime(System.currentTimeMillis() + (1000 * 60 * 2));
        // 结束时间，4个min之后，预约会议时长2min
        reminderMeeting.setEndTime(System.currentTimeMillis() + (1000 * 60 * 4));
        reminderMeeting.setAddress("地中海");
        reminderMeeting.setAutoInvite(1);
        reminderMeeting.setPassword("2345");
        reminderMeeting.setAutoRecord(0);
        reminderMeeting.setMeetingRoomType(1);
        reminderMeeting.setOwner(1234);
        return sma.remindMeeting(ENTERPRISE_ID, null, reminderMeeting);
    }


}
