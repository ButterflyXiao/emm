package com.icss.oa.meeting.dao;

import java.util.List;

import com.icss.oa.meeting.pojo.MeetingRoom;

public interface MeetingRoomMapper {
	void insert(MeetingRoom room);
	void delete(Integer roomId);
	void update(MeetingRoom room);
	MeetingRoom queryById(Integer roomId);
	List<MeetingRoom> query();
	
	//»±∑÷“≥≤È’“
}
