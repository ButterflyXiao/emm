package com.icss.oa.system.service;

import java.util.List;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.common.Pager;
import com.icss.oa.system.dao.EmployeeMapper;
import com.icss.oa.system.pojo.Employee;

/**
 * Ա��ҵ��
 * @author Administrator
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class EmployeeService {
	
	@Autowired
	private EmployeeMapper mapper;
	
	/**
	 * ��¼��֤
	 * ����1 �û���������  2�������  3��¼�ɹ�
	 */
	@Transactional(readOnly=true)
	public int checkLogin(String empLoginName,String empPwd) {
		
		Employee emp = mapper.queryByName(empLoginName);
		
		if (emp == null)
			return 1;
		else if (!empPwd.equals(emp.getEmpPwd()))
			return 2;
		else 
			return 3;
	}
	
	/**
	 *����Ա��
	 */
	public void addEmp(Employee emp) {
		mapper.insert(emp);
	}	
	
	/**
	 * ��ҳ��ѯԱ��
	 */
	@Transactional(readOnly=true)
	public List<Employee> queryEmpByPage(Pager pager) {
		return mapper.queryByPage2(pager.getStart(), pager.getPageSize());
	}	
	
	/**
	 * ����Ա���ܼ�¼��
	 */
	@Transactional(readOnly=true)
	public int getEmpCount() {
		return mapper.getCount();
	}
	
	
	/**
	 * ���ݵ�¼����ѯԱ��
	 */
	@Transactional(readOnly=true)
	public Employee queryEmpByName(String empLoginName) {
		return mapper.queryByName(empLoginName);
	}

}