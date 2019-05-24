package com.icss.oa.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.system.dao.JobMapper;
import com.icss.oa.system.pojo.Job;

/**
 * 部门业务
 * @author Administrator
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class JobService {
	
	@Autowired
	private JobMapper mapper;
	
	/**
	 * 增加部门
	 */	
	public void addJob(Job job) {	
		mapper.insert(job);		
	}
	
	/**
	 * 修改部门
	 */
	public void updateJob(Job job) {
		mapper.update(job);
	}
	
	/**
	 * 删除部门
	 */
	public void deleteJob(Integer jobId) {
		mapper.delete(jobId);
	}
	
	/**
	 * 根据id查询部门
	 */
	@Transactional(readOnly=true)
	public Job queryJobById(Integer jobId) {
		return mapper.queryById(jobId);
	}
	
	/**
	 * 查询所有部门
	 */
	@Transactional(readOnly=true)
	public List<Job> queryJob() {
		return mapper.query();
	}	

}