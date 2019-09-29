package com.goneat.goneat.ui.mode.response;

public class RestaurantCreationResponseModel {

    private OperationStatusResponseModel status;
    private RestaurantDetails restaurants;

    public OperationStatusResponseModel getStatus() {
        return status;
    }

    public void setStatus(OperationStatusResponseModel status) {
        this.status = status;
    }

    public RestaurantDetails getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(RestaurantDetails restaurants) {
        this.restaurants = restaurants;
    }
}
