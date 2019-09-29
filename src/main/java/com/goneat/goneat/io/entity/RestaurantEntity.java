package com.goneat.goneat.io.entity;

import javax.persistence.*;


@Entity
@Table(name="restaurants",
		indexes = {@Index(name="my_index_1", columnList = "cityId")})
public class RestaurantEntity {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
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
