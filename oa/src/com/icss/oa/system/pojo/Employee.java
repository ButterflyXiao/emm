package com.icss.oa.system.pojo;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

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
	
//	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date empBirthdate;
	
	private String empPhone;
	
	private String empEmail;
	
	private Integer empSal;
	
	private String empInfo;
	
	private Department dept; //多对一
	
	private Job job;//多对一
	
	private String empPhoto;

	public Employee() {
		super();
	}
	
	public Employee(String empName, String empLoginName, String empPwd, String empSex, Date empBirthdate,
			String empPhone, String empEmail, Integer empSal, String empInfo, Department dept, Job job) {
		super();
		this.empName = empName;
		this.empLoginName = empLoginName;
		this.empPwd = empPwd;
		this.empSex = empSex;
		this.empBirthdate = empBirthdate;
		this.empPhone = empPhone;
		this.empEmail = empEmail;
		this.empSal = empSal;
		this.empInfo = empInfo;
		this.dept = dept;
		this.job = job;
	}

	public Employee(Integer empId, String empName, String empLoginName, String empPwd, String empSex, Date empBirthdate,
			String empPhone, String empEmail, Integer empSal, String empInfo, Department dept, Job job,
			String empPhoto) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empLoginName = empLoginName;
		this.empPwd = empPwd;
		this.empSex = empSex;
		this.empBirthdate = empBirthdate;
		this.empPhone = empPhone;
		this.empEmail = empEmail;
		this.empSal = empSal;
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

	public Date getEmpBirthdate() {
		return empBirthdate;
	}

	public void setEmpBirthdate(Date empBirthdate) {
		this.empBirthdate = empBirthdate;
	}

	public String getEmpPhone() {
		return empPhone;
	}

	public void setEmpPhone(String empPhone) {
		this.empPhone = empPhone;
	}

	public String getEmpEmail() {
		return empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

	public Integer getEmpSal() {
		return empSal;
	}

	public void setEmpSal(Integer empSal) {
		this.empSal = empSal;
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
				+ empPwd + ", empSex=" + empSex + ", empBirthdate=" + empBirthdate + ", empPhone=" + empPhone
				+ ", empEmail=" + empEmail + ", empSal=" + empSal + ", empInfo=" + empInfo + ", dept=" + dept + ", job="
				+ job + "]";
	}
	
}