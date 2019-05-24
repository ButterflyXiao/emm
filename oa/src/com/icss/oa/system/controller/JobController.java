package com.icss.oa.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icss.oa.system.pojo.Job;
import com.icss.oa.system.service.JobService;

@Controller
public class JobController {

	@Autowired
	private JobService service;
	
	@RequestMapping("/job/add")
	public void add(HttpServletRequest request,HttpServletResponse response,Job job){
		service.addJob(job);
	}
	
	@RequestMapping("/job/update")
	public void update(HttpServletRequest request,HttpServletResponse response,Job job){
		service.updateJob(job);
	}
	
	@RequestMapping("/job/delete")
	public void update(HttpServletRequest request,HttpServletResponse response,Integer jobId){
		service.deleteJob(jobId);
	}
	
	@RequestMapping("/job/query")
	@ResponseBody
	public List<Job> query(HttpServletRequest request,HttpServletResponse response){
		return service.queryJob();
	}
	
	//µ•Ãı≤È—Ø
	
	@RequestMapping("/job/get")
	@ResponseBody
	public Job get(HttpServletRequest request,HttpServletResponse response,Integer jobId){
		return service.queryJobById(jobId);
	}
}
