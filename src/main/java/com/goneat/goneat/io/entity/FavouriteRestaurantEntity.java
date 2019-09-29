package com.goneat.goneat.io.entity;

import javax.persistence.*;

@Entity(name="favouriteRestaurant")
@Table(name="booking",
		indexes = {@Index(name="my_index_1", columnList = "restaurantId")})
public class FavouriteRestaurantEntity {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="userId",nullable=false)
	private String userId;
	
	@Column(name="restaurantId",nullable=false)
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
