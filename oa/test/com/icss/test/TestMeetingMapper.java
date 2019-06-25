package com.icss.test;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.meeting.dao.MeetingMapper;
import com.icss.oa.meeting.pojo.Meeting;
import com.icss.oa.meeting.pojo.MeetingRoom;
import com.icss.oa.meeting.service.MeetingService;
import com.icss.oa.system.dao.EmployeeMapper;
import com.icss.oa.system.pojo.Employee;
import com.icss.oa.system.service.DepartmentService;

public class TestMeetingMapper {

	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	MeetingMapper mapper=context.getBean(MeetingMapper.class); 
		
	MeetingService service= context.getBean(MeetingService.class);
	@Test
	public void insert() {
		MeetingRoom room=new MeetingRoom();
		room.setRoomId(3);
		Employee sponsor = new Employee();
		sponsor.setEmpId(4);
		
		mapper.insert(new Meeting(room, sponsor, "topic", "content",new Date(), 20));

	}
	
	@Test
	public void insertE() {
		MeetingRoom room=new MeetingRoom();
		room.setRoomId(3);
		Employee sponsor = new Employee();
		sponsor.setEmpId(4);
		Meeting mee=new Meeting();
		mee.setMeeContent("content");
		mee.setMeeTopic("topic");
		mee.setMeeDuration(20);
		mee.setMeeStartTime(new Date());
		mee.setMeeSponsor(sponsor);
		mee.setMeeRoom(room);
		service.addMee(mee);
	}
//	
//	@Test
//	public void update() {
//		MeetingRoom room=new MeetingRoom();
//		room.setRoomId(1);
//		Employee sponsor = new Employee();
//		sponsor.setEmpId(1);
//		
//		mapper.update(new Meeting(6, room, sponsor, null, null, null, null, null, 0));
//		
//	}
	
	@Test
	public void delete() {
		mapper.delete(2);
	}
		
	@Test
	public void queryById() {
		Meeting mee=mapper.queryById(12);
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
		List<Meeting> list = mapper.queryByCondition(0, 15,  null, null,"审批通过");
		System.out.println(list.size());
		for (Meeting mee : list) {
			System.out.println(mee);
		}
	}
	
	@Test
	public void testGetCountByCondition() {		
		
		int count = mapper.getCountByCondition( null,null,null);
		System.out.println(count);
		
	}
	
	
	@Test
	public void queryMeeByRoomId(){
		int roomId=6;
		List<Meeting> list = mapper.queryMeeByRoomId(roomId);
		
		for (Meeting meeting : list) {
			System.out.println(meeting);
		}
	}
	
	@Test
	public void LastByRoomId(){
		int roomId=6;
		Meeting  meeting=mapper.lastMeeByRoom(roomId,"审批通过");
		System.out.println(meeting);

	}
}
