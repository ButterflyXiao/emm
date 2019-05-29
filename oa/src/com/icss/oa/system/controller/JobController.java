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
 * ְ�������
 * @author Administrator
 *
 */
@Controller
public class JobController {
	
	@Autowired
	private JobService service;

	/**
	 * ���Ӳ���
	 * @param request
	 * @param response
	 * @param Job
	 */
	@RequestMapping("/job/add")
	public void add(HttpServletRequest request,HttpServletResponse response,Job Job) {
		service.addJob(Job);
	}
	
	/**
	 * �޸Ĳ���
	 * @param request
	 * @param response
	 * @param Job
	 */
	@RequestMapping("/job/update")
	public void update(HttpServletRequest request,HttpServletResponse response,Job Job) {
		service.updateJob(Job);
	}
	
	/**
	 * ɾ������
	 * @param request
	 * @param response
	 * @param Job
	 */
	@RequestMapping("/job/delete")
	public void delete(HttpServletRequest request,HttpServletResponse response,Integer jobId) {
		service.deleteJob(jobId);
	}
	
	/**
	 * ����id��ѯ����
	 * @param request
	 * @param response
	 * @param Job
	 */
	@RequestMapping("/job/get")
	@ResponseBody
	public Job get(HttpServletRequest request,HttpServletResponse response,Integer jobId) {
		return service.queryJobById(jobId);
	}	
	
	/**
	 * ��ѯ����
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