package com.goneat.goneat.service;

import com.goneat.goneat.io.entity.FavouriteRestaurantEntity;
import com.goneat.goneat.ui.mode.request.FavouriteRestaurantRequestModel;

public interface FavouriteRestaurant {
    FavouriteRestaurantEntity favouriteRestaurant(String userId, FavouriteRestaurantRequestModel model);
}
