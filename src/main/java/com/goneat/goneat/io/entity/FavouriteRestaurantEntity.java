package com.goneat.goneat.io.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="favouriteRestaurant")
public class FavouriteRestaurantEntity {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false)
	private String userId;
	
	@Column(nullable=false)
	private Long restaurantId;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}


	
	
}
