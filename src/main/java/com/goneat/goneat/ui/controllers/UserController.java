package com.goneat.goneat.ui.controllers;

import java.beans.Beans;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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

	@GetMapping(path="/{id}")
	public UserResponse getUser(@PathVariable String id)
	{
		UserResponse returnValue = new UserResponse();
		UserDto userDto = userService.findUserByUserId(id);
		BeanUtils.copyProperties(userDto, returnValue);
		return returnValue;
	}

	@GetMapping
	public List<UserResponse> getUsers(@RequestParam(value="page", defaultValue="1")int page,
									   @RequestParam(value="limit", defaultValue="25")int limit)
	{
		List<UserResponse> returnValue = new ArrayList<UserResponse>();
		List<UserDto> users = userService.getUsers(page,limit);

		for(UserDto user:users)
		{
			UserResponse userRest = new UserResponse();
			BeanUtils.copyProperties(user, userRest);
			returnValue.add(userRest);
		}
		return returnValue;
	}



}
