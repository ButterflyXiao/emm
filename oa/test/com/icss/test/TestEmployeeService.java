package com.icss.test;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.TextField;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.document.Field.Store;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icss.oa.common.Pager;
import com.icss.oa.system.index.EmpIndexDao;
import com.icss.oa.system.pojo.Employee;
import com.icss.oa.system.service.EmployeeService;

/**
 * Ա��ҵ�������
 * 
 * @author Administrator
 *
 */
public class TestEmployeeService {

	// ����Spring�������Ķ���
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	// ���service����
	EmployeeService service = context.getBean(EmployeeService.class);
	//�������dao����
	EmpIndexDao indexDao = context.getBean(EmpIndexDao.class);

	@Test
	public void testCheckLogin() {
		int result = service.checkLogin("zhangsan", "123456");
		System.out.println(result);
	}

	@Test
	public void testQueryEmpByPage() {

		Pager pager = new Pager(service.getEmpCount(), 7, 1);

		List<Employee> list = service.queryEmpByPage(pager);

		for (Employee emp : list) {
			System.out.println(emp);
		}

	}

	/**
	 * �ؽ�����
	 */
	@Test
	public void testCreateIndex() {

		Pager pager = new Pager(service.getEmpCount(), service.getEmpCount(), 1);

		List<Employee> list = service.queryEmpByPage(pager);

		for (Employee emp : list) {
			System.out.println(emp);
			
			// ����
			try {				

				// ���������ĵ�
				Document document = new Document();

				document.add(new TextField("empId", String.valueOf(emp.getEmpId()), Store.YES));
				document.add(new TextField("empName", emp.getEmpName(), Store.YES));
				document.add(new TextField("empPhone", emp.getEmpPhone(), Store.YES));
				document.add(new TextField("empInfo", emp.getEmpInfo(), Store.YES));

				// ��������dao
				indexDao.create(document);
				System.out.println("�����Ѵ���");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	/**
	 * ȫ�ļ�������
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws InvalidTokenOffsetsException 
	 */
	@Test
	public void testQueryEmpByIndex() throws ParseException, IOException, InvalidTokenOffsetsException {		

		List<Employee> list = service.queryEmpByIndex("13912345678");

		for (Employee emp : list) {
			System.out.println(emp);
		}

	}	

}