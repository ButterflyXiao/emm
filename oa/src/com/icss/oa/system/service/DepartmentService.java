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
 * 部门业务
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
	 * 增加部门
	 */
	public void addDept(Department dept) {
		mapper.insert(dept);
	}

	/**
	 * 修改部门
	 */
	public void updateDept(Department dept) {
		mapper.update(dept);
	}

	/**
	 * 删除部门
	 */
	public void deleteDept(Integer deptId) {
		mapper.delete(deptId);
	}

	/**
	 * 通过id查询部门
	 */
	@Transactional(readOnly = true)
	public Department queryDeptById(Integer deptId) {
		return mapper.queryById(deptId);
	}

	/**
	 * 查询所有部门
	 */
	@Transactional(readOnly = true)
	public List<Department> queryDept() {
		return mapper.query();
	}

	/**
	 * 导出excel报表
	 * @throws IOException 
	 */
	@Transactional(readOnly = true)
	public void exportExcel(OutputStream out) throws IOException {

		// 工作簿对象
		HSSFWorkbook wb = new HSSFWorkbook();

		// 工作表对象
		HSSFSheet sheet1 = wb.createSheet("部门数据");

		// 行对象
		HSSFRow headerRow = sheet1.createRow(0);
		
		headerRow.createCell(0).setCellValue("部门编号");
		headerRow.createCell(1).setCellValue("部门名称");
		headerRow.createCell(2).setCellValue("部门描述");
		
		//获得部门数据列表
		List<Department> list = mapper.query();
		
		//遍历数据
		for (int i = 0;i < list.size();i ++) {
			Department dept = list.get(i);
			
			HSSFRow row = sheet1.createRow(i+1);
			row.createCell(0).setCellValue(dept.getDeptId());
			row.createCell(1).setCellValue(dept.getDeptName());
			row.createCell(2).setCellValue(dept.getDeptInfo());			
		}
		
		//写入到数据流中
		wb.write(out);
		out.close();
	}

}