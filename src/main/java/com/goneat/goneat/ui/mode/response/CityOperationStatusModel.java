package com.goneat.goneat.ui.mode.response;

public class CityOperationStatusModel {
    private OperationStatusResponseModel status;
    private CityResponseModel city;

    public OperationStatusResponseModel getStatus() {
        return status;
    }

    public void setStatus(OperationStatusResponseModel status) {
        this.status = status;
    }

    public CityResponseModel getCity() {
        return city;
    }

    public void setCity(CityResponseModel city) {
        this.city = city;
    }
}
