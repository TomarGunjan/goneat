package com.goneat.goneat.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.goneat.goneat.exceptions.CityException;
import com.goneat.goneat.exceptions.ErrorMessages;
import com.goneat.goneat.ui.mode.request.RestaurantCreationModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	
	

	@Override
	public RestaurantEntity getRestaurantFromRestaurantId(Long restId) {
		return restaurantRepository.findByrestaurantId(restId);
	}


	@Override
	public List<Restaurant> searchRestaurantByName(Long cityId, String keyword, int page, int limit) {

		if(page>0) page=page-1;
		Pageable pageRequest = PageRequest.of(page, limit);
		RestaurantDetails response = new RestaurantDetails();
		Page<RestaurantEntity> pages =  restaurantRepository.findByRestaurantName(Long.toString(cityId),keyword,pageRequest);
		List<RestaurantEntity> restaurants = pages.getContent();
		List<Restaurant> list = new ArrayList();
		for(RestaurantEntity restEnt:restaurants)
		{
			Restaurant rest = new Restaurant();
			BeanUtils.copyProperties(restEnt, rest);
			list.add(rest);
		}
		return list;
	}

	@Override
	public Restaurant createRestaurant(RestaurantCreationModel model)
	{
		List<RestaurantEntity> restaurants = restaurantRepository.findAllBycityId(model.getCityId());
		List<String> restName = new ArrayList<>();
		restaurants.stream().map(x->x.getRestaurantName()).forEach(y->restName.add(y));
		if(restName.contains(model.getRestaurantName()))
		{
			throw new RestaurantsException("Restaurant already exists in city with same name");
		}
		if(cityRepository.findByCityId(model.getCityId())==null)
		{
			throw new CityException(ErrorMessages.CITYDOESNOTEXIST.getMessage());
		}
		RestaurantEntity entity = new RestaurantEntity();
		entity.setCityId(model.getCityId());
		entity.setRestaurantName(model.getRestaurantName());
		RestaurantEntity responseEntity = restaurantRepository.save(entity);
		Restaurant restaurant = new Restaurant();
		BeanUtils.copyProperties(responseEntity,restaurant);
		return restaurant;
	}

}
