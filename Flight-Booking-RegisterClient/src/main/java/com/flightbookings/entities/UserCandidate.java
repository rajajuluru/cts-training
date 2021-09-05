package com.flightbookings.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="users_table")
public class UserCandidate {


	public UserCandidate() {
		super();
	}
	
	//@GeneratedValue(strategy=GenerationType.AUTO)
	//private Long Userid;
	@NotNull(message="FirstName cannot be empty")
	@NotBlank(message="FirstName cannot be empty")
	@Column(nullable=false)
	private String FirstName;
	@NotNull(message="password cannot be empty")
	@NotBlank(message="password cannot be empty")
	@Size(min = 2, max = 14,message="size should be min 2 chars and max 14 chars")
	@Column(nullable=false)
	private String password;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@NotNull(message="Last Name cannot be empty")
	@NotBlank(message="Last Name cannot be empty")
	@Column(nullable=false)
	private String LastName;
	@Column(unique=true)
	//@NotNull(message="email cannot be empty")
	//@NotBlank(message="email cannot be empty")
	@Email(message="enter a valid email id")
	@Id
	private String Emailid;

	@NotNull(message="Address cannot be empty")
	@NotBlank(message="Address cannot be empty")
	@Column(nullable=false)
	private String Address;
	@NotNull(message="mobile number cannot be empty")
	@NotBlank(message="mobile number cannot be empty")
	@Size(min=10,max=10,message="phone number must be 10 digits please dont include country code")
	@Column(nullable=false)
	private String mobilenumber;
	@Column(nullable=false)
	private String roletype;
/*	public Long getUserid() {
		return Userid;
	}
	public void setUserid(Long userid) {
		Userid = userid;
	}*/
	public String getRoletype() {
		return roletype;
	}
	public void setRoletype(String roletype) {
		this.roletype = roletype;
	}

	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getEmailid() {
		return Emailid;
	}
	public void setEmailid(String emailid) {
		Emailid = emailid;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getMobilenumber() {
		return mobilenumber;
	}
	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Address == null) ? 0 : Address.hashCode());
		result = prime * result + ((Emailid == null) ? 0 : Emailid.hashCode());
		result = prime * result + ((FirstName == null) ? 0 : FirstName.hashCode());
		result = prime * result + ((LastName == null) ? 0 : LastName.hashCode());
		result = prime * result + ((mobilenumber == null) ? 0 : mobilenumber.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((roletype == null) ? 0 : roletype.hashCode());
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
		UserCandidate other = (UserCandidate) obj;
		if (Address == null) {
			if (other.Address != null)
				return false;
		} else if (!Address.equals(other.Address))
			return false;
		if (Emailid == null) {
			if (other.Emailid != null)
				return false;
		} else if (!Emailid.equals(other.Emailid))
			return false;
		if (FirstName == null) {
			if (other.FirstName != null)
				return false;
		} else if (!FirstName.equals(other.FirstName))
			return false;
		if (LastName == null) {
			if (other.LastName != null)
				return false;
		} else if (!LastName.equals(other.LastName))
			return false;
		if (mobilenumber == null) {
			if (other.mobilenumber != null)
				return false;
		} else if (!mobilenumber.equals(other.mobilenumber))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (roletype == null) {
			if (other.roletype != null)
				return false;
		} else if (!roletype.equals(other.roletype))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "UserCandidate [FirstName=" + FirstName + ", password=" + password + ", LastName=" + LastName
				+ ", Emailid=" + Emailid + ", Address=" + Address + ", mobilenumber=" + mobilenumber + ", roletype="
				+ roletype + "]";
	}
	


}
