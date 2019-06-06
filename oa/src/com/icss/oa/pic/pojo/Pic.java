package com.icss.oa.pic.pojo;

import java.util.Arrays;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Õºø‚ µÃÂ¿‡
 * 
 * @author Administrator
 *
 */
public class Pic {
	private Integer picId;

	private String picName;

	private Long picSize;

	private String picInfo;

	private Date picTime;

	private String picEmp;

	private byte[] picData;

	public Pic() {
		super();
	}

	public Pic(String picName, Long picSize, String picInfo, Date picTime, String picEmp, byte[] picData) {
		super();
		this.picName = picName;
		this.picSize = picSize;
		this.picInfo = picInfo;
		this.picTime = picTime;
		this.picEmp = picEmp;
		this.picData = picData;
	}

	public Pic(Integer picId, String picName, Long picSize, String picInfo, Date picTime, String picEmp,
			byte[] picData) {
		super();
		this.picId = picId;
		this.picName = picName;
		this.picSize = picSize;
		this.picInfo = picInfo;
		this.picTime = picTime;
		this.picEmp = picEmp;
		this.picData = picData;
	}

	public Integer getPicId() {
		return picId;
	}

	public void setPicId(Integer picId) {
		this.picId = picId;
	}

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName == null ? null : picName.trim();
	}

	public Long getPicSize() {
		return picSize;
	}

	public void setPicSize(Long picSize) {
		this.picSize = picSize;
	}

	public String getPicInfo() {
		return picInfo;
	}

	public void setPicInfo(String picInfo) {
		this.picInfo = picInfo == null ? null : picInfo.trim();
	}

	public Date getPicTime() {
		return picTime;
	}

	public void setPicTime(Date picTime) {
		this.picTime = picTime;
	}

	public String getPicEmp() {
		return picEmp;
	}

	public void setPicEmp(String picEmp) {
		this.picEmp = picEmp == null ? null : picEmp.trim();
	}

	public byte[] getPicData() {
		return picData;
	}

	public void setPicData(byte[] picData) {
		this.picData = picData;
	}

	@Override
	public String toString() {
		return "Pic [picId=" + picId + ", picName=" + picName + ", picSize=" + picSize + ", picInfo=" + picInfo
				+ ", picTime=" + picTime + ", picEmp=" + picEmp + ", picData=" + Arrays.toString(picData) + "]";
	}
		
}