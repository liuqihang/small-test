package com.xylink.sdk;

import com.xylink.config.SDKConfigMgr;
import com.xylink.util.Result;

import java.io.IOException;

public class SampleWithNormalMethod {
    //1、对企业id,clientId,clientSecret等参数进行赋值
    static final String ENTERPRISE_ID = "28617ab154b37d33c23ed5139b57a6dc2d8f5dc2";
    static final String CLIENT_ID = "MaOH94G6kxffqg7hHEQXhZQc";
    static final String CLIENT_SECRET = "xvUR0FyCTLVSbenosoBh8SDaVOQ5VQcG";
    static final String API_HOST = "https://sdkapi.xylink.com";

    //2、进行初始化，服务启动后初始化一次。注意能且仅能初始化一次！！
    static {
        SDKConfigMgr.initSDKConfig(API_HOST,CLIENT_ID,CLIENT_SECRET,ENTERPRISE_ID);
    }

    //3、以预约会议为例：
    public static void main(String[] args) throws IOException {
        // 预约会议
        Result<MyReminderMeetingResult> result = remindMeeting();
        System.out.println("预约：" + result);
        if (!result.isSuccess()) {
            System.out.println("预约会议失败");
            return;
        }
        //预约成功的返回
        MyReminderMeetingResult data = (MyReminderMeetingResult) result.getData();
        String meetingId = data.getMeetingId();
        String meetingRoomNumber = data.getMeetingRoomNumber();
        //根据预约会议id获取预约信息
        Result<ReminderMeetingDetailResult> detailResult = getRemindMeetingDetail(meetingId);
        System.out.println("获取预约：" + detailResult);
        if (!detailResult.isSuccess()) {
            System.out.println("获取预约会议失败");
            return;
        }
        ReminderMeetingDetailResult detail = (ReminderMeetingDetailResult) detailResult.getData();
        System.out.println("获取预约结果：" + detail);
    }

    static Result<MyReminderMeetingResult> remindMeeting() throws IOException {
        MyReminderMeeting reminderMeeting = new MyReminderMeeting();
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
        //从官网上找到想要的api，例如：https://sdk.xylink.com/api/rest/external/{version}/meetingreminders?enterpriseId=XXX&meetingLock=XXX&signature=XXX
        //我们需要的是：/api及其以后的和&signature=XXX之前的
        String uri = "/api/rest/external/v1/meetingreminders?enterpriseId=" + ENTERPRISE_ID;
        //SdkMethodV2代表使用的是签名算法2.0，SdkMethodV2代表使用的是签名算法1.0,请根据实际情况进行选择，签名算法1.0还需要传参token
        //GET、POST、PUT、DELETE分别提供了对应的sdkGet、sdkPost、sdkPut、sdkDelete，请根据实际情况进行切换
        //如需要使用签名1.0的get,则使用SdkMethodV1.sdkGet(uri);
        //Map.class可以根据实际的情况进行调整，比如下面的返回MyReminderMeetingResult.class等想要解析的对象
        return SdkMethodV2.sdkPost(uri, reminderMeeting, MyReminderMeetingResult.class);
    }

    static Result<ReminderMeetingDetailResult> getRemindMeetingDetail(String meetingId) throws IOException {
        //从官网上找到想要的api，例如：https://sdk.xylink.com/api/rest/external/{version}/meetingreminders?enterpriseId=XXX&meetingLock=XXX&signature=XXX
        //我们需要的是：/api及其以后的和&signature=XXX之前的
        String uri = "/api/rest/external/v1/meetingreminders/"+meetingId+"?enterpriseId=" + ENTERPRISE_ID;
        //SdkMethodV2代表使用的是签名算法2.0，SdkMethodV2代表使用的是签名算法1.0,请根据实际情况进行选择，签名算法1.0还需要传参token
        //GET、POST、PUT、DELETE分别提供了对应的sdkGet、sdkPost、sdkPut、sdkDelete，请根据实际情况进行切换
        //如需要使用签名1.0的get,则使用SdkMethodV1.sdkGet(uri);
        //Map.class可以根据实际的情况进行调整，比如下面的返回ReminderMeetingDetailResult.class等想要解析的对象
        return SdkMethodV2.sdkGet(uri, ReminderMeetingDetailResult.class);
    }

}
