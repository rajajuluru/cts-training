package com.flightbookings.controllers;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightbookings.ResponseHelperClass;
import com.flightbookings.entities.AirLines;
import com.flightbookings.entities.DiscountCouponMasterTable;
import com.flightbookings.entities.FlightSchduleMaster;
import com.flightbookings.entities.FlightsInventory;
import com.flightbookings.services.FlightOperationsServiceClass;
import com.flightbookings.services.IdGeneratorService;

@RestController
@RequestMapping("/Admin")
public class AdminController {
	@Autowired
	private FlightOperationsServiceClass flightOperationsServiceClass;

	@Autowired
	IdGeneratorService IdService;

	@Transactional
	@PostMapping("/AddAirlines")
	public ResponseHelperClass AddAirlines(@RequestBody AirLines airLines) {
		ResponseHelperClass helper = new ResponseHelperClass();
		AirLines addAirLinesService = flightOperationsServiceClass.AddAirLinesService(airLines);
		helper.setStatus(true);
		helper.setData(addAirLinesService);
		return helper;

	}

	@Transactional
	@PostMapping("/updateAirlines/{airid}")
	public ResponseHelperClass updateAirlines(@RequestBody AirLines airLines,
			@PathVariable(name = "airid") String airid) {
		ResponseHelperClass helper = new ResponseHelperClass();
		airLines.setAirLineId(airid);
		AirLines addAirLinesService = flightOperationsServiceClass.updateAirLinesService(airLines);
		helper.setStatus(true);
		helper.setData(addAirLinesService);
		return helper;

	}

	@Transactional
	@GetMapping("/deleteAirlines/{airid}")
	public void DeleteAirlines(@PathVariable(name = "airid") String airid) {
		flightOperationsServiceClass.deleteAirLinesService(airid);
	}

	@Transactional
	@PostMapping("/AddFlights")
	public ResponseHelperClass AddFlights(@RequestBody FlightsInventory flightInventory)
			throws SQLIntegrityConstraintViolationException {
		System.out.println("incoming request data" + flightInventory);
		ResponseHelperClass helper = new ResponseHelperClass();
		FlightsInventory addFlightService = flightOperationsServiceClass.AddFlightService(flightInventory);
		helper.setStatus(true);
		helper.setData(addFlightService);
		return helper;

	}

	@Transactional
	@PostMapping("updateFlights/{fid}")
	public ResponseHelperClass updateFlights(@RequestBody FlightsInventory flightInventory,
			@PathVariable(name = "fid") String fid) {
		System.out.println("incoming request data" + flightInventory);
		ResponseHelperClass helper = new ResponseHelperClass();
		flightInventory.setFlightID(fid);
		FlightsInventory addFlightService = flightOperationsServiceClass.updateFlightService(flightInventory);
		helper.setStatus(true);
		helper.setData(addFlightService);
		return helper;

	}

	@Transactional
	@PostMapping("deleteFlights/{fid}")
	public void deleteFlights(@PathVariable(name = "fid") String fid) {

		flightOperationsServiceClass.deleteFlightService(fid);

	}

	@PostMapping("findAllFlights")
	public ResponseHelperClass findAllFlights() {
		ResponseHelperClass helper = new ResponseHelperClass();
		helper.setStatus(true);
		helper.setData(flightOperationsServiceClass.findAllFlights());
		return helper;

	}

	// schdule the flight
	@PostMapping("/SchduleFlights")
	public ResponseHelperClass SchduleFlights(@RequestBody FlightSchduleMaster flightSchduleMaster) {
		System.out.println(flightSchduleMaster + "flightSchduleMaster");
		FlightSchduleMaster addScheduleService = flightOperationsServiceClass.AddScheduleService(flightSchduleMaster);
		ResponseHelperClass helper = new ResponseHelperClass();
		helper.setStatus(true);
		helper.setData(addScheduleService);
		return helper;

	}

	@GetMapping("/CancelSchduleFlight/{sid}")
	public ResponseHelperClass deleteSchduleFlights(@PathVariable(name = "sid") String sid) {
		System.out.println("schedule id for cancelling" + sid);
		return flightOperationsServiceClass.deleteScheduleService(sid);

	}

	@PostMapping("/AddDiscountCoupon")
	public ResponseHelperClass AddDiscountCoupon(@RequestBody DiscountCouponMasterTable couponMasterTable) {
		couponMasterTable.setCstatus(0);
		System.out.println(couponMasterTable + "couponMasterTable");
		return flightOperationsServiceClass.addcouponMaster(couponMasterTable);

	}
	
	
	@PostMapping("/getDiscount/{did}")
	public ResponseHelperClass getDiscountCoupon(@PathVariable(name="did")String discountCode) {

		return flightOperationsServiceClass.getcouponMaster(discountCode);

	}

}
