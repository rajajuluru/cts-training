package com.flightbookings.services;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightbookings.entities.IdGeneratorTable;
import com.flightbookings.repositories.IdGeneratorRepository;
@Service
public class BookingIdGenerationService implements Serializable{
	private final Integer BOOKING_MASTER_ID_GENERATOR = new Integer(4);
	private final Integer PNR_ID_GENERATOR = new Integer(5);
	
	@Autowired
	private IdGeneratorTable GeneratorTable;
	@Autowired
	private IdGeneratorRepository idRepo;
	
	//booking master id generator
		public synchronized String BookingMasterFlightIdGenerator() {
			ReentrantLock lock = new ReentrantLock(true);

			lock.lock();

			if (idRepo.existsById(BOOKING_MASTER_ID_GENERATOR)) {

			} else {
				GeneratorTable.setCurrentId(000000);
				GeneratorTable.setDate(new Date());
				GeneratorTable.setDescription("Id Generated for BOOKING_MASTER_ID_GENERATOR");
				GeneratorTable.setId(BOOKING_MASTER_ID_GENERATOR);
				GeneratorTable.setPrefix("BMASTER");
				idRepo.save(GeneratorTable);

			}

			IdGeneratorTable idGeneratorTable = idRepo.findById(BOOKING_MASTER_ID_GENERATOR).get();
			idGeneratorTable.setCurrentId(idGeneratorTable.getCurrentId() + 1);
			idRepo.save(idGeneratorTable);
			lock.unlock();
			return idGeneratorTable.getPrefix() + idGeneratorTable.getCurrentId();

		}
	

		//booking master id generator
				public synchronized String PNRBookingMasterFlightIdGenerator() {
					ReentrantLock lock = new ReentrantLock(true);

					lock.lock();

					if (idRepo.existsById(5)) {

					} else {
						GeneratorTable.setCurrentId(000000);
						GeneratorTable.setDate(new Date());
						GeneratorTable.setDescription("Id Generated for PNR number");
						GeneratorTable.setId(PNR_ID_GENERATOR);
						GeneratorTable.setPrefix("PNR");
						idRepo.save(GeneratorTable);

					}

					IdGeneratorTable idGeneratorTable = idRepo.findById(PNR_ID_GENERATOR).get();
					idGeneratorTable.setCurrentId(idGeneratorTable.getCurrentId() + 1);
					idRepo.save(idGeneratorTable);
					lock.unlock();
					return idGeneratorTable.getPrefix() + idGeneratorTable.getCurrentId();

				}
}
