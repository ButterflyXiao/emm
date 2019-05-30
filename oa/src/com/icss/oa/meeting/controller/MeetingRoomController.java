package com.icss.oa.meeting.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icss.oa.meeting.pojo.MeetingRoom;
import com.icss.oa.meeting.service.MeetingRoomService;
import com.icss.oa.system.pojo.Department;
import com.icss.oa.system.service.DepartmentService;

@Controller
public class MeetingRoomController {

	@Autowired
	private MeetingRoomService service;
	
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
	
	@RequestMapping("/room/get")
	@ResponseBody
	public MeetingRoom get(HttpServletRequest request,HttpServletResponse response,Integer roomId) {
		return service.queryRoomById(roomId);
	}	
	

}
