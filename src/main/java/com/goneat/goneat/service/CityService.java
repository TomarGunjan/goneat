package com.goneat.goneat.service;

import com.goneat.goneat.ui.mode.response.CityResponseModel;

import java.util.List;

public interface CityService {
    CityResponseModel createcity(String cityname);
    CityResponseModel getCityByCityId(Long cityId);
    List<CityResponseModel> getAllCity();
}
