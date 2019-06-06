package com.icss.oa.meeting.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.icss.oa.common.Pager;
import com.icss.oa.meeting.pojo.Meeting;
import com.icss.oa.meeting.pojo.MeetingRoom;
import com.icss.oa.meeting.service.MeetingRoomService;
import com.icss.oa.meeting.service.MeetingService;
import com.icss.oa.pic.pojo.Pic;
import com.icss.oa.system.pojo.Department;
import com.icss.oa.system.pojo.Employee;
import com.icss.oa.system.service.DepartmentService;

@Controller
public class MeetingController {

	@Autowired
	private MeetingService service;
	
	@RequestMapping("/meeting/add")
	public void add(HttpServletRequest request,HttpServletResponse response,Meeting mee){
		HttpSession session = request.getSession();
		int empId=(Integer)session.getAttribute("empId");
		Employee employee=new Employee();
		employee.setEmpId(empId);
		mee.setMeeSponsor(employee);
		
		service.addMee(mee);
	}
	
	@RequestMapping("/meeting/update")
	public void update(MultipartFile meeRecord,Integer empId,HttpServletRequest request,HttpServletResponse response) throws IOException{
		//转换文件数据为byte数组
		byte[] fileDataCopy = FileCopyUtils.copyToByteArray( meeRecord.getInputStream() );
		Meeting mee=new Meeting();
		Employee sponsor=new Employee();
		sponsor.setEmpId(empId);
		mee.setMeeId(8);
		mee.setMeeSponsor(sponsor);
		mee.setMeeRecord(fileDataCopy);
				
		service.updateMee(mee);
	}
	
	@RequestMapping("/meeting/delete")
	public void update(HttpServletRequest request,HttpServletResponse response,Integer meeId){
		System.out.println("删除");
		service.deleteMee(meeId);
	}
	
	@RequestMapping("/meeting/query")
	@ResponseBody
	public HashMap<String, Object> query(HttpServletRequest request,HttpServletResponse response,Integer pageSize,Integer pageNum) {
		
		if (pageSize == null)
			pageSize = 10;
		
		if (pageNum == null)
			pageNum = 1;
		
		Pager pager = new Pager(service.getMeeCount(), pageSize, pageNum);		
		List<Meeting> list = service.queryMeeByPage(pager);
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("pager",pager);
		map.put("list", list);
		
		return map;
	}
	
	/**
	 * 通过id返回员工
	 */
	@RequestMapping("/meeting/get")
	@ResponseBody
	public Meeting get(HttpServletRequest request,HttpServletResponse response,Integer meeId) {		
		Meeting mee = service.queryMeeById(meeId);
		return mee;			
	}
	
	
	@RequestMapping("/meeting/search")
	@ResponseBody
	public HashMap<String, Object> search(HttpServletRequest request,HttpServletResponse response,Integer pageSize,Integer pageNum,
			Integer roomId,String meeTopic,String meeApprovalStatus) {
		
		if (pageSize == null)
			pageSize = 10;
		
		if (pageNum == null)
			pageNum = 1;
		
		Pager pager = new Pager(service.getMeeCountByCondition(roomId,meeTopic,meeApprovalStatus), pageSize, pageNum);		
		List<Meeting> list = service.queryMeeByCondition(pager,roomId,meeTopic,meeApprovalStatus);
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("pager",pager);
		map.put("list", list);
		
		return map;
	}
	

}
