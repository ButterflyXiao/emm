package com.icss.oa.common;

/**
 * ������ҳ���㹦��
 * @author Administrator
 *
 */
public class Pager {
	
	private int recordCount; //�ܼ�¼��
	
	private int pageSize;//ÿҳ������
	
	private int pageCount;//����ҳ
	
	private int pageNum;//��ǰҳ��
	
	private int start;//��ǰҳ�ļ�¼��ʼ�±�
		
	/**
	 * ��ҳ����
	 * @param recordCount
	 * @param pageSize
	 * @param pageNum
	 */
	public Pager(int recordCount,int pageSize,int pageNum) {
		
		this.recordCount = recordCount;
		this.pageSize = pageSize;
		this.pageNum = pageNum;
		
		//����ҳ
		this.pageCount = this.recordCount / this.pageSize;
		if (this.recordCount % this.pageSize != 0)
			this.pageCount ++;
		
		//����ҳ��		
		if (this.pageNum > this.pageCount)
			this.pageNum = this.pageCount;
		
		if (this.pageNum < 1)
			this.pageNum = 1;
		
		//������ʼλ��
		this.start = (this.pageNum - 1) * this.pageSize;		
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
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
	
//	public static void main(String[] args) {		
//		Pager pager = new Pager(0, 15, 1);
//		System.out.println(pager.getPageCount());
//		System.out.println(pager.getStart());		
//	}
}