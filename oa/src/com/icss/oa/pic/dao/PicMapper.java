package com.icss.oa.pic.dao;

import com.icss.oa.pic.pojo.Pic;

/**
 * 
 * @author Administrator
 *
 */
public interface PicMapper {
	
	void insert(Pic pic);
	
	Pic queryById(Integer picId);

}