package com.flightbookings.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.flightbookings.entities.FlightSchduleMaster;

@Repository
public interface FlightScheduleMasterRepository extends JpaRepository<FlightSchduleMaster, String>{
	
	
	@Query("select a from FlightSchduleMaster a where a.FlightId in (?1) and a.scheduleStatus=0")
	List<FlightSchduleMaster> findByFlightId(Set<String> fid);
	
	@Query(" select a from FlightSchduleMaster a where a.SchduleId in (?1)")
	List<FlightSchduleMaster> findByschduleId(String sid);
	
	


}
