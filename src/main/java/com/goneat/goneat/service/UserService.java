package com.goneat.goneat.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.goneat.goneat.shared.dto.UserDto;
import com.goneat.goneat.ui.mode.request.FavouriteRestaurantRequestModel;
import com.goneat.goneat.ui.mode.response.FavouriteRestaurantResponseModel;

public interface UserService extends UserDetailsService{

	UserDto createUser(UserDto userDto);
	FavouriteRestaurantResponseModel favouriteRestaurant(String userId, FavouriteRestaurantRequestModel model);
	UserDto findUserByUserId(String userId);
	UserDto findUserByUsername(String userName);
	
	
}
