package com.icss.oa.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.system.dao.JobMapper;
import com.icss.oa.system.pojo.Job;

/**
 * ����ҵ��
 * @author Administrator
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class JobService {
	
	@Autowired
	private JobMapper mapper;
	
	/**
	 * ���Ӳ���
	 */	
	public void addJob(Job job) {	
		mapper.insert(job);		
	}
	
	/**
	 * �޸Ĳ���
	 */
	public void updateJob(Job job) {
		mapper.update(job);
	}
	
	/**
	 * ɾ������
	 */
	public void deleteJob(Integer jobId) {
		mapper.delete(jobId);
	}
	
	/**
	 * ����id��ѯ����
	 */
	@Transactional(readOnly=true)
	public Job queryJobById(Integer jobId) {
		return mapper.queryById(jobId);
	}
	
	/**
	 * ��ѯ���в���
	 */
	@Transactional(readOnly=true)
	public List<Job> queryJob() {
		return mapper.query();
	}	

}