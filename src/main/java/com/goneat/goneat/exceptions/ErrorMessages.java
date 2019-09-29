package com.goneat.goneat.exceptions;

public enum ErrorMessages {
	RESTAURANTNOTFOUND("Restaurant Not Found"),
	USERNOTDFOUND("User Not Found"), 
	NOTFUTUREBOOKINGDATE("BOOKING TIME SHOULD BE OF FUTURE"),
	INVALIDGUESTCAPACITY("Invalid Guest Number Provided"),
	OUTOFCAPACITY("Guest Capacity cannot be accomodated!!");
	
	ErrorMessages(String msg)
	{
		this.message=msg;
	}
	private String message;
	public String getMessage() {
		return message;
	}	
	
}
