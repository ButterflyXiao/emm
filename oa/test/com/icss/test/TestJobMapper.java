package com.icss.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.system.dao.JobMapper;
import com.icss.oa.system.pojo.Job;

/**
 * ְ��dao������
 * @author Administrator
 *
 */
public class TestJobMapper {
	
	//���Spring������������
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	//���mapper����dao����
	JobMapper mapper = context.getBean(JobMapper.class);
	
	@Test
	public void testInsert() {		
		Job job = new Job("xxxx", 111, 222);		
		mapper.insert(job);		
	}	
	
	@Test
	public void testUpdate() {		
		Job job = new Job(6,"�г��ƹ�", 4600, 8100);		
		mapper.update(job);	
	}
	
	@Test
	public void testDelete() {		
		mapper.delete(8);	
	}
	
	@Test
	public void testQueryById() {		
		
		Job job = mapper.queryById(4);
		System.out.println(job);
		
	}
	
	@Test
	public void testQuery() {		
		List<Job> list = mapper.query();		
		System.out.println(list);		
	}

}