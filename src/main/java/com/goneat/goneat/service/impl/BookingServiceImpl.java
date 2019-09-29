package com.goneat.goneat.service.impl;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.goneat.goneat.exceptions.BookingException;
import com.goneat.goneat.exceptions.ErrorMessages;
import com.goneat.goneat.exceptions.RestaurantsException;
import com.goneat.goneat.exceptions.UserServiceException;
import com.goneat.goneat.io.entity.BookingEntity;
import com.goneat.goneat.io.repositories.BookingRepository;
import com.goneat.goneat.service.BookingService;
import com.goneat.goneat.service.RestaurantService;
import com.goneat.goneat.service.UserService;
import com.goneat.goneat.shared.utils.LocalDateTimeAttributeConverter;
import com.goneat.goneat.shared.utils.Utils;
import com.goneat.goneat.ui.mode.request.BookingRequestModel;
import com.goneat.goneat.ui.mode.response.BookedDetails;
import com.goneat.goneat.ui.mode.response.BookingResponseModel;


@Service
public class BookingServiceImpl implements BookingService {
	
	
	@Value("${total.guest.capacity}")
    private int guestCapacity;
	
	@Autowired
	RestaurantService restaurantService;
	
	@Autowired
	BookingRepository bookingRepository;
	
	@Autowired
	LocalDateTimeAttributeConverter localDateTimeAttributeConverter;
	
	@Autowired
	UserService userService;
	
	@Autowired
	Utils utility;

	public BookedDetails booking(Long restId, BookingRequestModel bookingRequest)
	{
		BookedDetails response = new BookedDetails();
		bookingRepository.markPastSlotsInactive();
		boolean flag=false;
		flag=checkRestaurantExists(restId);
		if(!flag) throw new RestaurantsException(ErrorMessages.RESTAURANTNOTFOUND.getMessage());
		flag=checkUserExists(bookingRequest.getUserId());
		if(!flag) throw new UserServiceException(ErrorMessages.USERNOTDFOUND.getMessage());
		flag=checkAvailability(bookingRequest);
		BookingEntity entity = new BookingEntity();
		entity.setRestId(restId);
		entity.setNumOfGuests(bookingRequest.getNumberOfGuests());
		String date = bookingRequest.getDate();
		String time = bookingRequest.getTime();
		String dateTime = date+time;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-ddHH:mm");
        LocalDateTime formatDateTime = LocalDateTime.parse(dateTime, formatter);
		Timestamp stamp = localDateTimeAttributeConverter.convertToDatabaseColumn(formatDateTime);
        entity.setStartDateTime(stamp);
		entity.setUserId(bookingRequest.getUserId());
		String bookingId= "";
		while(true)
		{
			bookingId=utility.getUserId(8);
			if(bookingRepository.findBybookingId(bookingId)==null) break;
		}
		entity.setBookingId(bookingId);
		entity.setActivationStatus("active");
		BookingEntity responseEntity = bookingRepository.save(entity);
		response.setBookingId(responseEntity.getBookingId());
		response.setNumberOdGuests(responseEntity.getNumOfGuests());
		response.setStartTime(localDateTimeAttributeConverter.convertToEntityAttribute(responseEntity.getStartDateTime()).toString());
		return response;
	}
	
	private boolean checkAvailability(BookingRequestModel bookingRequest) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-ddHH:mm");
        LocalDateTime formatDateTime = LocalDateTime.parse(bookingRequest.getDate()+bookingRequest.getTime(), formatter);
        LocalDateTime presentTime = LocalDateTime.now();
        long sec = presentTime.until(formatDateTime, ChronoUnit.SECONDS);
        if(sec<0) throw new BookingException(ErrorMessages.NOTFUTUREBOOKINGDATE.getMessage());
        if(bookingRequest.getNumberOfGuests()<1) throw new BookingException(ErrorMessages.INVALIDGUESTCAPACITY.getMessage());
        List<BookingEntity> currentBookings = bookingRepository.findByTime(localDateTimeAttributeConverter.convertToDatabaseColumn(formatDateTime), localDateTimeAttributeConverter.convertToDatabaseColumn(formatDateTime.plusHours(1)));
		int guests =bookingRequest.getNumberOfGuests();
		for(BookingEntity entity:currentBookings)
		{
			guests=guests+entity.getNumOfGuests();
			if(entity.getUserId().equals(bookingRequest.getUserId())) throw new RestaurantsException("User Already has a booking in provided slot");
		}
		if(guests>guestCapacity) throw new BookingException(ErrorMessages.OUTOFCAPACITY.getMessage());
        return true;
	}

	private boolean checkRestaurantExists(Long restId)
	{
		return (restaurantService.getRestaurantFromRestaurantId(restId)!=null);
	}
	
	private boolean checkUserExists(String userId)
	{
		return (userService.findUserByUserId(userId)!=null);
	}
}
