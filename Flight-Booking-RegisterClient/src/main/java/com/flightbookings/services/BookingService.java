package com.flightbookings.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flightbookings.ResponseHelperClass;
import com.flightbookings.entities.FlightBookingDetails;
import com.flightbookings.entities.FlightBookingMaster;
import com.flightbookings.entities.FlightSchduleMaster;
import com.flightbookings.entities.FlightsInventory;
import com.flightbookings.helperClass.BookingHelperClass;
import com.flightbookings.helperClass.PassenderDetails;
import com.flightbookings.repositories.FlightBookingDetailsRepo;
import com.flightbookings.repositories.FlightBookingMasterRepository;
import com.flightbookings.repositories.FlightInventoryRepository;
import com.flightbookings.repositories.FlightScheduleMasterRepository;

@Service
public class BookingService {
	@Autowired
	private FlightBookingDetails flightBookingDetails;
	@Autowired
	private FlightBookingDetailsRepo flightBookingDetailsRepo;
	@Autowired
	private FlightScheduleMasterRepository FScheduleMasterRepository;
	@Autowired
	FlightBookingMasterRepository flightBookingMasterRepository;
	@Autowired
	BookingIdGenerationService IdGenerationService;
	@Autowired
	FlightBookingMaster flightBookingMaster;

	@Autowired
	FlightInventoryRepository flightInventoryRepository;

	Logger logger = LoggerFactory.getLogger(BookingService.class);

	@Transactional()
	public synchronized ResponseEntity<?> ValidateDataBeforeBooking(BookingHelperClass BhelperClass) {
		logger.info(BhelperClass.toString() + "BhelperClass");
		List<String> pnrnumber = new ArrayList();

		Set<String> hashSet = new HashSet();
		hashSet.add(BhelperClass.getScheduleflightId());
		List<FlightSchduleMaster> findBySchduleId = FScheduleMasterRepository
				.findByschduleId(BhelperClass.getScheduleflightId());
		final String flightId;

		if (findBySchduleId.size() > 0) {
			flightId = findBySchduleId.get(0).getFlightId();
			Optional<FlightsInventory> findByIdFlightInventory = flightInventoryRepository.findById(flightId);
			if (BhelperClass.getSeatnos() > 0) {

				if (BhelperClass.getDetailsOfPassenger().size() > 0) {
					boolean statusDetails = false;

					Set<PassenderDetails> detailsOfPassenger = BhelperClass.getDetailsOfPassenger();
					List<PassenderDetails> detailsOfPassengerList = new ArrayList(detailsOfPassenger);
					for (PassenderDetails details : detailsOfPassengerList) {
						System.out.println(details.getAge() + "details.getAge()");
						if (details.getAge() <= 0 || details.getGender().trim().isEmpty()
								|| details.getName().trim().isEmpty()) {
							statusDetails = true;
							return new ResponseEntity("Passender Details cannot be empty and age cannot be 0 or less",
									HttpStatus.BAD_REQUEST);
						}
					}

					String bookingMasterFlightIdGenerator = IdGenerationService.BookingMasterFlightIdGenerator();
					if (!statusDetails) {
						flightBookingMaster.setBookingDate(LocalDate.now());
						flightBookingMaster.setBookingfare(
								detailsOfPassengerList.size() * findByIdFlightInventory.get().getPrice().intValue());
						flightBookingMaster.setNoOfTickets(detailsOfPassengerList.size());
						flightBookingMaster.setBookingstatus(0);
						flightBookingMaster.setBookingId(bookingMasterFlightIdGenerator);
						flightBookingMaster.setUserid(BhelperClass.getUserid());
						flightBookingMaster.setSchedule_scheduleId(BhelperClass.getScheduleflightId());
						flightBookingMasterRepository.save(flightBookingMaster);
					}

					for (PassenderDetails details : detailsOfPassengerList) {

						if (!statusDetails)

						{

							// for(int i=0;i<BhelperClass.getSeatnos();i++)
							// {
							String pnrBookingMasterFlightIdGenerator = IdGenerationService
									.PNRBookingMasterFlightIdGenerator();

							flightBookingDetails.setBooking_Booking_Id(bookingMasterFlightIdGenerator);
							flightBookingDetails.setBooking_group_Id(pnrBookingMasterFlightIdGenerator);
							flightBookingDetails.setAge(details.getAge());

							flightBookingDetails.setP_gender(details.getGender());
							flightBookingDetails.setP_name(details.getName());
							flightBookingDetails.setTicketFare(findByIdFlightInventory.get().getPrice().intValue());
							flightBookingDetails.setTicketStatus(0);
							flightBookingDetails.setFlightId(flightId.trim());
							System.out.println("before save");
							System.out.println(flightId + "                   " + flightBookingDetails.getFlightId()
									+ "			flightBookingDetails.getFlightId()");
							flightBookingDetailsRepo.save(flightBookingDetails);

							pnrnumber.add(pnrBookingMasterFlightIdGenerator);

							// }

						}
					}

					return new ResponseEntity(pnrnumber, HttpStatus.OK);
				} else {

					return new ResponseEntity("Please give the Passender Details", HttpStatus.BAD_REQUEST);
				}

			} else {

				return new ResponseEntity("Seats should be greater than zero", HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity("Flight not found", HttpStatus.NOT_FOUND);
		}

	}

	@Transactional
	public synchronized ResponseEntity<?> pnrStatus(String pnrnumber) {
		logger.info("pnr" + pnrnumber);

		boolean existsById = flightBookingDetailsRepo.existsById(pnrnumber.trim().toUpperCase());
		if (existsById) {
			// Object pnrData =
			// flightBookingDetailsRepo.pnrData(pnrnumber.trim().toUpperCase());
			// System.out.println(pnrData+"pnrData object[] data");
			Optional<FlightBookingDetails> findById = flightBookingDetailsRepo.findById(pnrnumber.trim().toUpperCase());
			return new ResponseEntity(findById.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity(pnrnumber + "   number not found", HttpStatus.NOT_FOUND);
		}

	}

	// status 1 is cancelled by user
	@Transactional
	public synchronized ResponseEntity<?> pnrCancel(String pnrnumber) {
		logger.info("pnr" + pnrnumber);

		boolean existsById = flightBookingDetailsRepo.existsById(pnrnumber.trim().toUpperCase());
		if (existsById) {

			Optional<FlightBookingDetails> findById = flightBookingDetailsRepo.findById(pnrnumber.trim().toUpperCase());
			FlightBookingDetails flightBookingDetails2 = findById.get();
			flightBookingDetails2.setTicketStatus(1);
			flightBookingDetailsRepo.save(flightBookingDetails2);
			return new ResponseEntity("tickect Cancelled Successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity(pnrnumber + "  number not found", HttpStatus.NOT_FOUND);
		}

	}

	// status 1 is cancelled by user
	@Transactional
	public synchronized ResponseEntity<?> TicketsCancelByBookingId(String pnrnumber) {
		logger.info("booking id" + pnrnumber);
		Optional<FlightBookingMaster> findById = flightBookingMasterRepository.findById(pnrnumber.trim().toUpperCase());

		if (findById.isPresent()) {
			FlightBookingMaster flightBookingMaster2 = findById.get();
			flightBookingMaster2.setBookingstatus(1);// 1 cancelled
			flightBookingMasterRepository.save(flightBookingMaster2);

			List<FlightBookingDetails> findByBookingId = flightBookingDetailsRepo
					.findByBookingId(pnrnumber.trim().toUpperCase());
			if (findByBookingId.size() > 0) {
				for (FlightBookingDetails details : findByBookingId) {
					details.setTicketStatus(1);
					flightBookingDetailsRepo.save(details);

				}

				return new ResponseEntity("tickect Cancelled Successfully", HttpStatus.OK);
			} else {
				return new ResponseEntity(pnrnumber + "  number not found", HttpStatus.NOT_FOUND);
			}

		} else {
			return new ResponseEntity(pnrnumber + "  number not found", HttpStatus.NOT_FOUND);
		}

	}

	@Transactional
	public List<FlightBookingMaster> getUserBookingHistory(String userID) {

		List<FlightBookingMaster> findByUserid = flightBookingMasterRepository.findByUserid(userID);

		return findByUserid;

	}

	@Transactional
	public List<FlightBookingDetails> getBookingIdDetails(String bid) {
	

		List<FlightBookingDetails> findByBookingId = flightBookingDetailsRepo.findByBookingId(bid);
		return findByBookingId;

	}
}
