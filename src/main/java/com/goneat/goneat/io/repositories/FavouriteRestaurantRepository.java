package com.goneat.goneat.io.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.goneat.goneat.io.entity.FavouriteRestaurantEntity;

@Repository
public interface FavouriteRestaurantRepository extends CrudRepository<FavouriteRestaurantEntity,Long> {

}
