package com.flightbookings.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.flightbookings.entities.FlightBookingDetails;
import java.lang.String;
@Repository
@Transactional
public interface FlightBookingDetailsRepo extends JpaRepository<FlightBookingDetails, String>{

	/*@Query("select a,b.journeyDate,b.onBoradingTime,b.DepartureTime,c.fromLocation,c.toLocation,"
			+ "d.AirLineName from FlightBookingDetails a,FlightSchduleMaster b,FlightsInventory c,AirLines d"
			+ "  where a.Booking_group_Id=?1 and a.ScheduleId=b.SchduleId and b.FlightId=c.FlightID and c.AirLineId=d.AirLineId ")
	public Object[] pnrData(String pnr);*/
	
	
	@Query("select a,b,c,d"
			+ "  from FlightBookingDetails a,FlightSchduleMaster b,FlightsInventory c,AirLines d,FlightBookingMaster e "
			+ "  where a.Booking_group_Id=?1 and a.Booking_Booking_Id=e.bookingId and e.schedule_scheduleId=b.SchduleId and b.FlightId=c.FlightID and c.AirLineId=d.AirLineId ")
	public Object pnrData(String pnr);
	
	
	@Query("select a from FlightBookingDetails a where a.Booking_Booking_Id = ?1 ")
	public List<FlightBookingDetails> findByBookingId(String bid);
	
	List<FlightBookingDetails> findByFlightId(String flightid);
}
