package com.flightbookings.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.flightbookings.entities.FlightBookingMaster;

@Repository
public interface FlightBookingMasterRepository extends JpaRepository<FlightBookingMaster, String> {

	@Query("select a from FlightBookingMaster a where a.schedule_scheduleId in (?1)")
	List<FlightBookingMaster> findByschedule_scheduleId(String sid);
	
	List<FlightBookingMaster>findByUserid(String userid);
}
