package com.xylink.sdk;

import cn.hutool.core.net.URLDecoder;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.xylink.config.SDKConfigMgr;
import com.xylink.model.*;
import com.xylink.model.callback.CallbackEvent;
import com.xylink.model.callback.ExternalCallback;
import com.xylink.sdk.callback.CallBacksApi;
import com.xylink.sdk.conferenceControl.ConferenceControlApi;
import com.xylink.sdk.conferenceControl.CreateMeetingApi;
import com.xylink.sdk.conferenceControl.MeetingStatisticApi;
import com.xylink.sdk.dating.ScheduleMeetingApi;
import com.xylink.sdk.device.DeviceStatusApi;
import com.xylink.sdk.enterpriseNemo.EnterpriseNemoApi;
import com.xylink.sdk.vod.VodApi;
import com.xylink.util.Result;
import org.junit.Test;

import java.nio.charset.Charset;
import java.util.*;

/**
 * Unit test for simple App.
 */
public class AppTest {
    //1、对企业id,clientId,clientSecret等参数进行赋值
//    static final String ENTERPRISE_ID = "28617ab154b37d33c23ed5139b57a6dc2d8f5dc2";
//    static final String CLIENT_ID = "MaOH94G6kxffqg7hHEQXhZQc";
//    static final String CLIENT_SECRET = "xvUR0FyCTLVSbenosoBh8SDaVOQ5VQcG";

    //生产
    static final String ENTERPRISE_ID = "83d1728122c3b68bd8763487d1d0fde8365a3f74";
    static final String CLIENT_ID = "V4ig1ECpA86H39mFB1J0DTQD";
    static final String CLIENT_SECRET = "3xTXChDuatZMTyF2sJCMxADBJx5ZOJoK";

    static final String API_HOST = "https://sdkapi.xylink.com";
    static final String DEVICE_NO = "694364";
//    static final String DEVICE_NO = "272758";

    //2、进行初始化，服务启动后初始化一次。注意能且仅能初始化一次！！
    static {
        SDKConfigMgr.initSDKConfig(API_HOST,CLIENT_ID,CLIENT_SECRET,ENTERPRISE_ID);
    }

    @Test
    public void meetingreminders() throws Exception {

//        meetingApi.
    }


    /**
     * 创建SDK云会议室号
     */
    @Test
    public void createMeetingV2() throws Exception {
        SdkCloudMeetingRoomRequest request = new SdkCloudMeetingRoomRequest();
        request.setMeetingName("测试云会议室"+ new Random().nextInt(100));
        request.setStartTime(System.currentTimeMillis());
        request.setEndTime(System.currentTimeMillis()+(1000 * 60 * 6));

        CreateMeetingApi meetingApi = new CreateMeetingApi();
        SdkMeeting meetingV2 = meetingApi.createMeetingV2(ENTERPRISE_ID, null, request);
        System.out.println(JSONObject.toJSONString(meetingV2));
        //{"controlPassword":"820520","meetingNumber":"910007923678","shareUrl":"https://cloud.xylink.com/page/j/T5QIS3AF"}
    }

    /**
     *按云会议室号获取会议室信息
     * @throws Exception
     */
    @Test
    public void getMeetingInfo() throws Exception{
//        String meetingRoomNumber = "910007927030";
//        String meetingRoomNumber = "90003370933";
//        String meetingRoomNumber = "9083941358";
        String meetingRoomNumber = "9083854475";
        CreateMeetingApi meetingApi = new CreateMeetingApi();
        Result result =  meetingApi.getMeetingInfo(ENTERPRISE_ID,null,meetingRoomNumber);
        System.out.println(JSONObject.toJSONString(result));
    }

    /**
     * 预约会议
     */
    @Test
    public void remindMeeting() throws Exception {
//        String meetingRoomNumber = "910007305685";

        ScheduleMeetingApi sma = new ScheduleMeetingApi();
        ReminderMeeting reminderMeeting = new ReminderMeeting();
        reminderMeeting.setTitle("001-我预约的会议" + new Random().nextInt(100));
        // 开始时间，2个min之后
        reminderMeeting.setStartTime(System.currentTimeMillis()+ (1000 * 60 * 1));
        // 结束时间，4个min之后，预约会议时长2min
        reminderMeeting.setEndTime(System.currentTimeMillis() + (1000 * 60 * 4));
        reminderMeeting.setAddress("0708测试会议-002");
        reminderMeeting.setAutoInvite(1);
        reminderMeeting.setPassword("2345");
        reminderMeeting.setAutoRecord(1);
        reminderMeeting.setMeetingRoomType(2);
//        reminderMeeting.setConferenceNumber(meetingRoomNumber);
        reminderMeeting.setOwner(1234);
        reminderMeeting.setMeetingRoomType(1);
        Set<String> participants = new HashSet<>();
        participants.add(DEVICE_NO);
        reminderMeeting.setParticipants(participants);


        /*ScheduleMeetingApi sma = new ScheduleMeetingApi();
        ReminderMeeting reminderMeeting = new ReminderMeeting();
        reminderMeeting.setTitle("1111");
        // 开始时间，2个min之后
        reminderMeeting.setStartTime(System.currentTimeMillis());
        // 结束时间，4个min之后，预约会议时长2min
        reminderMeeting.setEndTime(System.currentTimeMillis() + (1000 * 60 * 2));
        reminderMeeting.setMeetingRoomType(1);
        reminderMeeting.setAutoRecord(0);
        reminderMeeting.setOwner(0);
        reminderMeeting.setAutoInvite(0);
        reminderMeeting.setCreateTime(0);
        reminderMeeting.setWeek(0);*/


        Result result = sma.remindMeeting(ENTERPRISE_ID, null, reminderMeeting);
        System.out.println(result);
//          data":{"meetingId":"9680ed62812555bd018194ccf7866601","meetingRoomNumber":"90003732593"}
//          data":{"meetingId":"968009e481255c6f018194d2fc907d87","meetingRoomNumber":"90003275414"}
//          data":{"meetingId":"96808e0c81bfd3190181dbdc8b7777ed","meetingRoomNumber":"90003833028"}
//          data":{"meetingId":"96802cb081bfcea70181dc04546e7df9","meetingRoomNumber":"90003923633"}
    }


    /**
     * 邀请入会
     * @throws Exception
     */
    @Test
    public void inviteJoinMeeting() throws Exception{
        String callNumber = "90003923633";
        ConferenceControlApi conferenceControlApi = new ConferenceControlApi();

//        Result<MeetingStatus> meetingStatusResult = conferenceControlApi.getMeetingStatus(ENTERPRISE_ID, null, "272758");
//        Result<MeetingStatus> meetingStatusResult = conferenceControlApi.getMeetingStatus(ENTERPRISE_ID, null, callNumber);

        CallInviteRequest callInviteRequest =  new CallInviteRequest();
        callInviteRequest.setCallNumber(callNumber);
        
        List<DeviceInfo> deviceInfoList = Lists.newArrayList();
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setNumber(DEVICE_NO);
        deviceInfoList.add(deviceInfo);
        callInviteRequest.setDeviceList(deviceInfoList);

        Result result = conferenceControlApi.inviteCall(ENTERPRISE_ID, null, callInviteRequest);
        System.out.println("inviteJoinMeeting 响应结果：" + result);

    }


    /**
     * 获取设备终端状态
     * @throws Exception
     */
    @Test
    public void getDeviceStatus() throws Exception{
        DeviceStatusApi deviceStatusApi = new DeviceStatusApi();
        Result<DeviceInfo> deviceInfo = deviceStatusApi.getDeviceInfo(ENTERPRISE_ID, null, DEVICE_NO);
        System.out.println("getDeviceStatus 响应结果：" + deviceInfo);
    }

    /**
     * 获取设备 被呼叫的会议信息
     * @throws Exception
     */
    @Test
    public void getDeviceInfoDetail() throws Exception{
        DeviceStatusApi deviceStatusApi = new DeviceStatusApi();
        Result<DeviceInfoDetail> deviceInfoDetail = deviceStatusApi.getDeviceInfoDetail(ENTERPRISE_ID, null, DEVICE_NO);
        System.out.println("getDeviceInfoDetail 响应结果：" + deviceInfoDetail);
    }

    /**
     * 根据会议ID 获取会议参与信息
     * @throws Exception
     */
    @Test
    public void getMeetingStatisticInfo() throws Exception{
        String meetingId = "968009e481255c6f018194d2fc907d87";
        MeetingStatisticApi meetingStatisticApi = new MeetingStatisticApi();
        Result<Pager> byMeetingId = meetingStatisticApi.getByMeetingId(ENTERPRISE_ID, null, meetingId);
        System.out.println("getMeetingStatisticInfo 响应结果：" + byMeetingId);
    }

    /**
     * 结束会议
     * @throws Exception
     */
    @Test
    public void setMeetingEnd() throws Exception{
        String callNumber = "90003732593";
        ConferenceControlApi conferenceControlApi = new ConferenceControlApi();

        Result result = conferenceControlApi.end(ENTERPRISE_ID, null, callNumber);
        System.out.println("setMeetingEnd 响应结果：" + result);

    }


    /**
     * 查询企业当前会议列表
     * @throws Exception
     */
    @Test
    public void getMeetingList() throws Exception{
        ConferenceControlApi conferenceControlApi = new ConferenceControlApi();

        Result result = conferenceControlApi.getEnterpriseCurrentMeeting(ENTERPRISE_ID, null, 0, 50);
        System.out.println("getMeetingList 响应结果：" + result);
    }

    /**
     *  获取企业ID下的所有设备终端列表
     * @throws Exception
     */
    @Test
    public void getDeviceList() throws Exception{
//        DeviceStatusApi deviceStatusApi = new DeviceStatusApi();
        EnterpriseNemoApi enterpriseNemoApi = new EnterpriseNemoApi();
//        Result<Pager<DeviceInfo>> deviceInfo = deviceStatusApi.getDeviceInfo(ENTERPRISE_ID, null, 0, 100);
        Result enterpriseDevicesPage = enterpriseNemoApi.getEnterpriseDevicesPage(ENTERPRISE_ID, null, 0, 50);
        System.out.println("getDeviceList 响应结果：" + enterpriseDevicesPage);

    }

    @Test
    public void addDeviceToEnterprise() throws Exception{
        EnterpriseNemoApi enterpriseNemoApi = new EnterpriseNemoApi();
        List<UserDeviceDto> userDeviceDtos = Lists.newArrayList();
        UserDeviceDto userDeviceDto = new UserDeviceDto();
        userDeviceDto.setNemoNumber(DEVICE_NO);
        userDeviceDtos.add(userDeviceDto);
        Result<NemoDto[]> result = enterpriseNemoApi.addNemo2Enterprise(ENTERPRISE_ID, null, userDeviceDtos);
        System.out.println("addDeviceToEnterprise 响应结果：" + JSON.toJSONString(result));
    }

    @Test
    public void removeDeviceFromEnterprise() throws Exception{
        EnterpriseNemoApi enterpriseNemoApi = new EnterpriseNemoApi();
        Result<NemoDto[]> result = enterpriseNemoApi.removeNemoFromEnterprise(ENTERPRISE_ID, null, DEVICE_NO);
        System.out.println("removeDeviceFromEnterprise 响应结果：" + JSON.toJSONString(result));
    }


    /**
     * 根据云会议号获取预约会议列表
     * @throws Exception
     */
    @Test
    public void getMeetingByConfrenceNumber() throws Exception{
        String meetingRoomNumber = "9022131329";

        ScheduleMeetingApi sma = new ScheduleMeetingApi();

        Result result = sma.getMeetingByConfrenceNumber(ENTERPRISE_ID,null,0,100,meetingRoomNumber);
        System.out.println(result);
    }

    /**
     * 根据预约会议id获取预约会议详细信息
     * @throws Exception
     */
    @Test
    public void getMeeting() throws Exception{
        String meetingId = "96802ca5809f800b0180c6706b7b7960";

        ScheduleMeetingApi sma = new ScheduleMeetingApi();

        Result result = sma.getMeeting(ENTERPRISE_ID,null,meetingId);
        System.out.println(result);
    }

    /**
     * 按云会议号分页获取视频列表
     * @throws Exception
     */
    @Test
    public void getMeetingRoomVodsPage() throws Exception{
        String meetingRoomNumber = "910006949130";

        VodApi vodApi = new VodApi();

        Result result = vodApi.getMeetingRoomVodsPage(ENTERPRISE_ID, null, meetingRoomNumber
                , System.currentTimeMillis() - (1000 * 60 * 40), System.currentTimeMillis(), 0, 100);
        System.out.println(result);
    }

    /**
     * 根据sessionId获取视频的下载链接
     * @throws Exception
     */
    @Test
    public void getDownloadurlBySessionId() throws Exception{
//        String sessionId = "NORMAL_101-bj1-prdSig1-1164350191139_101-bj1-prdSig1-1164350191139_751804_0";
        String sessionId = "NORMAL_101-bj2-prdSig2ms-2164400093607_101-bj2-prdSig2ms-2164400093607_301576_0";

        VodApi vodApi = new VodApi();

        Result result = vodApi.getDownloadurlBySessionId(ENTERPRISE_ID,null,sessionId);
        System.out.println(result);
    }
//==================================================================================================
    /**
     * 录屏结束时候自动回调
     * @throws Exception
     */
    @Test
    public void regExternalCallback_RecordingStop() throws Exception{
        ExternalCallback externalCallback = new ExternalCallback();
        externalCallback.setCallbackEvent(CallbackEvent.RecordingStop);
        externalCallback.setHandlerUrl("http://jqh519.qicp.vip/test/callbackVideo");

        CallBacksApi callBacksApi = new CallBacksApi();
        Result result = callBacksApi.regExternalCallback(ENTERPRISE_ID, null, externalCallback);
        System.out.println(result);
    }

    /**
     * 视频转码成功之后回调（获取视频分享、下载链接，解密状态反馈）
     * @throws Exception
     */
    @Test
    public void regExternalCallback_DecryptStatus() throws Exception{
        ExternalCallback externalCallback = new ExternalCallback();
        externalCallback.setCallbackEvent(CallbackEvent.DecryptStatus);
        externalCallback.setHandlerUrl("http://jqh519.qicp.vip/test/convertCode");

        CallBacksApi callBacksApi = new CallBacksApi();
        Result result = callBacksApi.regExternalCallback(ENTERPRISE_ID, null, externalCallback);
        System.out.println(result);
    }

    /**
     * 查询所有注册回调事件
     * @throws Exception
     */
    @Test
    public void getExternalCallbacks() throws Exception{
        CallBacksApi callBacksApi = new CallBacksApi();
//        callBacksApi.removeExternalCallback(ENTERPRISE_ID,null,CallbackEvent.NewUserCall);

        Result<ExternalCallback[]> externalCallbacks = callBacksApi.getExternalCallbacks(ENTERPRISE_ID, null);
        System.out.println(externalCallbacks);
    }
//=========================================================================================
    /**
     * 分页获取企业的所有小鱼终端
     * @throws Exception
     */
    @Test
    public void getDeviceInfo() throws Exception{
        DeviceStatusApi api = new DeviceStatusApi();

        Result result = api.getDeviceInfo(ENTERPRISE_ID,null,0,100);
        System.out.println(result);
    }

    @Test
    public void test(){
        String url = "http://testlb1.ccb.com/v/preview/static/js/lib/pdfjs/web/viewer.html?file=http%3A%2F%2Ftestlb1.ccb.com%3A80%2Fv4%2Fwopi%2Fdoc_preview%3Faccess_token%3D901ab00adcb36c2a_a8dabfca684172ecdf5fc3181841bf0d%26X-LENOVO-SESS-ID%3Df404caa9825343d790432839692b2cce%26neid%3D1526126606681116710%26bytes%3D175204%26rev%3D8da12a61c3414677aea2909610e1fc10%26type%3Ddoc%26mimetype%3D.docx%26hash%3Da8dabfca684172ecdf5fc3181841bf0d%26addWatermark%3Dtrue%26watermarkText%3D%25E8%2581%2594%25E6%2583%25B3Filez%252Fapptest1.zh-172-17-42-1%252F2022%2B05%2B17%2B08%253A59%253A46%26wopisrc%3DEC2DC2407730F46370234CF1598D3B24B86A60F156BC02FA7EA983C5E418EDD7F5847BBA9E53830786B63F324F59376EDF01CB4B4E63AABE461B1D3CC3FD9E6D8BC864996AC12B7F5C7F5F6E461A7A03B1C8BF18CDA48090388DE1D94B1A008039A10DEDA1A3535009ECBF93F139D5D9CF3B9DBEDEA539DBE9272889EE6E30B171E72A11D3F88C9444966EA9598CB599088BFDD9E9307D546ED69D385B03837566F0C877EDDD38F3C2349DE9500506949BDCE50DFC67349A3C684A90610C36CD%26previewType%3Dstream%26isSecondVisit%3D1%26recLog%3Dfalse%26language%3Dzh%26from_upc%3D1";
        String decode = URLDecoder.decode(url, Charset.defaultCharset());
        System.out.println(decode);

    }
}
