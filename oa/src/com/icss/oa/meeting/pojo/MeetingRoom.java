package com.icss.oa.meeting.pojo;

public class MeetingRoom {
	private Integer roomId;
	private String roomPlace;
	private Boolean isReservation;
	private Integer roomSize;
	private Boolean hasAirConditoning;
	public Integer getRoomId() {
		return roomId;
	}
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}
	public String getRoomPlace() {
		return roomPlace;
	}
	public void setRoomPlace(String roomPlace) {
		this.roomPlace = roomPlace;
	}
	public Boolean getIsReservation() {
		return isReservation;
	}
	public void setIsReservation(Boolean isReservation) {
		this.isReservation = isReservation;
	}
	public Integer getRoomSize() {
		return roomSize;
	}
	public void setRoomSize(Integer roomSize) {
		this.roomSize = roomSize;
	}
	public Boolean getHasAirConditoning() {
		return hasAirConditoning;
	}
	public void setHasAirConditoning(Boolean hasAirConditoning) {
		this.hasAirConditoning = hasAirConditoning;
	}
	public MeetingRoom(Integer roomId, String roomPlace, Boolean isReservation, Integer roomSize,
			Boolean hasAirConditoning) {
		super();
		this.roomId = roomId;
		this.roomPlace = roomPlace;
		this.isReservation = isReservation;
		this.roomSize = roomSize;
		this.hasAirConditoning = hasAirConditoning;
	}
	public MeetingRoom( String roomPlace, Boolean isReservation, Integer roomSize,
			Boolean hasAirConditoning) {
		super();
		this.roomPlace = roomPlace;
		this.isReservation = isReservation;
		this.roomSize = roomSize;
		this.hasAirConditoning = hasAirConditoning;
	}
	public MeetingRoom() {
		super();
	}
	@Override
	public String toString() {
		return "MeetingRoom [roomId=" + roomId + ", roomPlace=" + roomPlace + ", IsReservation=" + isReservation
				+ ", roomSize=" + roomSize + ", hasAirConditoning=" + hasAirConditoning + "]";
	}
}
