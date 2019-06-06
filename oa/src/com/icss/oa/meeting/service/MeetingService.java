package com.icss.oa.meeting.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icss.oa.common.Pager;
import com.icss.oa.meeting.dao.MeetingMapper;
import com.icss.oa.meeting.pojo.Meeting;
/**
 * 员工业务
 * @author Administrator
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class MeetingService {
	
	@Autowired
	private MeetingMapper mapper;
	

	/**
	 *增加会议
	 */
	public void addMee(Meeting mee) {
		mapper.insert(mee);
	}
	
	public void deleteMee(Integer meeId){
		mapper.delete(meeId);
	}
	
	public void updateMee(Meeting mee){
		mapper.update(mee);
	}
	
	/**
	 * 分页查询会议
	 */
	@Transactional(readOnly=true)
	public List<Meeting> queryMeeByPage(Pager pager) {
		return mapper.queryByPage(pager.getStart(), pager.getPageSize());
	}	
	
	@Transactional(readOnly=true)
	public List<Meeting> query(){
		return mapper.query();
	}
	
	@Transactional(readOnly=true)
	public int getMeeCount() {
		return mapper.getCount();
	}
	
	
	@Transactional(readOnly=true)
	List<Meeting> queryByPage(Integer start,Integer pageSize){
		return queryByPage(start, pageSize);
	}
	
	@Transactional(readOnly=true)
	List<Meeting> queryByTopic(String meeTopic){
		return queryByTopic(meeTopic);
	}
	@Transactional(readOnly=true)
	List<Meeting> queryByCondition(Integer start,Integer pageSize,Integer roomId,Integer empId,String meeTopic,String meeApprovalStatus,Date meeStartTime){
		return queryByCondition(start, pageSize, roomId, empId, meeTopic, meeApprovalStatus, meeStartTime);
	}
	
	@Transactional(readOnly=true)
	public int getMeeCountByCondition(Integer roomId,  String meeTopic, String meeApprovalStatus
			) {
		return mapper.getCountByCondition(roomId, meeTopic, meeApprovalStatus);
	}

	public List<Meeting> queryMeeByCondition(Pager pager, Integer roomId,  String meeTopic,
			String meeApprovalStatus) {
		return mapper.queryByCondition(pager.getStart(), pager.getPageSize(),roomId,meeTopic,meeApprovalStatus);
	}

	public Meeting queryMeeById(Integer meeId) {
		return mapper.queryById(meeId);
	}



	
	

}