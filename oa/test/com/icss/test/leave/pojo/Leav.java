package com.icss.oa.leave.pojo;

import java.util.Date;

import com.icss.oa.system.pojo.Employee;

public class Leav {
    private Integer leaId;

    private Employee leaEmployee;

    private Date leaStartTime;

    private Date leaFinishTime;

    private String leaReason;

    private Boolean leaApplicationStatus;

    private Date leaApplicationTime;

    private Employee leaApplicationPerson;

    private String leaApplicationNote;

    public Integer getLeaId() {
        return leaId;
    }

    public void setLeaId(Integer leaId) {
        this.leaId = leaId;
    }

    public Employee getLeaEmployee() {
        return leaEmployee;
    }

    public void setLeaEmployee(Employee leaEmployee) {
        this.leaEmployee = leaEmployee;
    }

    public Date getLeaStartTime() {
        return leaStartTime;
    }

    public void setLeaStartTime(Date leaStartTime) {
        this.leaStartTime = leaStartTime;
    }

    public Date getLeaFinishTime() {
        return leaFinishTime;
    }

    public void setLeaFinishTime(Date leaFinishTime) {
        this.leaFinishTime = leaFinishTime;
    }

    public String getLeaReason() {
        return leaReason;
    }

    public void setLeaReason(String leaReason) {
        this.leaReason = leaReason == null ? null : leaReason.trim();
    }

    public Boolean getLeaApplicationStatus() {
        return leaApplicationStatus;
    }

    public void setLeaApplicationStatus(Boolean leaApplicationStatus) {
        this.leaApplicationStatus = leaApplicationStatus;
    }

    public Date getLeaApplicationTime() {
        return leaApplicationTime;
    }

    public void setLeaApplicationTime(Date leaApplicationTime) {
        this.leaApplicationTime = leaApplicationTime;
    }

    public Employee getLeaApplicationPerson() {
        return leaApplicationPerson;
    }

    public void setLeaApplicationPerson(Employee leaApplicationPerson) {
        this.leaApplicationPerson = leaApplicationPerson;
    }

    public String getLeaApplicationNote() {
        return leaApplicationNote;
    }

    public void setLeaApplicationNote(String leaApplicationNote) {
        this.leaApplicationNote = leaApplicationNote == null ? null : leaApplicationNote.trim();
    }

	public Leav(Integer leaId, Employee leaEmployee, Date leaStartTime, Date leaFinishTime, String leaReason,
			Boolean leaApplicationStatus, Date leaApplicationTime, Employee leaApplicationPerson,
			String leaApplicationNote) {
		super();
		this.leaId = leaId;
		this.leaEmployee = leaEmployee;
		this.leaStartTime = leaStartTime;
		this.leaFinishTime = leaFinishTime;
		this.leaReason = leaReason;
		this.leaApplicationStatus = leaApplicationStatus;
		this.leaApplicationTime = leaApplicationTime;
		this.leaApplicationPerson = leaApplicationPerson;
		this.leaApplicationNote = leaApplicationNote;
	}

	public Leav() {
		super();
	}

	public Leav(Employee leaEmployee, Date leaStartTime, Date leaFinishTime, String leaReason,
			Boolean leaApplicationStatus, Date leaApplicationTime, Employee leaApplicationPerson,
			String leaApplicationNote) {
		super();
		this.leaEmployee = leaEmployee;
		this.leaStartTime = leaStartTime;
		this.leaFinishTime = leaFinishTime;
		this.leaReason = leaReason;
		this.leaApplicationStatus = leaApplicationStatus;
		this.leaApplicationTime = leaApplicationTime;
		this.leaApplicationPerson = leaApplicationPerson;
		this.leaApplicationNote = leaApplicationNote;
	}

	@Override
	public String toString() {
		return "Leave [leaId=" + leaId + ", leaEmployee=" + leaEmployee + ", leaStartTime=" + leaStartTime
				+ ", leaFinishTime=" + leaFinishTime + ", leaReason=" + leaReason + ", leaApplicationStatus="
				+ leaApplicationStatus + ", leaApplicationTime=" + leaApplicationTime + ", leaApplicationPerson="
				+ leaApplicationPerson + ", leaApplicationNote=" + leaApplicationNote + "]";
	}
}