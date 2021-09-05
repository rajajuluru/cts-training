package com.flightbookings.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flightbookings.entities.AirLines;

@Repository
public interface AirLinesRepository extends JpaRepository<AirLines, String>{

}
