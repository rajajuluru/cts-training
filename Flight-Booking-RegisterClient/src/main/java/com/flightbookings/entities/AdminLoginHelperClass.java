package com.flightbookings.entities;

import org.springframework.stereotype.Component;

@Component
public class AdminLoginHelperClass {
	
	public AdminLoginHelperClass() {
		super();
	}
	public String getAdminID() {
		return adminID;
	}
	public void setAdminID(String adminID) {
		this.adminID = adminID;
	}
	public String getPassword() {
		return password;
	}
	@Override
	public String toString() {
		return "AdminLoginHelperClass [adminID=" + adminID + ", password=" + password + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adminID == null) ? 0 : adminID.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AdminLoginHelperClass other = (AdminLoginHelperClass) obj;
		if (adminID == null) {
			if (other.adminID != null)
				return false;
		} else if (!adminID.equals(other.adminID))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
	public AdminLoginHelperClass(String adminID, String password) {
		super();
		this.adminID = adminID;
		this.password = password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private String adminID;
	private String password;

}
