package com.icss.oa.meeting.dao;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.mail.search.IntegerComparisonTerm;

import org.apache.ibatis.annotations.Param;

import com.icss.oa.meeting.pojo.Meeting;
import com.icss.oa.meeting.pojo.MeetingRoom;
import com.icss.oa.system.pojo.Employee;

public interface MeetingMapper {
	void insert(Meeting mee);
	void delete(Integer meeId);
	void update(Meeting mee);//不包含参会人
	void updatePar(@Param("meeId")Integer meeId,@Param("meePar")Integer meePar);
	Meeting queryById(Integer meeId);
	List<Meeting> query();
	int getCount();


	List<Meeting> queryByPage(@Param("start") Integer start,@Param("pageSize") Integer pageSize);
	List<Meeting> queryByTopic(String meeTopic);
	List<Meeting> queryByCondition(@Param("start") Integer start,@Param("pageSize") Integer pageSize,@Param("roomId") Integer roomId,@Param("meeTopic") String meeTopic,
			@Param("meeApprovalStatus") String meeApprovalStatus);
	
	int getCountByCondition(@Param("roomId") Integer roomId,@Param("meeTopic") String meeTopic,@Param("meeApprovalStatus") String meeApprovalStatus
			);
	
	int getLastInsertId();
	
	List<Meeting> queryMeeByRoomId(Integer roomId);
	Meeting lastMeeByRoom(@Param("roomId")Integer roomId,@Param("meeApprovalStatus")String meeApprovalStatus);
	void setApproval(Meeting mee);
	List<Meeting> queryMeeBySponsorId(Integer empId);
		
	}
