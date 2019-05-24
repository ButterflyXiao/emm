package com.icss.test;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.meeting.dao.MeetingMapper;
import com.icss.oa.meeting.pojo.Meeting;
import com.icss.oa.meeting.pojo.MeetingRoom;
import com.icss.oa.system.dao.EmployeeMapper;
import com.icss.oa.system.pojo.Employee;

public class TestMeetingMapper {

	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	MeetingMapper mapper=context.getBean(MeetingMapper.class); 
		
	@Test
	public void insert() {
		MeetingRoom room=new MeetingRoom();
		room.setRoomId(1);
		Employee sponsor = new Employee();
		sponsor.setEmpId(1);
		
		mapper.insert(new Meeting("xx","鏈棰勭害","鏃犺褰�",room,sponsor,new Date(0),30));
		
	}
	
	@Test
	public void update() {
		MeetingRoom room=new MeetingRoom();
		room.setRoomId(2);
		Employee sponsor = new Employee();
		sponsor.setEmpId(2);
		
		mapper.update(new Meeting(1,"xx","鏈棰勭害","鏃犺褰�",room,sponsor,new Date(0),30));
		
	}
	
	@Test
	public void delete() {
		mapper.delete(7);
	}
		
	@Test
	public void queryById() {
		Meeting mee=mapper.queryById(1);
		System.out.println(mee);
	}
	
	@Test
	public void query() {
		List<Meeting> list=mapper.query();
		for(Meeting mee:list)
			System.out.println(mee);
		
	}
	
//	@Test
//	public void testQueryByPage() {
//		
//		HashMap<String, Integer> map = new HashMap<>();
//		map.put("start", 0);
//		map.put("pageSize", 7);
//		
//		List<Meeting> list = mapper.queryByPage(map);
//		
//		for (Meeting mee : list) {
//			System.out.println(mee);
//		}
//	}	
	@Test
	public void testQueryByPage() {
			
		List<Meeting> list = mapper.queryByPage(0,7);
		
		for (Meeting mee : list) {
			System.out.println(mee);
		}
	}	
	
	@Test
	public void queryByTopic() {
		List<Meeting> list=mapper.queryByTopic("xx");
		for (Meeting mee : list) {
			System.out.println(mee);
		}
	}
	
	@Test
	public void queryByCondition() {
		List<Meeting> list = mapper.queryByCondition(0, 5, null, null, null, "鏈棰勭害",new Date(0));
		
		for (Meeting mee : list) {
			System.out.println(mee);
		}
	}
	
	@Test
	public void testGetCountByCondition() {		
		
		int count = mapper.getCountByCondition(1 , 1, "", null,new Date(0));
		System.out.println(count);
		
	}
}
