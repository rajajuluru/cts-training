package com.flightbookings.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class AirLines implements Serializable{

	@Id
	private String AirLineId;
	private String AirLineName;
	private String AirLineDescription;
	private Date date;
	private String ipAddress;

	@Override
	public String toString() {
		return "AirLines [AirLineId=" + AirLineId + ", AirLineName=" + AirLineName + ", AirLineDescription="
				+ AirLineDescription + ", date=" + date + ", ipAddress=" + ipAddress + ", LockStatus=" + LockStatus
				;
	}

	private Long LockStatus;
/*	@OneToMany(mappedBy = "airlineDetails")
	private List<FlightsInventory> inventories;

	public List<FlightsInventory> getInventories() {
		return inventories;
	}

	public void setInventories(List<FlightsInventory> inventories) {
		this.inventories = inventories;
	}*/

	public String getAirLineId() {
		return AirLineId;
	}

	public void setAirLineId(String airLineId) {
		AirLineId = airLineId;
	}

	public String getAirLineName() {
		return AirLineName;
	}

	public void setAirLineName(String airLineName) {
		AirLineName = airLineName;
	}

	public String getAirLineDescription() {
		return AirLineDescription;
	}

	public void setAirLineDescription(String airLineDescription) {
		AirLineDescription = airLineDescription;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Long getLockStatus() {
		return LockStatus;
	}

	public void setLockStatus(Long lockStatus) {
		LockStatus = lockStatus;
	}
}
