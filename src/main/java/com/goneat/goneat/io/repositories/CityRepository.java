package com.goneat.goneat.io.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.goneat.goneat.io.entity.CityEntity;

import java.util.List;

@Repository
public interface CityRepository extends PagingAndSortingRepository<CityEntity,Long>{
		CityEntity findByCityId(Long id);
		CityEntity findByCityName(String cityName);
		List<CityEntity> findAll();
}
