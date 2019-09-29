package com.goneat.goneat.ui.controllers;

import java.beans.Beans;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.goneat.goneat.service.UserService;
import com.goneat.goneat.shared.dto.UserDto;
import com.goneat.goneat.ui.mode.request.FavouriteRestaurantRequestModel;
import com.goneat.goneat.ui.mode.request.UserDetailsRequest;
import com.goneat.goneat.ui.mode.response.FavouriteRestaurantResponseModel;
import com.goneat.goneat.ui.mode.response.UserResponse;

@RestController
@RequestMapping(value="user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping()
	public UserResponse createUser(@RequestBody UserDetailsRequest userReq )
	{
		
		UserDto dto = new UserDto();
		BeanUtils.copyProperties(userReq, dto);
		UserDto returnDto = new UserDto();
		returnDto = userService.createUser(dto);
		UserResponse response = new UserResponse();
		BeanUtils.copyProperties(returnDto, response);
		return response;
	}
	
	@GetMapping
	public String getUser()
	{
		return "get user was called";
	}
	
	@PostMapping("/favourite/userId/{userId}")
	public FavouriteRestaurantResponseModel setFavouriteRestaurant(@PathVariable String userId, @RequestBody FavouriteRestaurantRequestModel details)
	{
		return userService.favouriteRestaurant(userId,details);
	}

}
