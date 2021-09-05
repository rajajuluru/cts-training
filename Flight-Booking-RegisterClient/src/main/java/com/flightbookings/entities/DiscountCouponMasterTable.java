package com.flightbookings.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DiscountCouponMasterTable implements Serializable {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Integer Did;
	@Column(nullable=false,unique=true)
	private String couponcode;
	@Column(nullable=false,unique=true)
	private Integer couponDiscount;
	@Column(nullable=false,unique=true)
	private Integer cstatus;
	public Integer getDid() {
		return Did;
	}
	public void setDid(Integer did) {
		Did = did;
	}
	public String getCouponcode() {
		return couponcode;
	}
	public void setCouponcode(String couponcode) {
		this.couponcode = couponcode;
	}
	public Integer getCouponDiscount() {
		return couponDiscount;
	}
	public void setCouponDiscount(Integer couponDiscount) {
		this.couponDiscount = couponDiscount;
	}
	public Integer getCstatus() {
		return cstatus;
	}
	public void setCstatus(Integer cstatus) {
		this.cstatus = cstatus;
	}
	@Override
	public String toString() {
		return "DiscountCouponMasterTable [Did=" + Did + ", couponcode=" + couponcode + ", couponDiscount="
				+ couponDiscount + ", cstatus=" + cstatus + "]";
	}
	
	
}
