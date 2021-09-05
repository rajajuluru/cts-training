package com.flightbookings.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.flightbookings.entities.FlightsInventory;

public interface FlightInventoryRepository extends JpaRepository<FlightsInventory, String>{

	@Query("select a from FlightsInventory a where (a.fromLocation like ?1 or  a.fromLocation =?2) "
			+ " and  (a.toLocation like ?3 or a.toLocation =?4)")
	public List<FlightsInventory> getFlights(String s1,String s2,String s3,String s4);
}
