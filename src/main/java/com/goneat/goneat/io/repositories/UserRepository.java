package com.goneat.goneat.io.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.goneat.goneat.io.entity.UserEntity;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
	
	UserEntity findByEmail(String email);
	UserEntity findByUserId(String userId);

}
