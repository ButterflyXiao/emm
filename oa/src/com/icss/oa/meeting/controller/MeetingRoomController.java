package com.icss.oa.meeting.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icss.oa.meeting.pojo.Meeting;
import com.icss.oa.meeting.pojo.MeetingRoom;
import com.icss.oa.meeting.service.MeetingRoomService;
import com.icss.oa.meeting.service.MeetingService;
import com.icss.oa.system.pojo.Department;
import com.icss.oa.system.service.DepartmentService;

@Controller
public class MeetingRoomController {

	@Autowired
	private MeetingRoomService service;
	@Autowired
	private MeetingService meeService;
	
	@RequestMapping("/room/add")
	public void add(HttpServletRequest request,HttpServletResponse response,MeetingRoom room){
		service.addMee(room);
	}
	
	@RequestMapping("/room/update")
	public void update(HttpServletRequest request,HttpServletResponse response,MeetingRoom room){
		service.updateMee(room);
	}
	
	@RequestMapping("/room/delete")
	public void update(HttpServletRequest request,HttpServletResponse response,Integer roomId){
		service.deleteMee(roomId);
	}
	
	@RequestMapping("/room/query")
	@ResponseBody
	public List<MeetingRoom> query(HttpServletRequest request,HttpServletResponse response){
		return service.query();
	}
	
	@RequestMapping("/room/getLast")
	@ResponseBody
	public HashMap<String,Object> getLast(HttpServletRequest request,HttpServletResponse response){
		List<MeetingRoom> list= service.query();
		List<Meeting> last=new ArrayList<>();
		for (MeetingRoom meetingRoom : list) {
			last.add(service.lastMeeByRoom(meetingRoom.getRoomId()));
		}
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("roomList", list);
		map.put("lastMeeList", last);
		return map;
		
	}
	
	@RequestMapping("/room/getHistory")
	@ResponseBody
//	public List<Meeting> getHistory(HttpServletRequest request,HttpServletResponse response,Integer roomId){
//		return service.queryMeeByRoomId(roomId);
//	}
	public HashMap<String,Object> getHistory(HttpServletRequest request,HttpServletResponse response,Integer roomId) throws ParseException{
		List<Meeting> list= service.queryMeeByRoomId(roomId);
		List<Boolean> bool=new ArrayList<>();
		Date now=new Date();
		DateFormat df = DateFormat.getDateTimeInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		for (Meeting meeting : list) {

			String date=sdf.format(meeting.getMeeStartTime());
			if( now.before(df.parse(date)))
				bool.add(true);
			else
				bool.add(false);
		}
		
		System.out.println(bool.toString());
		HashMap<String, Object> map = new HashMap<>();
		map.put("roomList", list);
		map.put("bool", bool);
		return map;
		
	}
	
	@RequestMapping("/room/get")
	@ResponseBody
	public MeetingRoom get(HttpServletRequest request,HttpServletResponse response,Integer roomId) {
		return service.queryRoomById(roomId);
	}	
	
	@RequestMapping("/room/getSize")
	@ResponseBody
	public Integer getSize(HttpServletRequest request,HttpServletResponse response,Integer meeId) {
		System.out.println(meeId);

		Integer roomId=meeService.getRoomId(meeId);
		System.out.println(roomId);
		return service.getSize(roomId);
	}
}
