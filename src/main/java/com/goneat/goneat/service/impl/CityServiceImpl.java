package com.goneat.goneat.service.impl;

import com.goneat.goneat.exceptions.CityException;
import com.goneat.goneat.exceptions.ErrorMessages;
import com.goneat.goneat.io.entity.CityEntity;
import com.goneat.goneat.io.repositories.CityRepository;
import com.goneat.goneat.service.CityService;
import com.goneat.goneat.ui.mode.response.CityResponseModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    CityRepository cityrepository;

    @Override
    public CityResponseModel createcity(String cityname) {
        CityResponseModel model = new CityResponseModel();
        CityEntity  entity = new CityEntity();
        if(cityrepository.findByCityName(cityname)!=null)
        {
            throw new CityException(ErrorMessages.CITYALREADYEXISTS.getMessage());
        }
        entity.setCityName(cityname);
        CityEntity responseEntity = cityrepository.save(entity);
        BeanUtils.copyProperties(responseEntity,model);
        return model;
    }

    @Override
    public CityResponseModel getCityByCityId(Long cityId)
    {
        CityEntity  entity =cityrepository.findByCityId(cityId);
        if(entity==null) throw new CityException(ErrorMessages.CITYDOESNOTEXIST.getMessage());
        CityResponseModel model = new CityResponseModel();
        BeanUtils.copyProperties(entity,model);
        return model;
    }

    @Override
    public List<CityResponseModel> getAllCity()
    {
        List<CityEntity> citiesEntity = cityrepository.findAll();
        List<CityResponseModel> response = new ArrayList<>();
        for(CityEntity entity:citiesEntity)
        {
            CityResponseModel model = new CityResponseModel();
            BeanUtils.copyProperties(entity,model);
            response.add(model);
        }
        return response;
    }
}
