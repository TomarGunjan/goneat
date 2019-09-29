package com.goneat.goneat.service;

import com.goneat.goneat.ui.mode.request.BookingRequestModel;
import com.goneat.goneat.ui.mode.response.BookedDetails;

public interface BookingService {
	
	BookedDetails booking(Long restId, BookingRequestModel bookingRequest);

}
