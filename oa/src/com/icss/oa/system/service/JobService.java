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
	public void addJob(Job Job) {		
		mapper.insert(Job);		
	}	
	
	/**
	 * �޸Ĳ���
	 */
	public void updateJob(Job Job) {
		mapper.update(Job);
	}

	/**
	 * ɾ������
	 */
	public void deleteJob(Integer JobId) {
		mapper.delete(JobId);
	}
	
	/**
	 * ͨ��id��ѯ����
	 */
	@Transactional(readOnly=true)
	public Job queryJobById(Integer JobId) {
		return mapper.queryById(JobId);
	}
	
	/**
	 * ��ѯ���в���
	 */
	@Transactional(readOnly=true)
	public List<Job> queryJob() {
		return mapper.query();
	}
	
}