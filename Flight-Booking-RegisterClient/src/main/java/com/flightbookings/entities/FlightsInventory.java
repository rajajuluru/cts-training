package com.flightbookings.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
//@Table(name="flights_inventory")
public class FlightsInventory implements Serializable{

	
	@Id
	private String FlightID;
	private String fromLocation;
	private String toLocation;
	
	private Long SeatsCount;
	private String Remarks;
	private Long lockstatus;
	private Date date;
	private String ipaddress;
	private Long price;
	@Column(name="AirLineId")
	private String AirLineId;
	//mapping code
	@OneToOne
	@JoinColumn(name="AirLineId",referencedColumnName="AirLineId",insertable=false,updatable=false)
	private AirLines airlineDetails;
	public String getFlightID() {
		return FlightID;
	}
	public Long getLockstatus() {
		return lockstatus;
	}
	public void setLockstatus(Long lockstatus) {
		this.lockstatus = lockstatus;
	}
	@Override
	public String toString() {
		return "FlightsInventory [FlightID=" + FlightID + ", fromLocation=" + fromLocation + ", toLocation="
				+ toLocation + ", SeatsCount=" + SeatsCount + ", Remarks=" + Remarks + ", lockstatus=" + lockstatus
				+ ", date=" + date + ", ipaddress=" + ipaddress + ", price=" + price + ", AirLineId=" + AirLineId
				;
	}
	public void setFlightID(String flightID) {
		FlightID = flightID;
	}
	public String getAirLineId() {
		return AirLineId;
	}
	public void setAirLineId(String airLineId) {
		AirLineId = airLineId;
	}
	
	//mapping
	public AirLines getAirlineDetails() {
		return airlineDetails;
	}
	public void setAirlineDetails(AirLines airlineDetails) {
		this.airlineDetails = airlineDetails;
	}
	public String getFromLocation() {
		return fromLocation;
	}
	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}
	public String getToLocation() {
		return toLocation;
	}
	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}
	public Long getSeatsCount() {
		return SeatsCount;
	}
	public void setSeatsCount(Long seatsCount) {
		SeatsCount = seatsCount;
	}
	public String getRemarks() {
		return Remarks;
	}
	public void setRemarks(String remarks) {
		Remarks = remarks;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getIpaddress() {
		return ipaddress;
	}
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	
}
