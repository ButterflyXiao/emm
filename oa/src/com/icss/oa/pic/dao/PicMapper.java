package com.icss.oa.pic.dao;

import com.icss.oa.pic.pojo.Pic;

/**
 * ͼ�����ݷ���
 * @author Administrator
 *
 */
public interface PicMapper {
	
	void insert(Pic pic);
	
	Pic queryById(Integer picId);

}