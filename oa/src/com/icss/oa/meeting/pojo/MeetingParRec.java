package com.icss.oa.meeting.pojo;

public class MeetingParRec {
	private MeetingRoom meetingRoom;
	private Meeting meeting;
	public MeetingRoom getMeetingRoom() {
		return meetingRoom;
	}
	public void setMeetingRoom(MeetingRoom meetingRoom) {
		this.meetingRoom = meetingRoom;
	}
	public Meeting getMeeting() {
		return meeting;
	}
	public void setMeeting(Meeting meeting) {
		this.meeting = meeting;
	}
	
	public MeetingParRec(MeetingRoom meetingRoom, Meeting meeting) {
		super();
		this.setMeetingRoom(meetingRoom);
		this.setMeeting(meeting);
	}
	public MeetingParRec() {
		super();
	}
	@Override
	public String toString() {
		return "MeetingParRec [meetingRoom=" + meetingRoom + ", meeting=" + meeting + "]";
	}
	
}
