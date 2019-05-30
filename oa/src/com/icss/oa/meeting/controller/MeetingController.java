package com.icss.oa.meeting.controller;

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
public class MeetingController {

	@Autowired
	private MeetingService service;
	
	@RequestMapping("/meeting/add")
	public void add(HttpServletRequest request,HttpServletResponse response,Meeting mee){
		service.addMee(mee);
	}
	
	@RequestMapping("/meeting/update")
	public void update(HttpServletRequest request,HttpServletResponse response,Meeting mee){
		service.updateMee(mee);
	}
	
	@RequestMapping("/meeting/delete")
	public void update(HttpServletRequest request,HttpServletResponse response,Integer meeId){
		service.deleteMee(meeId);
	}
	
	@RequestMapping("/meeting/query")
	@ResponseBody
	public List<Meeting> query(HttpServletRequest request,HttpServletResponse response){
		return service.query();
	}
	

}
