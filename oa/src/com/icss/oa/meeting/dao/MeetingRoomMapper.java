package com.icss.oa.meeting.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.icss.oa.meeting.pojo.MeetingRoom;

public interface MeetingRoomMapper {
	void insert(MeetingRoom room);
	void delete(Integer roomId);
	void update(MeetingRoom room);
	MeetingRoom queryById(Integer roomId);
	List<MeetingRoom> query();

	List<MeetingRoom> queryByPage(@Param("start") Integer start,@Param("pageSize") Integer pageSize);
	List<MeetingRoom> queryByCondition(@Param("start") Integer start,@Param("pageSize") Integer pageSize,@Param("roomPlace") String roomPlace,@Param("isReservation") Boolean isReservation,@Param("roomSize") Integer roomSize,@Param("hasAirConditoning") Boolean hasAirConditoning);
	
	int getCountByCondition(@Param("roomPlace") String roomPlace,@Param("isReservation") Boolean isReservation,@Param("roomSize") Integer roomSize,@Param("hasAirConditoning") Boolean hasAirConditoning);

}
