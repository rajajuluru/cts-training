package com.flightbookings.services;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.flightbookings.entities.IdGeneratorTable;
import com.flightbookings.repositories.IdGeneratorRepository;

@RequestScope
@Component
public class IdGeneratorService implements Serializable {

	@Autowired
	private IdGeneratorTable GeneratorTable;
	@Autowired
	private IdGeneratorRepository idRepo;
	private final Integer AIR_LINE_ID = new Integer(1);
	private final Integer FLIGHT_ID = new Integer(2);
	private final Integer SCHUD_FLIGHT_ID = new Integer(3);
	
	public synchronized String AirLineIdGenerator() {
		ReentrantLock lock = new ReentrantLock(true);

		lock.lock();

		if (idRepo.existsById(AIR_LINE_ID)) {

		} else {
			GeneratorTable.setCurrentId(000000);
			GeneratorTable.setDate(new Date());
			GeneratorTable.setDescription("Id Generated for AirLines");
			GeneratorTable.setId(AIR_LINE_ID);
			GeneratorTable.setPrefix("AIRLINE");
			idRepo.save(GeneratorTable);

		}

		IdGeneratorTable idGeneratorTable = idRepo.findById(AIR_LINE_ID).get();
		idGeneratorTable.setCurrentId(idGeneratorTable.getCurrentId() + 1);
		idRepo.save(idGeneratorTable);
		lock.unlock();
		return idGeneratorTable.getPrefix() + idGeneratorTable.getCurrentId();

	}
	
	
	
	public synchronized String FlightIdGenerator() {
		ReentrantLock lock = new ReentrantLock(true);

		lock.lock();

		if (idRepo.existsById(FLIGHT_ID)) {

		} else {
			GeneratorTable.setCurrentId(000000);
			GeneratorTable.setDate(new Date());
			GeneratorTable.setDescription("Id Generated for Flights");
			GeneratorTable.setId(FLIGHT_ID);
			GeneratorTable.setPrefix("FLIGHT");
			idRepo.save(GeneratorTable);

		}

		IdGeneratorTable idGeneratorTable = idRepo.findById(FLIGHT_ID).get();
		idGeneratorTable.setCurrentId(idGeneratorTable.getCurrentId() + 1);
		idRepo.save(idGeneratorTable);
		lock.unlock();
		return idGeneratorTable.getPrefix() + idGeneratorTable.getCurrentId();

	}
	
	
	
	//schedule id generator
	public synchronized String ScheduleFlightIdGenerator() {
		ReentrantLock lock = new ReentrantLock(true);

		lock.lock();

		if (idRepo.existsById(SCHUD_FLIGHT_ID)) {

		} else {
			GeneratorTable.setCurrentId(000000);
			GeneratorTable.setDate(new Date());
			GeneratorTable.setDescription("Id Generated for Schdule_Flights_Id");
			GeneratorTable.setId(SCHUD_FLIGHT_ID);
			GeneratorTable.setPrefix("SCHFLIGHT");
			idRepo.save(GeneratorTable);

		}

		IdGeneratorTable idGeneratorTable = idRepo.findById(SCHUD_FLIGHT_ID).get();
		idGeneratorTable.setCurrentId(idGeneratorTable.getCurrentId() + 1);
		idRepo.save(idGeneratorTable);
		lock.unlock();
		return idGeneratorTable.getPrefix() + idGeneratorTable.getCurrentId();

	}
}
