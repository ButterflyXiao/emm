package com.icss.oa.meeting.pojo;



import java.util.Date;

import com.icss.oa.system.pojo.Employee;

public class Meeting {
	private Integer meeId;
	private MeetingRoom meeRoom;
	private Employee meeSponsor;
	private Boolean meeApprovalStatus;
	private String meeTopic;
	private String meeContent;
	private String meeRecord;
	private Date meeStartTime;
	private int meeDuration;
	public MeetingRoom getMeeRoom() {
		return meeRoom;
	}
	public void setMeeRoom(MeetingRoom meeRoom) {
		this.meeRoom = meeRoom;
	}
	public Employee getMeeSponsor() {
		return meeSponsor;
	}
	public void setMeeSponsor(Employee meeSponsor) {
		this.meeSponsor = meeSponsor;
	}
	public Boolean getMeeApprovalStatus() {
		return meeApprovalStatus;
	}
	public void setMeeApprovalStatus(Boolean meeApprovalStatus) {
		this.meeApprovalStatus = meeApprovalStatus;
	}
	public String getMeeTopic() {
		return meeTopic;
	}
	public void setMeeTopic(String meeTopic) {
		this.meeTopic = meeTopic;
	}
	public String getMeeContent() {
		return meeContent;
	}
	public void setMeeContent(String meeContent) {
		this.meeContent = meeContent;
	}
	public String getMeeRecord() {
		return meeRecord;
	}
	public void setMeeRecord(String meeRecord) {
		this.meeRecord = meeRecord;
	}
	public Date getMeeStartTime() {
		return meeStartTime;
	}
	public void setMeeStartTime(Date meeStartTime) {
		this.meeStartTime = meeStartTime;
	}
	public int getMeeDuration() {
		return meeDuration;
	}
	public void setMeeDuration(int meeDuration) {
		this.meeDuration = meeDuration;
	}
	public Meeting(MeetingRoom meeRoom, Employee meeSponsor, Boolean meeApprovalStatus, String meeTopic,
			String meeContent, String meeRecord, Date meeStartTime, int meeDuration) {
		super();
		this.meeRoom = meeRoom;
		this.meeSponsor = meeSponsor;
		this.meeApprovalStatus = meeApprovalStatus;
		this.meeTopic = meeTopic;
		this.meeContent = meeContent;
		this.meeRecord = meeRecord;
		this.meeStartTime = meeStartTime;
		this.meeDuration = meeDuration;
	}
	public Meeting(Integer meeId, MeetingRoom meeRoom, Employee meeSponsor, Boolean meeApprovalStatus, String meeTopic,
			String meeContent, String meeRecord, Date meeStartTime, int meeDuration) {
		super();
		this.meeId = meeId;
		this.meeRoom = meeRoom;
		this.meeSponsor = meeSponsor;
		this.meeApprovalStatus = meeApprovalStatus;
		this.meeTopic = meeTopic;
		this.meeContent = meeContent;
		this.meeRecord = meeRecord;
		this.meeStartTime = meeStartTime;
		this.meeDuration = meeDuration;
	}
	public Meeting() {
		super();
	}
	@Override
	public String toString() {
		return "Meeting [meeId=" + meeId + ", meeRoom=" + meeRoom + ", meeSponsor=" + meeSponsor
				+ ", meeApprovalStatus=" + meeApprovalStatus + ", meeTopic=" + meeTopic + ", meeContent=" + meeContent
				+ ", meeRecord=" + meeRecord + ", meeStartTime=" + meeStartTime + ", meeDuration=" + meeDuration
				+ "]";
	}
	public Integer getMeeId() {
		return meeId;
	}
	public void setMeeId(Integer meeId) {
		this.meeId = meeId;
	}

}
