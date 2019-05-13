package com.icss.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.icss.oa.system.dao.DepartmentMapper;
import com.icss.oa.system.pojo.Department;

/**
 * ����dao������
 * 
 * @author Administrator
 *
 */
public class TestDepartmentMapper {

	// ����Spring�������Ķ���
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	// ���Mapper����dao����
	DepartmentMapper mapper = context.getBean(DepartmentMapper.class);

	@Test
	public void testInsert() {
		// ����pojo����
		Department dept = new Department("ddd", "ddd");
		// �������ݲ���
		mapper.insert(dept);
	}

	@Test
	public void testUpdate() {
		// ����pojo����
		Department dept = new Department(9,"��ѯ��","�ͷ�����");
		// �������ݲ���
		mapper.update(dept);
	}
	
	@Test
	public void testDelete() {
		mapper.delete(10);
	}
	
	@Test
	public void testQueryById() {		
		Department dept = mapper.queryById(1);
		System.out.println(dept);		
	}
	
	@Test
	public void testQuery() {		
		List<Department> list = mapper.query();
		
		for (Department dept : list) {
			System.out.println(dept);
		}
	}	

}