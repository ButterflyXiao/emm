package com.icss.test;

import java.util.Date;
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
		
		mapper.insert(new Meeting(0, room, sponsor,"审批中", "topic", "content", "无记录", new Date(), 20));
		
	}
	
	@Test
	public void update() {
		MeetingRoom room=new MeetingRoom();
		room.setRoomId(1);
		Employee sponsor = new Employee();
		sponsor.setEmpId(1);
		
		mapper.update(new Meeting(6, room, sponsor, null, null, null, null, null, 0));
		
	}
	
	@Test
	public void delete() {
		mapper.delete(2);
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
		List<Meeting> list = mapper.queryByCondition(1, 10,  null, null, null);
		
		for (Meeting mee : list) {
			System.out.println(mee);
		}
	}
	
	@Test
	public void testGetCountByCondition() {		
		
		int count = mapper.getCountByCondition(  1,null,null);
		System.out.println(count);
		
	}
}
