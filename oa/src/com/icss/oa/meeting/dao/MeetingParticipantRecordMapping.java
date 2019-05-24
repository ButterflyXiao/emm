package com.icss.oa.meeting.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.icss.oa.meeting.pojo.Meeting;
import com.icss.oa.meeting.pojo.MeetingParticipantRecord;
import com.icss.oa.system.pojo.Employee;


public interface MeetingParticipantRecordMapping {

	void insert(MeetingParticipantRecord rec);
	
	void delete(MeetingParticipantRecord rec);
	
//	List<Employee> queryByMeeId(Integer meeId);
//	
//	List<MeetingParticipantRecord> queryByEmp(Employee emp);
//	
	
	

}
