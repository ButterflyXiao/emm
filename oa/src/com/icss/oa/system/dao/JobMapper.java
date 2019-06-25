package com.icss.oa.system.dao;

import java.util.List;

import com.icss.oa.system.pojo.Job;

/**
 * 职务数据访问
 * @author Administrator
 *
 */
public interface JobMapper {
	
	void insert(Job job);
	
	void update(Job job);
	
	void delete(Integer jobId);
	
	Job queryById(Integer jobId);
	
	List<Job> query();
    
}