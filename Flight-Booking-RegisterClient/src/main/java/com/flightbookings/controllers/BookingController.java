package com.flightbookings.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightbookings.FlightSearchHelperClass;
import com.flightbookings.ResponseHelperClass;
import com.flightbookings.entities.FlightBookingDetails;
import com.flightbookings.entities.FlightBookingMaster;
import com.flightbookings.entities.FlightSchduleMaster;
import com.flightbookings.helperClass.BookingHelperClass;
import com.flightbookings.services.BookingService;
import com.flightbookings.services.FlightOperationsServiceClass;

@RestController
@RequestMapping("/booking")
@CrossOrigin("*")
public class BookingController implements Serializable {

	@Autowired
	BookingService bookingService;
	@Autowired
	private FlightOperationsServiceClass flightOperationsServiceClass;
	@Autowired
	private LoadBalancerClient loadBalancerClient;

	private String getEurekaUrlforJWT() {
		ServiceInstance serviceInstance = loadBalancerClient.choose("JWT-CLIENT");
		return serviceInstance.getUri().toString();
	}

	@PostMapping("doBooking")
	public Object DoBooking(@RequestBody BookingHelperClass bookingHelperClass, HttpServletRequest request) {
		System.out.println(bookingHelperClass + "bookingHelperClass");

		System.out.println(bookingHelperClass.getDetailsOfPassenger().size() + "size of passenger details");
		ResponseEntity<?> validateDataBeforeBooking = bookingService.ValidateDataBeforeBooking(bookingHelperClass);
		int statusCodeValue = validateDataBeforeBooking.getStatusCodeValue();
		ResponseHelperClass res = new ResponseHelperClass();
		if (statusCodeValue == 200) {
			res.setStatus(true);
			res.setData(validateDataBeforeBooking.getBody());

		} else {
			res.setStatus(false);
			res.setData(validateDataBeforeBooking.getBody());
		}
		return res;
	}

	@GetMapping("pnr/{pnrnumber}")
	public Object pnrDetails(@PathVariable(name = "pnrnumber") String pnrnumber) {
		System.out.println(pnrnumber + "pnrnumber");
		ResponseEntity<?> pnrStatus = bookingService.pnrStatus(pnrnumber.trim().toUpperCase());

		return pnrStatus;
	}

	@GetMapping("pnrcancel/{pnrnumber}")
	public Object pnrcancel(@PathVariable(name = "pnrnumber") String pnrnumber) {
		System.out.println(pnrnumber + "pnrnumber");
		ResponseEntity<?> pnrStatus = bookingService.pnrCancel(pnrnumber.trim().toUpperCase());

		return pnrStatus;
	}

	@GetMapping("ticketsCancelByBookingId/{bid}")
	public Object CancelAlltickets(@PathVariable(name = "bid") String pnrnumber) {
		System.out.println(pnrnumber + "booking id");
		ResponseEntity<?> pnrStatus = bookingService.TicketsCancelByBookingId(pnrnumber.trim().toUpperCase());

		return pnrStatus;
	}

	@PostMapping(value = "/SearchFlight")
	public Object SearchFlight(@RequestBody FlightSearchHelperClass searchHelperClass) {
		List<FlightSchduleMaster> finalResponseDate = new ArrayList();
		ResponseHelperClass helper = new ResponseHelperClass();
		System.out.println(searchHelperClass + "flight search");

		finalResponseDate = flightOperationsServiceClass.searchFlightService(searchHelperClass);
		helper.setStatus(true);
		helper.setData(finalResponseDate);

		return helper;

	}

	@GetMapping(value = "/getHistory/{userid}")
	public ResponseHelperClass SearchFlight(@PathVariable(name = "userid") String userid) {
		System.out.println("userid" + userid);

		List<FlightBookingMaster> userBookingHistory = bookingService.getUserBookingHistory(userid);
		ResponseHelperClass helper = new ResponseHelperClass();
		helper.setStatus(true);
		helper.setData(userBookingHistory);

		return helper;

	}

	@GetMapping(value = "/getBookingIdDetails/{bid}")
	public ResponseHelperClass getBookingIdDetails(@PathVariable(name = "bid") String bid) {
		System.out.println("bid" + bid);

		List<FlightBookingDetails> bookingIdDetails = bookingService.getBookingIdDetails(bid);
		ResponseHelperClass helper = new ResponseHelperClass();
		helper.setStatus(true);
		helper.setData(bookingIdDetails);

		return helper;

	}

}
