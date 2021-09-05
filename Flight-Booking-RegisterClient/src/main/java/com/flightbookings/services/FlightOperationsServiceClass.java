package com.flightbookings.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flightbookings.FlightSearchHelperClass;
import com.flightbookings.ResponseHelperClass;
import com.flightbookings.entities.AirLines;
import com.flightbookings.entities.DiscountCouponMasterTable;
import com.flightbookings.entities.FlightBookingDetails;
import com.flightbookings.entities.FlightBookingMaster;
import com.flightbookings.entities.FlightSchduleMaster;
import com.flightbookings.entities.FlightsInventory;
import com.flightbookings.repositories.AirLinesRepository;
import com.flightbookings.repositories.DiscountCouponMasterTableRepository;
import com.flightbookings.repositories.FlightBookingDetailsRepo;
import com.flightbookings.repositories.FlightBookingMasterRepository;
import com.flightbookings.repositories.FlightInventoryRepository;
import com.flightbookings.repositories.FlightScheduleMasterRepository;
import com.flightbookings.repositories.UserCandidateRepository;

@Service
public class FlightOperationsServiceClass implements Serializable {

	@Autowired
	DiscountCouponMasterTableRepository CouponMasterTableRepository;
	@Autowired
	private UserCandidateRepository userrepository;

	@Autowired
	IdGeneratorService IdService;

	@Autowired
	private AirLinesRepository AirRepository;
	@Autowired
	FlightBookingDetailsRepo flightBookingDetailsRepo;
	@Autowired
	private FlightScheduleMasterRepository ScheduleMasterRepository;

	@Autowired
	private FlightInventoryRepository flightInventoryRepository;

	public List<FlightSchduleMaster> searchFlightService(FlightSearchHelperClass searchHelperClass) {
		List<FlightSchduleMaster> finalResponseDate = new ArrayList();
		List<FlightsInventory> flights = flightInventoryRepository.getFlights(searchHelperClass.getSource() + '%',
				searchHelperClass.getSource(), searchHelperClass.getDestination() + '%',
				searchHelperClass.getDestination());
		System.out.println(flights.size() + "size");

		Set<String> flightids = new HashSet();
		if (flights.size() > 0) {
			flights.stream().forEach(s -> {
				flightids.add(s.getFlightID().trim());
			});
		}

		List<FlightSchduleMaster> findByFlightId = ScheduleMasterRepository.findByFlightId(flightids);
		System.out.println(findByFlightId.size() + "findByFlightId.size");
		for (FlightSchduleMaster i : findByFlightId) {

			if (i.getJourneyDate().compareTo(searchHelperClass.getJourney()) == 0)// if
																					// same
			{
				System.out.println(i.getFlightId() + "is matching so adding to response list");
				finalResponseDate.add(i);
			}

		}

		return finalResponseDate;
	}

	@Transactional
	public FlightsInventory AddFlightService(FlightsInventory flightInventory) {

		String FlightIdGenerator = IdService.FlightIdGenerator();
		flightInventory.setFlightID(FlightIdGenerator);

		// System.out.println(flightInventory+"flight inventory");
		FlightsInventory save = flightInventoryRepository.save(flightInventory);

		return save;
	}

	@Transactional
	public FlightsInventory updateFlightService(FlightsInventory flightInventory) {

		FlightsInventory save = flightInventoryRepository.save(flightInventory);

		return save;
	}

	@Transactional
	public void deleteFlightService(String fid) {
		List<FlightBookingDetails> findByFlightId = flightBookingDetailsRepo.findByFlightId(fid);
		if (findByFlightId.size() == 0) {

			flightInventoryRepository.deleteById(fid);
		} else {

		}

	}

	public List<FlightsInventory> findAllFlights() {
		return flightInventoryRepository.findAll();

	}

	@Transactional
	public FlightSchduleMaster AddScheduleService(FlightSchduleMaster flightSchduleMaster) {
		String SchFlightIdGenerator = IdService.ScheduleFlightIdGenerator();
		flightSchduleMaster.setSchduleId(SchFlightIdGenerator);
		flightSchduleMaster.setScheduleStatus(0);
		FlightSchduleMaster save = ScheduleMasterRepository.save(flightSchduleMaster);

		return save;
	}

	@Transactional
	public AirLines AddAirLinesService(AirLines airLines) {
		String airLineIdGenerator = IdService.AirLineIdGenerator();
		airLines.setAirLineId(airLineIdGenerator);
		airLines.setDate(new Date());
		System.out.println(airLines + "Air lined data");

		AirLines save = AirRepository.save(airLines);

		return save;
	}

	@Transactional
	public AirLines updateAirLinesService(AirLines airLines) {

		airLines.setDate(new Date());
		System.out.println(airLines + "Air lined data gng to update");
		AirLines save = AirRepository.save(airLines);

		return save;
	}

	@Transactional
	public void deleteAirLinesService(String airId) {

		AirRepository.deleteById(airId);

	}

	@Autowired
	FlightBookingMasterRepository flightBookingMasterRepository;

	@Transactional
	public ResponseHelperClass deleteScheduleService(String schid) {
		ResponseHelperClass res = new ResponseHelperClass();

		Optional<FlightSchduleMaster> findById = ScheduleMasterRepository.findById(schid);
		findById.get().setScheduleStatus(1);// cancel by admins
		ScheduleMasterRepository.save(findById.get());
		String booking_booking_id = "";
		List<FlightBookingMaster> findByschedule_scheduleId = flightBookingMasterRepository
				.findByschedule_scheduleId(findById.get().getSchduleId());
		if (findByschedule_scheduleId.size() > 0) {
			booking_booking_id = findByschedule_scheduleId.get(0).getBookingId();

			findByschedule_scheduleId.stream().forEach(s -> {

				s.setBookingstatus(2);// cancelled by admin
				flightBookingMasterRepository.save(s);// saving again

			});
		}
		List<FlightBookingDetails> bookingDetailsList = flightBookingDetailsRepo.findByBookingId(booking_booking_id);
		if (bookingDetailsList.size() > 0) {
			bookingDetailsList.stream().forEach(s -> {
				s.setTicketStatus(2);// canceled by admin
				flightBookingDetailsRepo.save(s);

			});
		}
		res.setData("schedule for this flight has been cancelled by admin");
		res.setStatus(true);
		return res;

	}

	// coupon master
	@Transactional
	public ResponseHelperClass addcouponMaster(DiscountCouponMasterTable discountCouponMasterTable) {
		ResponseHelperClass res = new ResponseHelperClass();
		discountCouponMasterTable.setCouponcode(discountCouponMasterTable.getCouponcode().toUpperCase());
		DiscountCouponMasterTable save = CouponMasterTableRepository.save(discountCouponMasterTable);
		res.setStatus(true);
		res.setData(save);
		return res;

	}
	
	@Transactional
	public ResponseHelperClass getcouponMaster(String disid) {
		ResponseHelperClass res = new ResponseHelperClass();
		DiscountCouponMasterTable save = CouponMasterTableRepository.findByCouponcode(disid.trim().toUpperCase());
		res.setStatus(true);
		res.setData(save);
		return res;

	}

	
}
