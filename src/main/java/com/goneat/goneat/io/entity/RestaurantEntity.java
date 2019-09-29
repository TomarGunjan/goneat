package com.goneat.goneat.io.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;



@Entity(name="restaurants")
public class RestaurantEntity {
	
	@Id
	@Column
	private long restaurantId;
	@Column
	private String restaurantName;
	@Column
	private long cityId;
	
	public long getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(long restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public long getCityId() {
		return cityId;
	}
	public void setCityId(long cityId) {
		this.cityId = cityId;
	}
	
	
	

}
