package com.icss.test;

import java.sql.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.meeting.dao.MeetingParticipantRecordMapping;
import com.icss.oa.meeting.pojo.Meeting;
import com.icss.oa.meeting.pojo.MeetingParticipantRecord;

import com.icss.oa.system.pojo.Employee;

public class TestMeetingParticipantRecordMapping {

	// ����Spring�������Ķ���
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	// mapper����
	MeetingParticipantRecordMapping mapper = context.getBean(MeetingParticipantRecordMapping.class);

	@Test
	public void testInsert() {

		Meeting meeting = new Meeting();
		meeting.setMeeId(1);

		Employee employee = new Employee();
		employee.setEmpId(4);

		MeetingParticipantRecord reco = new MeetingParticipantRecord(meeting, employee);
		mapper.insert(reco);

	}
}
