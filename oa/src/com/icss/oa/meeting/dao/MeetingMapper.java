package com.icss.oa.meeting.dao;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.icss.oa.meeting.pojo.Meeting;
import com.icss.oa.system.pojo.Employee;

public interface MeetingMapper {
	void insert(Meeting mee);
	void delete(Integer meeId);
	void update(Meeting mee);
	Meeting queryById(Integer meeId);
	List<Meeting> query();

	List<Meeting> queryByPage(@Param("start") Integer start,@Param("pageSize") Integer pageSize);
	List<Meeting> queryByTopic(String meeTopic);
	List<Meeting> queryByCondition(@Param("start") Integer start,@Param("pageSize") Integer pageSize,@Param("roomId") Integer roomId,@Param("empId") Integer empId,
			@Param("meeTopic") String meeTopic,@Param("meeRecord") Date meeStartTime);
	
	int getCountByCondition(@Param("start") Integer start,@Param("pageSize") Integer pageSize,@Param("roomId") Integer roomId,@Param("empId") Integer empId,
			@Param("meeTopic") String meeTopic,@Param("meeRecord") Date meeStartTime);

}
