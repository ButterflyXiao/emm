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

/**
 * 职务控制器
 * @author Administrator
 *
 */
@Controller
public class JobController {
	
	@Autowired
	private JobService service;
	
	/**
	 * 增加部门
	 * @param request
	 * @param response
	 * @param job
	 */
	@RequestMapping("/job/add")
	public void add(HttpServletRequest request,HttpServletResponse response,Job job) {
		service.addJob(job);
	}
	
	/**
	 * 修改部门
	 * @param request
	 * @param response
	 * @param job
	 */
	@RequestMapping("/job/update")
	public void update(HttpServletRequest request,HttpServletResponse response,Job job) {
		service.updateJob(job);
	}
	
	/**
	 * 删除部门
	 * @param request
	 * @param response
	 * @param job
	 */
	@RequestMapping("/job/delete")
	public void update(HttpServletRequest request,HttpServletResponse response,Integer jobId) {
		service.deleteJob(jobId);
	}
	
	/**
	 * 通过id查询部门
	 * @param request
	 * @param response
	 * @param job
	 */
	@RequestMapping("/job/get")
	@ResponseBody
	public Job get(HttpServletRequest request,HttpServletResponse response,Integer jobId) {
		return service.queryJobById(jobId);
	}
	
	/**
	 * 查询部门
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/job/query")
	@ResponseBody
	public List<Job> query(HttpServletRequest request,HttpServletResponse response) {
		return service.queryJob();
	}

}