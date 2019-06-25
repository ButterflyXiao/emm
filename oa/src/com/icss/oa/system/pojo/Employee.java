package com.icss.oa.system.pojo;

import java.sql.Date;

/**
 * 员工实体类
 * @author Administrator
 *
 */
public class Employee {

	private Integer empId;

	private String empName;

	private String empLoginName;

	private String empPwd;

	private String empSex;

	private Date empBirthday;

	private String empPhone;

	private Integer empSal;

	private String empEmail;

	private String empInfo;

	private Department dept;

	private Job job;

	private String empPhoto;

	public Employee() {
		super();
	}

	public Employee(String empName, String empLoginName, String empPwd, String empSex, Date empBirthday,
			String empPhone, Integer empSal, String empEmail, String empInfo, Department dept, Job job) {
		super();
		this.empName = empName;
		this.empLoginName = empLoginName;
		this.empPwd = empPwd;
		this.empSex = empSex;
		this.empBirthday = empBirthday;
		this.empPhone = empPhone;
		this.empSal = empSal;
		this.empEmail = empEmail;
		this.empInfo = empInfo;
		this.dept = dept;
		this.job = job;
	}

	public Employee(Integer empId, String empName, String empLoginName, String empPwd, String empSex, Date empBirthday,
			String empPhone, Integer empSal, String empEmail, String empInfo, Department dept, Job job) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empLoginName = empLoginName;
		this.empPwd = empPwd;
		this.empSex = empSex;
		this.empBirthday = empBirthday;
		this.empPhone = empPhone;
		this.empSal = empSal;
		this.empEmail = empEmail;
		this.empInfo = empInfo;
		this.dept = dept;
		this.job = job;
	}

	public Employee(Integer empId, String empName, String empLoginName, String empPwd, String empSex, Date empBirthday,
			String empPhone, Integer empSal, String empEmail, String empInfo, Department dept, Job job,
			String empPhoto) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empLoginName = empLoginName;
		this.empPwd = empPwd;
		this.empSex = empSex;
		this.empBirthday = empBirthday;
		this.empPhone = empPhone;
		this.empSal = empSal;
		this.empEmail = empEmail;
		this.empInfo = empInfo;
		this.dept = dept;
		this.job = job;
		this.empPhoto = empPhoto;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpLoginName() {
		return empLoginName;
	}

	public void setEmpLoginName(String empLoginName) {
		this.empLoginName = empLoginName;
	}

	public String getEmpPwd() {
		return empPwd;
	}

	public void setEmpPwd(String empPwd) {
		this.empPwd = empPwd;
	}

	public String getEmpSex() {
		return empSex;
	}

	public void setEmpSex(String empSex) {
		this.empSex = empSex;
	}

	public Date getEmpBirthday() {
		return empBirthday;
	}

	public void setEmpBirthday(Date empBirthday) {
		this.empBirthday = empBirthday;
	}

	public String getEmpPhone() {
		return empPhone;
	}

	public void setEmpPhone(String empPhone) {
		this.empPhone = empPhone;
	}

	public Integer getEmpSal() {
		return empSal;
	}

	public void setEmpSal(Integer empSal) {
		this.empSal = empSal;
	}

	public String getEmpEmail() {
		return empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

	public String getEmpInfo() {
		return empInfo;
	}

	public void setEmpInfo(String empInfo) {
		this.empInfo = empInfo;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public String getEmpPhoto() {
		return empPhoto;
	}

	public void setEmpPhoto(String empPhoto) {
		this.empPhoto = empPhoto;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", empLoginName=" + empLoginName + ", empPwd="
				+ empPwd + ", empSex=" + empSex + ", empBirthday=" + empBirthday + ", empPhone=" + empPhone
				+ ", empSal=" + empSal + ", empEmail=" + empEmail + ", empInfo=" + empInfo + ", dept=" + dept + ", job="
				+ job + ", empPhoto=" + empPhoto + "]";
	}

}
