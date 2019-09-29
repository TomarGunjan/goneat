package com.goneat.goneat.io.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.goneat.goneat.io.entity.RestaurantEntity;
import com.goneat.goneat.ui.mode.response.RestaurantDetails;

@Repository
public interface RestaurantRepository extends PagingAndSortingRepository<RestaurantEntity,Long> {
	
	List<RestaurantEntity> findAllBycityId(Long id);

	RestaurantEntity findByrestaurantId(Long restId);
	
	@Query(value="Select * from restaurants r where r.restaurant_Name like %:keyword% and r.city_Id=:cityId",nativeQuery=true)
	Page<RestaurantEntity> findByRestaurantName(@Param("cityId")String cityId, @Param("keyword")String keyword, Pageable pageRequest);
	
}
