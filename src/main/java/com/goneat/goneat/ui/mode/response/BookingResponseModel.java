package com.goneat.goneat.ui.mode.response;

public class BookingResponseModel {
	private OperationStatusResponseModel status;
	private BookedDetails details;
	public OperationStatusResponseModel getStatus() {
		return status;
	}
	public void setStatus(OperationStatusResponseModel status) {
		this.status = status;
	}
	public BookedDetails getDetails() {
		return details;
	}
	public void setDetails(BookedDetails details) {
		this.details = details;
	}
	
}
