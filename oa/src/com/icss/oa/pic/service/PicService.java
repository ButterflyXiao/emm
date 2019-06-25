package com.icss.oa.pic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.pic.dao.PicMapper;
import com.icss.oa.pic.pojo.Pic;

/**
 * Í¼¿âÒµÎñ
 * @author Administrator
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class PicService {

	@Autowired
	private PicMapper mapper;
	
	public void addPic(Pic pic) {
		mapper.insert(pic);
	}	
	
	@Transactional(readOnly=true)
	public Pic queryPicById(Integer picId) {
		return mapper.queryById(picId);
	}
		
}