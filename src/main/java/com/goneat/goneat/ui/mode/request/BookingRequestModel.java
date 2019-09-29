package com.goneat.goneat.ui.mode.request;

import java.time.LocalDateTime;

public class BookingRequestModel {
	
	private String userId;
	private String date;
	private String time;
	private Integer numberOfGuests;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Integer getNumberOfGuests() {
		return numberOfGuests;
	}
	public void setNumberOfGuests(Integer numberOfGuests) {
		this.numberOfGuests = numberOfGuests;
	}
	
	

}
