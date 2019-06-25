package com.icss.test;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.system.dao.DepartmentMapper;
import com.icss.oa.system.pojo.Department;

/**
 * ���Բ���dao��
 * @author Administrator
 *
 */
public class TestDepartmentMapper {
	
	//���Spring������������
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	//���Mapper����dao����
	DepartmentMapper mapper = context.getBean(DepartmentMapper.class);
	

	@Test
	public void testInsert() {	
		//����pojo����
		Department dept = new Department("fff", "fff");		
		//�������ݲ���
		mapper.insert(dept);
	}
	
	@Test
	public void testUpdate() {			
		//����pojo����
		Department dept = new Department(7,"��Ϣ��ȫ��", "��Ϣ��ȫ����");		
		//�������ݲ���
		mapper.update(dept);
	}
	
	@Test
	public void testDelete() {	
		//�������ݲ���
		mapper.delete(8);
	}
	
	@Test
	public void testQueryById() {	
		//���ݲ�ѯ����
		Department dept = mapper.queryById(16);
		System.out.println(dept);
	}
	
	@Test
	public void testQuery() {	
		List<Department> list = mapper.query();
		
		for (Department dept : list) {
			System.out.println(dept);
		}
	}
	
	@Test
	public void testQuery2() {	
		List<Map<String, Object>> list = mapper.query2();
		
		for (Map dept : list) {
			System.out.println(dept);
		}
	}
	
}