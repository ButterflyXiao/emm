package com.icss.test;

import java.util.*;
import java.sql.Date;
import java.util.ArrayList;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.meeting.dao.MeetingParticipantRecordMapper;
import com.icss.oa.meeting.pojo.Meeting;
import com.icss.oa.meeting.pojo.MeetingParticipantRecord;
import com.icss.oa.system.pojo.Department;
import com.icss.oa.system.pojo.Employee;

public class TestMeetingParticipantRecordMapping {

	// ����Spring�������Ķ���
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	// mapper����
	MeetingParticipantRecordMapper mapper = context.getBean(MeetingParticipantRecordMapper.class);

	@Test
	public void testInsert() {

		Meeting meeting = new Meeting();
		meeting.setMeeId(14);

		Employee employee = new Employee();
		employee.setEmpId(1);

		MeetingParticipantRecord reco = new MeetingParticipantRecord(meeting, employee);
		mapper.insert(reco);

	}
	

	
	@Test
	public void testDelete() {

		Meeting meeting = new Meeting();
		meeting.setMeeId(14);

		Employee employee = new Employee();
		employee.setEmpId(1);

		MeetingParticipantRecord reco = new MeetingParticipantRecord(meeting, employee);
		mapper.delete(reco);
	}
	
	
	@Test
	public void testQueryByMeetingId() {
		
		int meeId=12;
		java.util.List<Employee> list=mapper.queryByMeeId(meeId);
		for (Employee employee : list) {
			System.out.println(employee);
		}
	}
	
	@Test
	public void testQueryByEmployee(){
		int empId=1;
		mapper.queryByEmpId(empId);
	}
	
	@Test
	public void removeAll(){
		List<Integer> newParIds=new ArrayList<>();
		List<Integer> oldParIds=new ArrayList<>();
		newParIds.add(1);
		newParIds.add(2);
		oldParIds.add(2);
		oldParIds.add(3);


//		List<Integer> addIdList=newParIds;
		List<Integer> addIdList=new ArrayList<>();
		addIdList.add(1);
		addIdList.add(2);
		addIdList.removeAll(oldParIds);
		
		List<Integer> delIdList=oldParIds;
		delIdList.removeAll(newParIds);
		
		System.out.println(oldParIds.toString());
		System.out.println(newParIds.toString());
		System.out.println(addIdList.toString());
		System.out.println(delIdList.toString());
		
	}
}
