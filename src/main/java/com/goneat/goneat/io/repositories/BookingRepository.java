package com.goneat.goneat.io.repositories;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.goneat.goneat.io.entity.BookingEntity;
import com.goneat.goneat.io.entity.UserEntity;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BookingRepository extends PagingAndSortingRepository<BookingEntity, Long> {
	
	@Query(value="Select * from booking b where b.start_date_Time between :startTime and :endTime and activation_status='active'",nativeQuery=true)
	public List<BookingEntity> findByTime(@Param("startTime")Timestamp startTime,@Param("endTime")Timestamp endTime);


	@Modifying
	@Transactional
	@Query(value="Update booking b set b.activation_status='inactive' where b.start_date_time<current_timestamp()",nativeQuery=true)
	public void markPastSlotsInactive();

	BookingEntity findBybookingId(String bookingId);
}
