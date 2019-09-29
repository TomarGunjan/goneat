package com.goneat.goneat.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.goneat.goneat.exceptions.UserServiceException;
import com.goneat.goneat.io.entity.FavouriteRestaurantEntity;
import com.goneat.goneat.io.entity.RestaurantEntity;
import com.goneat.goneat.io.entity.UserEntity;
import com.goneat.goneat.io.repositories.FavouriteRestaurantRepository;
import com.goneat.goneat.io.repositories.UserRepository;
import com.goneat.goneat.service.RestaurantService;
import com.goneat.goneat.service.UserService;
import com.goneat.goneat.shared.dto.UserDto;
import com.goneat.goneat.shared.utils.Utils;
import com.goneat.goneat.ui.mode.request.FavouriteRestaurantRequestModel;
import com.goneat.goneat.ui.mode.response.FavouriteRestaurantResponseModel;
import com.goneat.goneat.ui.mode.response.RestaurantDetails;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RestaurantService restaurantService;
	
	@Autowired
	FavouriteRestaurantRepository favRestRepo;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	Utils utils;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		
		if(userRepository.findByEmail(userDto.getEmail())!=null)
			throw new UserServiceException("User Already Exists");
		UserEntity entity = new UserEntity();
		BeanUtils.copyProperties(userDto, entity);
		String publicUserId="";
		while(true)
		{
			publicUserId = utils.getUserId(30);
			if(userRepository.findByUserId(publicUserId)==null) break;
		}
		entity.setUserId(publicUserId);
		entity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
		UserEntity returnEntity = userRepository.save(entity);
		UserDto returnDto = new UserDto();
		BeanUtils.copyProperties(returnEntity, returnDto);
		return returnDto;
	}



	@Override
	public UserDto findUserByUserId(String userId) {
		
		UserEntity entity = userRepository.findByUserId(userId);
		if(entity==null) throw new UsernameNotFoundException(userId);
		UserDto dto = new UserDto();
		BeanUtils.copyProperties(entity,dto);
		return dto;
	}
	
	public UserDto findUserByUsername(String userName)
	{
		UserEntity entity = userRepository.findByEmail(userName);
		if(entity==null) throw new UsernameNotFoundException(userName);
		UserDto dto = new UserDto();
		BeanUtils.copyProperties(entity,dto);
		return dto;	
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity=userRepository.findByEmail(email);
		if(userEntity==null) throw new UsernameNotFoundException(email);
		return new User(userEntity.getEmail(),userEntity.getEncryptedPassword(),new ArrayList());
	}

}
