package com.goneat.goneat.io.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.goneat.goneat.io.entity.FavouriteRestaurantEntity;

import java.util.List;

@Repository
public interface FavouriteRestaurantRepository extends CrudRepository<FavouriteRestaurantEntity,Long> {

    @Query(value="Select * from favourite_restaurant r where r.user_id=:userId",nativeQuery =true)
    List<FavouriteRestaurantEntity> findFavouriteRestaurants(@Param("userId")String userId);
}
