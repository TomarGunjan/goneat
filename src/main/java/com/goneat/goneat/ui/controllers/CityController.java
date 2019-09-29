package com.goneat.goneat.ui.controllers;


import com.goneat.goneat.service.CityService;
import com.goneat.goneat.ui.mode.response.CityOperationStatusModel;
import com.goneat.goneat.ui.mode.response.CityResponseModel;
import com.goneat.goneat.ui.mode.response.OperationStatusResponseModel;
import com.goneat.goneat.ui.mode.response.OperationStatusValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="city")
public class CityController {

    @Autowired
    CityService cityService;

    @PostMapping
    public CityOperationStatusModel createCity(@RequestBody String cityname)
    {
        CityOperationStatusModel status = new CityOperationStatusModel();
        OperationStatusResponseModel statusmodel = new OperationStatusResponseModel();
        try
        {
            CityResponseModel model = cityService.createcity(cityname);
            statusmodel.setStatus(OperationStatusValues.SUCCESS.name());
            status.setCity(model);

        }
        catch(RuntimeException ex)
        {
            statusmodel.setStatus(OperationStatusValues.ERROR.name());
            statusmodel.setMessage(ex.getMessage());
        }
        return status;
    }

    @GetMapping(path="/{cityId}")
    public CityOperationStatusModel getCityByCityId(@PathVariable Long cityId)
    {
        CityOperationStatusModel status = new CityOperationStatusModel();
        OperationStatusResponseModel statusmodel = new OperationStatusResponseModel();
        try
        {
            CityResponseModel model = cityService.getCityByCityId(cityId);
            statusmodel.setStatus(OperationStatusValues.SUCCESS.name());
            status.setCity(model);
        }
        catch(RuntimeException ex)
        {
            statusmodel.setStatus(OperationStatusValues.ERROR.name());
            statusmodel.setMessage(ex.getMessage());
        }
        status.setStatus(statusmodel);
        return status;
    }

    @GetMapping
    public List<CityResponseModel> getAllcities()
    {
        return cityService.getAllCity();

    }
}
