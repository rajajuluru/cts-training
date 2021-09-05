package com.flightbookings.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class FlightSchduleMaster implements Serializable{

	@Id
	private String SchduleId;
	@Column(nullable=false)
	private String FlightId;
	@Column(nullable=false)
	private LocalDate journeyDate;
/*	private LocalDateTime onBoradingTime;
	private LocalDateTime DepartureTime;*/
	@Column(nullable=false)
	private LocalTime onBoradingTime;
	@Column(nullable=false)
	private LocalTime DepartureTime;
	@Column(nullable=false)
	private LocalDate bookingStartDate;
	@Column(nullable=false)
	private LocalDate bookingEndDate;
	

	@Column(nullable=false)
	private Integer scheduleStatus;
	public Integer getScheduleStatus() {
		return scheduleStatus;
	}

	public void setScheduleStatus(Integer scheduleStatus) {
		this.scheduleStatus = scheduleStatus;
	}
	@OneToOne
	@JoinColumn(name="FlightId",referencedColumnName="FlightID",insertable=false,updatable=false)
	private FlightsInventory FlightsInventoryDetails;
	public String getSchduleId() {
		return SchduleId;
	}
	
	public FlightsInventory getFlightsInventoryDetails() {
		return FlightsInventoryDetails;
	}

	public void setFlightsInventoryDetails(FlightsInventory flightsInventoryDetails) {
		FlightsInventoryDetails = flightsInventoryDetails;
	}

	public LocalDate getBookingStartDate() {
		return bookingStartDate;
	}

	public void setBookingStartDate(LocalDate bookingStartDate) {
		this.bookingStartDate = bookingStartDate;
	}

	public LocalDate getBookingEndDate() {
		return bookingEndDate;
	}

	public void setBookingEndDate(LocalDate bookingEndDate) {
		this.bookingEndDate = bookingEndDate;
	}

	public void setSchduleId(String schduleId) {
		SchduleId = schduleId;
	}
	public String getFlightId() {
		return FlightId;
	}
	public void setFlightId(String flightId) {
		FlightId = flightId;
	}
	public LocalDate getJourneyDate() {
		return journeyDate;
	}
	public void setJourneyDate(LocalDate journeyDate) {
		this.journeyDate = journeyDate;
	}
	public LocalTime getOnBoradingTime() {
		return onBoradingTime;
	}
	public void setOnBoradingTime(LocalTime onBoradingTime) {
		this.onBoradingTime = onBoradingTime;
	}
	public LocalTime getDepartureTime() {
		return DepartureTime;
	}
	public void setDepartureTime(LocalTime departureTime) {
		DepartureTime = departureTime;
	}

	@Override
	public String toString() {
		return "FlightSchduleMaster [SchduleId=" + SchduleId + ", FlightId=" + FlightId + ", journeyDate=" + journeyDate
				+ ", onBoradingTime=" + onBoradingTime + ", DepartureTime=" + DepartureTime + ", bookingStartDate="
				+ bookingStartDate + ", bookingEndDate=" + bookingEndDate + ", scheduleStatus=" + scheduleStatus
				+ ", FlightsInventoryDetails=" + FlightsInventoryDetails + "]";
	}
	
}
