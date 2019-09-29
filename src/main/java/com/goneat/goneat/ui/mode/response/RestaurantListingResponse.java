package com.goneat.goneat.ui.mode.response;

import java.util.List;

public class RestaurantListingResponse {

    private OperationStatusResponseModel status;
    private List<Restaurant> restaurants;

    public OperationStatusResponseModel getStatus() {
        return status;
    }

    public void setStatus(OperationStatusResponseModel status) {
        this.status = status;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }
}
