package com.goneat.goneat.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goneat.goneat.exceptions.RestaurantsException;
import com.goneat.goneat.io.entity.CityEntity;
import com.goneat.goneat.io.entity.RestaurantEntity;
import com.goneat.goneat.io.repositories.CityRepository;
import com.goneat.goneat.io.repositories.RestaurantRepository;
import com.goneat.goneat.service.RestaurantService;
import com.goneat.goneat.ui.mode.response.Restaurant;
import com.goneat.goneat.ui.mode.response.RestaurantDetails;

@Service
public class RestaurantServiceImpl implements RestaurantService{
	
	@Autowired
	CityRepository cityRepository;
	
	@Autowired
	RestaurantRepository restaurantRepository;
	
	
	public RestaurantDetails getRestaurantFromCity(Long cityId)
	{
		if(cityRepository.findByCityId(cityId)==null)
			throw new RestaurantsException("No City Found with given Id");
		CityEntity entity=cityRepository.findByCityId(cityId);
		String cityName = entity.getCityName();
		List<RestaurantEntity> restEntities = restaurantRepository.findAllBycityId(cityId);
		RestaurantDetails response = new RestaurantDetails();
		response.setCityId(cityId);
		response.setCityName(cityName);
		List<Restaurant> list = new ArrayList();
		for(RestaurantEntity restEnt:restEntities)
		{
			Restaurant rest = new Restaurant();
			BeanUtils.copyProperties(restEnt, rest);
			list.add(rest);
		}
		response.setRestaurants(list);
		return response;
	}


	@Override
	public RestaurantEntity getRestaurantFromRestaurantId(Long restId) {
		return restaurantRepository.findByrestaurantId(restId);
	}


	@Override
	public List<Restaurant> searchRestaurantByName(Long cityId, String keyword) {
		RestaurantDetails response = new RestaurantDetails();
		List<RestaurantEntity> restaurants =  restaurantRepository.findByRestaurantName(Long.toString(cityId),keyword);
		List<Restaurant> list = new ArrayList();
		for(RestaurantEntity restEnt:restaurants)
		{
			Restaurant rest = new Restaurant();
			BeanUtils.copyProperties(restEnt, rest);
			list.add(rest);
		}
		return list;
	}

}
