package com.flightbookings.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flightbookings.entities.IdGeneratorTable;

@Repository
public interface IdGeneratorRepository extends JpaRepository<IdGeneratorTable, Integer>{

}
