package com.flightbookings.helperClass;

import java.io.Serializable;
import java.util.Set;

public class BookingHelperClass implements Serializable {

	private String scheduleflightId;

	private Integer seatnos;
	private Set<PassenderDetails> detailsOfPassenger;

	public Integer getSeatnos() {
		return seatnos;
	}

	public void setSeatnos(Integer seatnos) {
		this.seatnos = seatnos;
	}
	private String flightId;

	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	private String userid;


	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	

	@Override
	public String toString() {
		return "BookingHelperClass [scheduleflightId=" + scheduleflightId + ", seatnos=" + seatnos
				+ ", detailsOfPassenger=" + detailsOfPassenger + ", flightId=" + flightId + ", userid=" + userid + "]";
	}

	public Set<PassenderDetails> getDetailsOfPassenger() {
		return detailsOfPassenger;
	}

	public void setDetailsOfPassenger(Set<PassenderDetails> detailsOfPassenger) {
		this.detailsOfPassenger = detailsOfPassenger;
	}

	public String getScheduleflightId() {
		return scheduleflightId;
	}

	public void setScheduleflightId(String scheduleflightId) {
		this.scheduleflightId = scheduleflightId;
	}

}
