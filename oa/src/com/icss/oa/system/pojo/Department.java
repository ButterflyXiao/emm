package com.icss.oa.system.pojo;

/**
 * 部门实体类
 * 
 * @author Administrator
 *
 */
public class Department {

	private Integer deptId;

	private String deptName;

	private String deptInfo;
	
	public Department() {
		super();
	}

	public Department(String deptName, String deptInfo) {
		super();
		this.deptName = deptName;
		this.deptInfo = deptInfo;
	}

	public Department(Integer deptId, String deptName, String deptInfo) {
		super();
		this.deptId = deptId;
		this.deptName = deptName;
		this.deptInfo = deptInfo;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptInfo() {
		return deptInfo;
	}

	public void setDeptInfo(String deptInfo) {
		this.deptInfo = deptInfo;
	}

	@Override
	public String toString() {
		return "Department [deptId=" + deptId + ", deptName=" + deptName + ", deptInfo=" + deptInfo + "]";
	}	

}