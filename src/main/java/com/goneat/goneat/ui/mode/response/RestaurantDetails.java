package com.goneat.goneat.ui.mode.response;

import java.util.List;

public class RestaurantDetails {
	private Long cityId;

	private List<Restaurant> restaurants;
	public Long getCityId() {
		return cityId;
	}
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	public List<Restaurant> getRestaurants() {
		return restaurants;
	}
	public void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}
	
	
}
