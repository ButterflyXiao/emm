package com.icss.oa.system.service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.system.dao.DepartmentMapper;
import com.icss.oa.system.pojo.Department;

/**
 * ����ҵ��
 * 
 * @author Administrator
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DepartmentService {

	@Autowired
	private DepartmentMapper mapper;

	/**
	 * ���Ӳ���
	 */
	public void addDept(Department dept) {
		mapper.insert(dept);
	}

	/**
	 * �޸Ĳ���
	 */
	public void updateDept(Department dept) {
		mapper.update(dept);
	}

	/**
	 * ɾ������
	 */
	public void deleteDept(Integer deptId) {
		mapper.delete(deptId);
	}

	/**
	 * ͨ��id��ѯ����
	 */
	@Transactional(readOnly = true)
	public Department queryDeptById(Integer deptId) {
		return mapper.queryById(deptId);
	}

	/**
	 * ��ѯ���в���
	 */
	@Transactional(readOnly = true)
	public List<Department> queryDept() {
		return mapper.query();
	}

	/**
	 * ����excel����
	 * @throws IOException 
	 */
	@Transactional(readOnly = true)
	public void exportExcel(OutputStream out) throws IOException {

		// ����������
		HSSFWorkbook wb = new HSSFWorkbook();

		// ���������
		HSSFSheet sheet1 = wb.createSheet("��������");

		// �ж���
		HSSFRow headerRow = sheet1.createRow(0);
		
		headerRow.createCell(0).setCellValue("���ű��");
		headerRow.createCell(1).setCellValue("��������");
		headerRow.createCell(2).setCellValue("��������");
		
		//��ò��������б�
		List<Department> list = mapper.query();
		
		//��������
		for (int i = 0;i < list.size();i ++) {
			Department dept = list.get(i);
			
			HSSFRow row = sheet1.createRow(i+1);
			row.createCell(0).setCellValue(dept.getDeptId());
			row.createCell(1).setCellValue(dept.getDeptName());
			row.createCell(2).setCellValue(dept.getDeptInfo());			
		}
		
		//д�뵽��������
		wb.write(out);
		out.close();
	}

}