package com.flightbookings;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class FlightSearchHelperClass implements Serializable{
	
	private String source;
	private String Destination;
	private LocalDate journey;
	private Integer noOfTickets;
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return Destination;
	}
	public void setDestination(String destination) {
		Destination = destination;
	}
	public LocalDate getJourney() {
		return journey;
	}
	public void setJourney(LocalDate journey) {
		this.journey = journey;
	}
	public Integer getNoOfTickets() {
		return noOfTickets;
	}
	public void setNoOfTickets(Integer noOfTickets) {
		this.noOfTickets = noOfTickets;
	}
	@Override
	public String toString() {
		return "FlightSearchHelperClass [source=" + source + ", Destination=" + Destination + ", journey=" + journey
				+ ", noOfTickets=" + noOfTickets + "]";
	}

	

}
