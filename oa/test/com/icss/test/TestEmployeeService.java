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
 * 
 * @author Administrator
 *
 */
public class TestEmployeeService {
	
	//���Spring������������
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	//���Service����
	EmployeeService service = context.getBean(EmployeeService.class);
	
	EmpIndexDao indexDao = context.getBean(EmpIndexDao.class);
	
	@Test
	public void testCheckLogin() {		
		int result = service.checkLogin("zhangsan2", "123456");
		System.out.println(result);		
	}	
	
	@Test
	public void testQueryEmpByPage() {		
		
		Pager pager = new Pager(service.getEmpCount(), 7,2);
		List<Employee> list = service.queryEmpByPage(pager);
		
		for (Employee emp : list) {
			System.out.println(emp);
		}
		
	}
	
	@Test
	public void testQueryEmpByCondition() {		
		
		Pager pager = new Pager(service.getEmpCount(), 7,1);
		List<Employee> list = service.queryEmpByCondition(pager, 5, 9, "��");
		
		for (Employee emp : list) {
			System.out.println(emp);
		}
		
	}
	
	/**
	 * �ؽ�Ա��������
	 */
	@Test
	public void testCreateIndex(){
		
		Pager pager = new Pager(service.getEmpCount(),service.getEmpCount() ,1);
		List<Employee> list = service.queryEmpByPage(pager);
		
		for (Employee emp : list) {
						
			System.out.println(emp);
			
			try {
				/********** �������� *************/
				// ���������ĵ�
				Document document = new Document();
				document.add(new TextField("empId", String.valueOf(emp.getEmpId())  , Store.YES));
				document.add(new TextField("empName", emp.getEmpName(), Store.YES));
				document.add(new TextField("empPhone", emp.getEmpPhone(), Store.YES));
				document.add(new TextField("empInfo", emp.getEmpInfo(), Store.YES));

				// ��������dao
				indexDao.create(document);
				
				System.out.println("�����Ѵ���");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}	
		
	}
	
	
	/**
	 * ����ȫ�ļ���
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws InvalidTokenOffsetsException 
	 */
	@Test
	public void testQueryByIndex() throws ParseException, IOException, InvalidTokenOffsetsException {
		
		List<Employee> list = service.queryEmpByIndex("13812345678");
		
		for (Employee emp : list) {
			System.out.println(emp);
		}	
		
	}

}