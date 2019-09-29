package com.goneat.goneat.ui.controllers;

import java.util.Arrays;
import java.util.List;

import com.goneat.goneat.io.entity.FavouriteRestaurantEntity;
import com.goneat.goneat.io.entity.RestaurantEntity;
import com.goneat.goneat.service.FavouriteRestaurant;
import com.goneat.goneat.ui.mode.request.FavouriteRestaurantRequestModel;
import com.goneat.goneat.ui.mode.request.RestaurantCreationModel;
import com.goneat.goneat.ui.mode.response.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.goneat.goneat.service.BookingService;
import com.goneat.goneat.service.RestaurantService;
import com.goneat.goneat.ui.mode.request.BookingRequestModel;

@RestController
@RequestMapping(value="restaurants")
public class RestaurantsController {
	
	@Autowired
	RestaurantService restaurantService;
	
	@Autowired
	BookingService bookingService;

	@Autowired
	FavouriteRestaurant favouriteRestaurant;


	@PostMapping
	public RestaurantCreationResponseModel createRestaurant(@RequestBody RestaurantCreationModel request)
	{
		RestaurantCreationResponseModel response = new RestaurantCreationResponseModel();
		RestaurantDetails details = new RestaurantDetails();
		OperationStatusResponseModel statusmodel = new OperationStatusResponseModel();
		try
		{
			details.setCityId(request.getCityId());
			Restaurant model = restaurantService.createRestaurant(request);
			details.setRestaurants(Arrays.asList(model));
			statusmodel.setStatus(OperationStatusValues.SUCCESS.name());
			response.setRestaurants(details);
		}
		catch(RuntimeException ex)
		{
			statusmodel.setStatus(OperationStatusValues.ERROR.name());
			statusmodel.setMessage(ex.getMessage());
		}
		response.setStatus(statusmodel);
		return response;
	}

	@GetMapping(path="/{restId}")
	public RestaurantListingResponse getByRestaurantId(@PathVariable Long restId)
	{
		RestaurantListingResponse responseModel = new RestaurantListingResponse();
		OperationStatusResponseModel status = new OperationStatusResponseModel();
		try
		{
			RestaurantEntity model = restaurantService.getRestaurantFromRestaurantId(restId);
			Restaurant restaurant = new Restaurant();
			BeanUtils.copyProperties(model,restaurant);
			responseModel.setRestaurants(Arrays.asList(restaurant));
			status.setStatus(OperationStatusValues.SUCCESS.name());
		}
		catch(RuntimeException exception)
		{
			status.setStatus(OperationStatusValues.ERROR.name());
			status.setMessage(exception.getMessage());
		}
		responseModel.setStatus(status);
		return responseModel;
	}

	
	@PostMapping(value="/booking")
	public BookingResponseModel bookTable(@RequestParam(value="restId")Long restId,@RequestBody BookingRequestModel request)
	{	
		BookingResponseModel response = new BookingResponseModel();
		OperationStatusResponseModel status = new OperationStatusResponseModel();
		try
		{
			BookedDetails details = bookingService.booking(restId, request);
			response.setDetails(details);
			status.setStatus(OperationStatusValues.SUCCESS.name());
		}
		catch(RuntimeException ex)
		{
			ex.printStackTrace();
			String message = ex.getMessage();
			status.setStatus(OperationStatusValues.ERROR.name());
			status.setMessage(message);
		}
		response.setStatus(status);
		
		return response;
	}
	
	@GetMapping
	public RestaurantListingResponse searchRestaurants(@RequestParam(value="cityId")Long cityId, @RequestParam(value="keyword")String keyword,
													   @RequestParam(value="page", defaultValue="1")int page,
													   @RequestParam(value="limit", defaultValue="25")int limit)
	{
		RestaurantListingResponse response = new RestaurantListingResponse();
		OperationStatusResponseModel model = new OperationStatusResponseModel();
		try
		{
			model.setStatus(OperationStatusValues.SUCCESS.name());
			List<Restaurant> details= restaurantService.searchRestaurantByName(cityId,keyword,page,limit);
			response.setRestaurants(details);
		}
		catch(RuntimeException ex)
		{
			model.setStatus(OperationStatusValues.ERROR.name());
			model.setMessage(ex.getMessage());
		}
		response.setStatus(model);
		return response;
	}

	@PostMapping("/favourite/userId/{userId}")
	public FavouriteRestaurantResponseModel setFavouriteRestaurant(@PathVariable String userId, @RequestBody FavouriteRestaurantRequestModel details)
	{
		FavouriteRestaurantResponseModel response = new FavouriteRestaurantResponseModel();
		OperationStatusResponseModel model = new OperationStatusResponseModel();
		try
		{
			FavouriteRestaurantEntity entity =  favouriteRestaurant.favouriteRestaurant(userId,details);
			model.setStatus(OperationStatusValues.SUCCESS.name());
			model.setMessage("Restaurant with id "+entity.getRestaurantId()+" was marked favourite");
		}
		catch(RuntimeException ex)
		{
			model.setStatus(OperationStatusValues.ERROR.name());
			model.setMessage(ex.getMessage());
		}
		response.setStatus(model);
		return response;
	}

}
