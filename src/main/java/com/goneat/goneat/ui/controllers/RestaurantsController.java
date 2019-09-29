package com.goneat.goneat.ui.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.goneat.goneat.ui.mode.response.BookedDetails;
import com.goneat.goneat.ui.mode.response.BookingResponseModel;
import com.goneat.goneat.ui.mode.response.OperationStatusResponseModel;
import com.goneat.goneat.ui.mode.response.OperationStatusValues;
import com.goneat.goneat.ui.mode.response.Restaurant;
import com.goneat.goneat.ui.mode.response.RestaurantDetails;

@RestController
@RequestMapping(value="restaurants")
public class RestaurantsController {
	
	@Autowired
	RestaurantService restaurantService;
	
	@Autowired
	BookingService bookingService;
	
	@GetMapping
	public RestaurantDetails getRestaurants(@RequestParam(value="cityId")Long cityId)
	{
		RestaurantDetails details = restaurantService.getRestaurantFromCity(cityId);
		return details;
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
	
//	@GetMapping
//	public List<Restaurant> searchRestaurants(@RequestParam(value="cityId")Long cityId,@RequestParam(value="keyword")String keyword)
//	{
//		List<Restaurant> details= restaurantService.searchRestaurantByName(cityId,keyword);
//		return details;
//	}

}
