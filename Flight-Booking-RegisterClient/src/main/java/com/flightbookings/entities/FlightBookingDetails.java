package com.flightbookings.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

@Component
@Entity
public class FlightBookingDetails implements Serializable{

	@Id
	@Column(name="pnr_number")
	private String Booking_group_Id;
	

	public String getBooking_group_Id() {
		return Booking_group_Id;
	}
	public void setBooking_group_Id(String booking_group_Id) {
		Booking_group_Id = booking_group_Id;
	}
	@Column(nullable=false)
	private String Booking_Booking_Id;
	public String getFlightId() {
		return flightId;
	}
	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}
	@Column(nullable=false)
	private String p_name;
	@Column(nullable=false)
	private Integer Age;
	@Column(nullable=false)
	private String p_gender;
	@Column(nullable=false)
	private Integer ticketFare;
	@Column(nullable=false)
	private Integer ticketStatus;
	@Column(nullable=false)
	private String flightId; 
	
	@Override
	public String toString() {
		return "FlightBookingDetails [Booking_group_Id=" + Booking_group_Id + ", Booking_Booking_Id="
				+ Booking_Booking_Id + ", p_name=" + p_name + ", Age=" + Age + ", p_gender=" + p_gender
				+ ", ticketFare=" + ticketFare + ", ticketStatus=" + ticketStatus + ", FlightId=" + flightId + "]";
	}
	@ManyToOne
	@JoinColumn(name="Booking_Booking_Id",referencedColumnName="bookingId",insertable=false,updatable=false)
	private FlightBookingMaster bookingmasterDetails;
	
	
	public FlightBookingMaster getBookingmasterDetails() {
		return bookingmasterDetails;
	}
	public void setBookingmasterDetails(FlightBookingMaster bookingmasterDetails) {
		this.bookingmasterDetails = bookingmasterDetails;
	}
	
	public String getBooking_Booking_Id() {
		return Booking_Booking_Id;
	}
	public void setBooking_Booking_Id(String booking_Booking_Id) {
		Booking_Booking_Id = booking_Booking_Id;
	}
	
	public Integer getTicketFare() {
		return ticketFare;
	}
	
	public void setTicketFare(Integer ticketFare) {
		this.ticketFare = ticketFare;
	}
	public Integer getTicketStatus() {
		return ticketStatus;
	}
	public void setTicketStatus(Integer ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public Integer getAge() {
		return Age;
	}
	public void setAge(Integer age) {
		Age = age;
	}
	public String getP_gender() {
		return p_gender;
	}
	public void setP_gender(String p_gender) {
		this.p_gender = p_gender;
	}
	

}
