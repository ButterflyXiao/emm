package com.icss.test;

import static org.hamcrest.CoreMatchers.nullValue;

import java.sql.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.meeting.dao.MeetingRoomMapper;
import com.icss.oa.meeting.pojo.Meeting;
import com.icss.oa.meeting.pojo.MeetingRoom;


public class TestMeetingRoomMapper {
	// ����Spring�������Ķ���
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		// ���Mapper����dao����
		MeetingRoomMapper mapper = context.getBean(MeetingRoomMapper.class);

		@Test
		public void testInsert() {
			// ����pojo����
			MeetingRoom room = new MeetingRoom("ddd", true,50,false);
			// �������ݲ���
			mapper.insert(room);
		}

		@Test
		public void testUpdate() {
			// ����pojo����
			MeetingRoom room = new MeetingRoom(1,"ccc",false,40,true);
			// �������ݲ���
			mapper.update(room);
		}
		
		@Test
		public void testDelete() {
			mapper.delete(10);
		}
		
		@Test
		public void testQueryById() {		
			MeetingRoom room = mapper.queryById(1);
			System.out.println(room);		
		}
		
		@Test
		public void testQuery() {		
			List<MeetingRoom> list = mapper.query();
			
			for (MeetingRoom room : list) {
				System.out.println(room);
			}
		}	
		
		@Test
		public void testQueryByPage() {
				
			List<MeetingRoom> list = mapper.queryByPage(0,7);
			
			for (MeetingRoom room : list) {
				System.out.println(room);
			}
		}	
		
		
		@Test
		public void queryByCondition() {
			List<MeetingRoom> list = mapper.queryByCondition(0, 5, "1", null, null,null);
			
			for (MeetingRoom room : list) {
				System.out.println(room);
			}
		}
		
		@Test
		public void testGetCountByCondition() {		
			
			int count = mapper.getCountByCondition(null,null,null, null);
			System.out.println(count);
			
		}
}
