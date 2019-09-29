package com.goneat.goneat.service;

import java.util.List;

import com.goneat.goneat.io.entity.RestaurantEntity;
import com.goneat.goneat.ui.mode.request.RestaurantCreationModel;
import com.goneat.goneat.ui.mode.response.Restaurant;
import com.goneat.goneat.ui.mode.response.RestaurantDetails;

public interface RestaurantService {

	RestaurantEntity getRestaurantFromRestaurantId(Long restId);
	List<Restaurant> searchRestaurantByName(Long cityId,String keyword, int page, int limit);
	Restaurant createRestaurant(RestaurantCreationModel model);
}
