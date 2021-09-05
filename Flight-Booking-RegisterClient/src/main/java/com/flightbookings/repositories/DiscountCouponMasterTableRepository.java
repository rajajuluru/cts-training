package com.flightbookings.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flightbookings.entities.DiscountCouponMasterTable;

public interface DiscountCouponMasterTableRepository extends JpaRepository<DiscountCouponMasterTable, Integer>{

	DiscountCouponMasterTable findByCouponcode(String code);
	
	
}
