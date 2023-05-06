package com.xylink.sdk;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MyReminderMeeting implements Serializable {

    private String id;
    private long startTime;
    private long endTime;
    private String title;
    private String address;
    private String details;
    private long owner;
    private int autoInvite;
    private String conferenceNumber;
    private long createTime;
    private String password;
    private String conferenceControlPassword;
    private Set<String> participants = new HashSet();
    private Set<String> singleRecordParticipants = new HashSet();
    private int week;
    private int meetingRoomType = -1;
    private int autoRecord;
    private int enableOffLineRecord;
    private int enableSingleRecord;
    private String mainImage;
    private Map recordViewLayout;
    private Boolean meetingLock;
    private Integer lineRateMin;
    private Integer lineRateMax;
    /**
     * 需要传入的是终端的id，格式为Long.toStirng()
     */
    private List<String> meetingHost;


    public Map getRecordViewLayout() {
        return this.recordViewLayout;
    }

    public void setRecordViewLayout(Map recordViewLayout) {
        this.recordViewLayout = recordViewLayout;
    }

    public MyReminderMeeting() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return this.endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetails() {
        return this.details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public long getOwner() {
        return this.owner;
    }

    public void setOwner(long owner) {
        this.owner = owner;
    }

    public int getAutoInvite() {
        return this.autoInvite;
    }

    public void setAutoInvite(int autoInvite) {
        this.autoInvite = autoInvite;
    }

    public String getConferenceNumber() {
        return this.conferenceNumber;
    }

    public void setConferenceNumber(String conferenceNumber) {
        this.conferenceNumber = conferenceNumber;
    }

    public long getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public Set<String> getParticipants() {
        return this.participants;
    }

    public void setParticipants(Set<String> participants) {
        this.participants = participants;
    }

    public int getWeek() {
        return this.week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMeetingRoomType() {
        return this.meetingRoomType;
    }

    public void setMeetingRoomType(int meetingRoomType) {
        this.meetingRoomType = meetingRoomType;
    }

    public String getConferenceControlPassword() {
        return this.conferenceControlPassword;
    }

    public void setConferenceControlPassword(String conferenceControlPassword) {
        this.conferenceControlPassword = conferenceControlPassword;
    }

    public int getAutoRecord() {
        return this.autoRecord;
    }

    public void setAutoRecord(int autoRecord) {
        this.autoRecord = autoRecord;
    }

    public int getEnableOffLineRecord() {
        return this.enableOffLineRecord;
    }

    public void setEnableOffLineRecord(int enableOffLineRecord) {
        this.enableOffLineRecord = enableOffLineRecord;
    }

    public int getEnableSingleRecord() {
        return this.enableSingleRecord;
    }

    public void setEnableSingleRecord(int enableSingleRecord) {
        this.enableSingleRecord = enableSingleRecord;
    }

    public String getMainImage() {
        return this.mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public Set<String> getSingleRecordParticipants() {
        return this.singleRecordParticipants;
    }

    public void setSingleRecordParticipants(Set<String> singleRecordParticipants) {
        this.singleRecordParticipants = singleRecordParticipants;
    }

    public Boolean getMeetingLock() {
        return meetingLock;
    }

    public void setMeetingLock(Boolean meetingLock) {
        this.meetingLock = meetingLock;
    }
    public Integer getLineRateMin() {
        return lineRateMin;
    }

    public void setLineRateMin(Integer lineRateMin) {
        this.lineRateMin = lineRateMin;
    }

    public Integer getLineRateMax() {
        return lineRateMax;
    }

    public void setLineRateMax(Integer lineRateMax) {
        this.lineRateMax = lineRateMax;
    }

    public List<String> getMeetingHost() {
        return meetingHost;
    }

    public void setMeetingHost(List<String> meetingHost) {
        this.meetingHost = meetingHost;
    }
}
