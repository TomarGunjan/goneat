package com.goneat.goneat.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.goneat.goneat.shared.dto.UserDto;
import com.goneat.goneat.ui.mode.request.FavouriteRestaurantRequestModel;
import com.goneat.goneat.ui.mode.response.FavouriteRestaurantResponseModel;

import java.util.List;

public interface UserService extends UserDetailsService{

	UserDto createUser(UserDto userDto);
	UserDto findUserByUserId(String userId);
	UserDto findUserByUsername(String userName);
	List<UserDto> getUsers(int page, int limit);
	
	
}
