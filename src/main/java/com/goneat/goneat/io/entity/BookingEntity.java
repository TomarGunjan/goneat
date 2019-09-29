package com.goneat.goneat.io.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Index;
import javax.persistence.Id;

@Entity
@Table(name="booking",
		indexes = {@Index(name="my_index_1", columnList = "restId"),
				@Index(name="my_index_2",columnList="activation_status")})
public class BookingEntity{

	@Id
	@Column(nullable=false)
	private String bookingId;
	
	@Column(nullable=false)
	private long restId;
	
	@Column(name="startDateTime",nullable=false)
	private Timestamp startDateTime;
	
	@Column(name="numOfGuests",nullable=false)
	private int numOfGuests;
	
	@Column(nullable=false)
	private String userId;
	
	@Column(name="activation_status",columnDefinition = "VARCHAR(15) NULL DEFAULT 'active'")
	private String activationStatus;

	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public long getRestId() {
		return restId;
	}

	public void setRestId(long restId) {
		this.restId = restId;
	}

	public Timestamp getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(Timestamp startDateTime) {
		this.startDateTime = startDateTime;
	}

	public int getNumOfGuests() {
		return numOfGuests;
	}

	public void setNumOfGuests(int numOfGuests) {
		this.numOfGuests = numOfGuests;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getActivationStatus() {
		return activationStatus;
	}

	public void setActivationStatus(String activationStatus) {
		this.activationStatus = activationStatus;
	}
	
	
	

}
