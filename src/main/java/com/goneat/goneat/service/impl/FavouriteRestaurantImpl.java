package com.goneat.goneat.service.impl;

import com.goneat.goneat.exceptions.ErrorMessages;
import com.goneat.goneat.exceptions.RestaurantsException;
import com.goneat.goneat.exceptions.UserServiceException;
import com.goneat.goneat.io.entity.FavouriteRestaurantEntity;
import com.goneat.goneat.io.entity.RestaurantEntity;
import com.goneat.goneat.io.entity.UserEntity;
import com.goneat.goneat.io.repositories.FavouriteRestaurantRepository;
import com.goneat.goneat.io.repositories.UserRepository;
import com.goneat.goneat.service.FavouriteRestaurant;
import com.goneat.goneat.service.RestaurantService;
import com.goneat.goneat.ui.mode.request.FavouriteRestaurantRequestModel;
import com.goneat.goneat.ui.mode.response.FavouriteRestaurantResponseModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class FavouriteRestaurantImpl implements FavouriteRestaurant {


    @Autowired
    UserRepository userRepository;

    @Autowired
    FavouriteRestaurantRepository favRestRepo;

    @Autowired
    RestaurantService restaurantService;

    @Override
    public FavouriteRestaurantEntity favouriteRestaurant(String userId, FavouriteRestaurantRequestModel model) {
        if(userRepository.findByUserId(userId)==null)
            throw new UserServiceException("User Not found");
        FavouriteRestaurantResponseModel responseModel = new FavouriteRestaurantResponseModel();
        UserEntity entity = userRepository.findByUserId(userId);

        Long restaurantId =model.getRestaurantIds();
        RestaurantEntity restDetail = restaurantService.getRestaurantFromRestaurantId(restaurantId);
        if(restDetail==null) throw new RestaurantsException(ErrorMessages.RESTAURANTNOTFOUND.getMessage());
        List<FavouriteRestaurantEntity> restaurantIds = favRestRepo.findFavouriteRestaurants(userId);
        List restIds = new ArrayList();
        restaurantIds.stream().map(x->x.getRestaurantId()).forEach(y->restIds.add(y));
        if(restIds.contains(restaurantId)) throw new RestaurantsException("restaurant is already added as favourite");
        FavouriteRestaurantEntity favEntity = new FavouriteRestaurantEntity();
        favEntity.setRestaurantId(restaurantId);
        favEntity.setUserId(userId);
        FavouriteRestaurantEntity favEntityresponse = favRestRepo.save(favEntity);


        return favEntityresponse;
    }
}
