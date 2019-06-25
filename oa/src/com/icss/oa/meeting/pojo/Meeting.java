package com.icss.oa.meeting.pojo;



import java.util.Arrays;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.icss.oa.system.pojo.Employee;

public class Meeting {
	private Integer meeId;
	private MeetingRoom meeRoom;
	private Employee meeSponsor;
	private String meeApprovalStatus;
	private String meeTopic;
	private String meeContent;
	private String meeRecordName;
	private byte[] meeRecord;
	private Integer meePar;
	
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")  
	private Date meeStartTime;
	private Integer meeDuration;
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
	public String getMeeApprovalStatus() {
		return meeApprovalStatus;
	}
	public void setMeeApprovalStatus(String meeApprovalStatus) {
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
	public byte[] getMeeRecord() {
		return meeRecord;
	}
	public void setMeeRecord(byte[] meeRecord) {
		this.meeRecord = meeRecord;
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
	public Meeting(MeetingRoom meeRoom, Employee meeSponsor, String meeApprovalStatus, String meeTopic,
			String meeContent, byte[] meeRecord,String meeRecordName, Date meeStartTime, Integer meeDuration) {
		super();
		this.meeRoom = meeRoom;
		this.meeSponsor = meeSponsor;
		this.meeApprovalStatus = meeApprovalStatus;
		this.meeTopic = meeTopic;
		this.meeContent = meeContent;
		this.meeRecord = meeRecord;
		this.meeStartTime = meeStartTime;
		this.meeDuration = meeDuration;
		this.meeRecordName = meeRecordName;
	}
	public Meeting(Integer meeId, MeetingRoom meeRoom, Employee meeSponsor, String meeApprovalStatus, String meeTopic,
			String meeContent, byte[] meeRecord, Date meeStartTime, Integer meeDuration,String meeRecordName) {
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
		this.meeRecordName=meeRecordName;
	}
	public Meeting() {
		super();
	}
	@Override
	public String toString() {
		return "Meeting [meeId=" + meeId + ", meeRoom=" + meeRoom + ", meeSponsor=" + meeSponsor
				+ ", meeApprovalStatus=" + meeApprovalStatus + ", meeTopic=" + meeTopic + ", meeContent=" + meeContent
				+ ", meeRecordName=" + meeRecordName + ", meeRecord=" + Arrays.toString(meeRecord) + ", meePar="
				+ meePar + ", meeStartTime=" + meeStartTime + ", meeDuration=" + meeDuration + "]";
	}
	public Integer getMeeId() {
		return meeId;
	}
	public void setMeeId(Integer meeId) {
		this.meeId = meeId;
	}
	public String getMeeRecordName() {
		return meeRecordName;
	}
	public void setMeeRecordName(String meeRecordName) {
		this.meeRecordName = meeRecordName;
	}
	public Integer getMeePar() {
		return meePar;
	}
	public void setMeePar(Integer meePar) {
		this.meePar = meePar;
	}
	public Meeting(Integer meeId, MeetingRoom meeRoom, Employee meeSponsor, String meeApprovalStatus, String meeTopic,
			String meeContent, String meeRecordName, byte[] meeRecord, Integer meePar, Date meeStartTime,
			Integer meeDuration) {
		super();
		this.meeId = meeId;
		this.meeRoom = meeRoom;
		this.meeSponsor = meeSponsor;
		this.meeApprovalStatus = meeApprovalStatus;
		this.meeTopic = meeTopic;
		this.meeContent = meeContent;
		this.meeRecordName = meeRecordName;
		this.meeRecord = meeRecord;
		this.meePar = meePar;
		this.meeStartTime = meeStartTime;
		this.meeDuration = meeDuration;
	}
	public Meeting( MeetingRoom meeRoom, Employee meeSponsor,String meeTopic,
			String meeContent, Date meeStartTime, Integer meeDuration) {
		super();

		this.meeRoom = meeRoom;
		this.meeSponsor = meeSponsor;

		this.meeTopic = meeTopic;
		this.meeContent = meeContent;


		this.meeStartTime = meeStartTime;
		this.meeDuration = meeDuration;
	}

}
