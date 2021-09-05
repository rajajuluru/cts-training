package com.flightbookings.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Component;

@Component
@Entity
public class FlightBookingMaster implements Serializable{

	@Id
	@Column(nullable=false)
	private String bookingId;
	@Column(nullable=false)
	private Integer noOfTickets;
	@Column(nullable=false)
	private Integer bookingfare;
	@Column(nullable=false)
	private LocalDate bookingDate;
	@Column(nullable=false)
	private Integer bookingstatus;
	@Column(nullable=false)
	private String schedule_scheduleId;
	@Column(nullable=false)
	private String userid;
	/*@OneToMany(mappedBy="bookingmasterDetails")
	
	private Set<FlightBookingDetails> flightBookingDetails;*/

	public String getSchedule_scheduleId() {
		return schedule_scheduleId;
	}
	@Override
	public String toString() {
		return "FlightBookingMaster [bookingId=" + bookingId + ", noOfTickets=" + noOfTickets + ", bookingfare="
				+ bookingfare + ", bookingDate=" + bookingDate + ", bookingstatus=" + bookingstatus
				+ ", schedule_scheduleId=" + schedule_scheduleId + ", userid=" + userid + "]";
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public void setSchedule_scheduleId(String schedule_scheduleId) {
		this.schedule_scheduleId = schedule_scheduleId;
	}
	public String getBookingId() {
		return bookingId;
	}
	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}
	public Integer getNoOfTickets() {
		return noOfTickets;
	}
	public void setNoOfTickets(Integer noOfTickets) {
		this.noOfTickets = noOfTickets;
	}
	public Integer getBookingfare() {
		return bookingfare;
	}
	public void setBookingfare(Integer bookingfare) {
		this.bookingfare = bookingfare;
	}
	public LocalDate getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}
	public Integer getBookingstatus() {
		return bookingstatus;
	}
	public void setBookingstatus(Integer bookingstatus) {
		this.bookingstatus = bookingstatus;
	}
	
	
}
