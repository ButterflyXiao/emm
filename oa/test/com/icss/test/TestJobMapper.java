package com.icss.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.system.dao.JobMapper;
import com.icss.oa.system.pojo.Job;
import com.icss.oa.system.pojo.Job;

public class TestJobMapper {

	// ����Spring�������Ķ���
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	// ���Mapper����dao����
	JobMapper mapper = context.getBean(JobMapper.class);

	@Test
	public void testInsert() {
		// ����pojo����
		Job job = new Job("ddd", 2,3);
		// �������ݲ���
		mapper.insert(job);
	}

	@Test
	public void testUpdate() {
		// ����pojo����
		Job job = new Job(9,"��ѯ��",2,3);
		// �������ݲ���
		mapper.update(job);
	}
	
	@Test
	public void testDelete() {
		mapper.delete(1);
	}
	
	@Test
	public void testQueryById() {		
		Job job = mapper.queryById(1);
		System.out.println(job);		
	}
	
	@Test
	public void testQuery() {		
		List<Job> list = mapper.query();
		
		for (Job job : list) {
			System.out.println(job);
		}
	}	
}
