package com.icss.oa.meeting.pojo;

import java.sql.Date;

import com.icss.oa.system.pojo.Employee;

public class Meeting {
	private Integer meeId;
	private String meeTopic;
	private String meeApprovalStatus;
	private String meeRecord;
	private MeetingRoom meetingRoom;
	private Employee sponsor;
	private Date meeStartTime;
	public Meeting(Integer meeId, String meeTopic, String meeApprovalStatus, String meeRecord, MeetingRoom meetingRoom,
			Employee sponsor, Date meeStartTime, Integer meeDuration) {
		super();
		this.meeId = meeId;
		this.meeTopic = meeTopic;
		this.meeApprovalStatus = meeApprovalStatus;
		this.meeRecord = meeRecord;
		this.meetingRoom = meetingRoom;
		this.sponsor = sponsor;
		this.meeStartTime = meeStartTime;
		this.meeDuration = meeDuration;
	}
	public Date getMeeStartTime() {
		return meeStartTime;
	}
	public void setMeeStartTime(Date meeStartTime) {
		this.meeStartTime = meeStartTime;
	}
	public Integer getMeeDuration() {
		return meeDuration;
	}
	public void setMeeDuration(Integer meeDuration) {
		this.meeDuration = meeDuration;
	}
	private Integer meeDuration;
	

	public Employee getSponsor() {
		return sponsor;
	}
	public void setSponsor(Employee sponsor) {
		this.sponsor = sponsor;
	}
	public String getMeeTopic() {
		return meeTopic;
	}
	public void setMeeTopic(String meeTopic) {
		this.meeTopic = meeTopic;
	}
	public String getMeeApprovalStatus() {
		return meeApprovalStatus;
	}
	public void setMeeApprovalStatus(String meeApprovalStatus) {
		this.meeApprovalStatus = meeApprovalStatus;
	}
	public String getMeeRecord() {
		return meeRecord;
	}
	public void setMeeRecord(String meeRecord) {
		this.meeRecord = meeRecord;
	}
	public MeetingRoom getMeetingRoom() {
		return meetingRoom;
	}
	public void setMeetingRoom(MeetingRoom meetingRoom) {
		this.meetingRoom = meetingRoom;
	}


	public Meeting() {
		super();
	}

	public Meeting(String meeTopic, String meeApprovalStatus, String meeRecord, MeetingRoom meetingRoom,
			Employee sponsor, Date meeStartTime, Integer meeDuration) {
		super();
		this.meeTopic = meeTopic;
		this.meeApprovalStatus = meeApprovalStatus;
		this.meeRecord = meeRecord;
		this.meetingRoom = meetingRoom;
		this.sponsor = sponsor;
		this.meeStartTime = meeStartTime;
		this.meeDuration = meeDuration;
	}
	@Override
	public String toString() {
		return "Meeting [meeId=" + meeId + ", meeTopic=" + meeTopic + ", meeApprovalStatus=" + meeApprovalStatus
				+ ", meeRecord=" + meeRecord + ", meetingRoom=" + meetingRoom + ", sponsor=" + sponsor
				+ ", meeStartTime=" + meeStartTime + ", meeDuration=" + meeDuration + "]";
	}
	public Integer getMeeId() {
		return meeId;
	}
	public void setMeeId(Integer meeId) {
		this.meeId = meeId;
	}
	
	
}
