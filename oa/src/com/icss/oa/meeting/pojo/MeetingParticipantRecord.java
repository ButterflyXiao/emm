package com.icss.oa.meeting.pojo;

import com.icss.oa.system.pojo.Employee;

public class MeetingParticipantRecord {
	private Meeting mee;
	private Employee emp;
	public Meeting getMee() {
		return mee;
	}
	public void setMee(Meeting mee) {
		this.mee = mee;
	}
	public Employee getEmp() {
		return emp;
	}
	public void setEmp(Employee emp) {
		this.emp = emp;
	}
	public MeetingParticipantRecord(Meeting mee, Employee emp) {
		super();
		this.mee = mee;
		this.emp = emp;
	}
	public MeetingParticipantRecord() {
		super();
	}
	@Override
	public String toString() {
		return "MeetingParticipantRecord [mee=" + mee + ", emp=" + emp + "]";
	}
	
}
