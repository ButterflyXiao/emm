package com.icss.oa.meeting.pojo;

import com.icss.oa.system.pojo.Employee;

public class Meeting {
	private Integer meeId;
	private String meeTopic;
	private String meeApprovalStatus;
	private String meeRecord;
	private MeetingRoom meetingRoom;
	private Employee employee;
	

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
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Meeting(Integer meeId, String meeTopic, String meeApprovalStatus, String meeRecord, MeetingRoom meetingRoom,
			Employee employee) {
		super();
		this.meeId = meeId;
		this.meeTopic = meeTopic;
		this.meeApprovalStatus = meeApprovalStatus;
		this.meeRecord = meeRecord;
		this.meetingRoom = meetingRoom;
		this.employee = employee;
	}
	public Meeting() {
		super();
	}
	@Override
	public String toString() {
		return "Meeting [meeId=" + meeId + ", meeTopic=" + meeTopic + ", meeApprovalStatus=" + meeApprovalStatus
				+ ", meeRecord=" + meeRecord + ", meetingRoom=" + meetingRoom + ", employee=" + employee + "]";
	}
	
	
}
