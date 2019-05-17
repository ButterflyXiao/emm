package com.icss.oa.common;

import org.junit.experimental.theories.Theories;

public class Pager {

	private int record;//总记录数
	private int pageSize;//每页多少条
	private int pageCount;//共几页
	private int pageNum;//当前页码
	private int start;
	
	public Pager(int record,int pageSize,int PageNum){
		this.record=record;
		this.pageSize=pageSize;
		this.pageNum=PageNum;
		
		this.pageCount=this.record/this.pageSize;
		if(this.record%this.pageSize!=0)
			this.pageCount++;
		
		if(this.pageNum<1)
			pageNum=1;
		if(this.pageNum>pageCount)
			this.pageNum=this.pageCount;
		
		start=(this.pageNum-1)*this.pageSize;
	}

	public int getRecord() {
		return record;
	}

	public void setRecord(int record) {
		this.record = record;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}
	
}
