package com.flightbookings.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.flightbookings.entities.UserCandidate;
@Repository
public interface UserCandidateRepository extends JpaRepository<UserCandidate, String>{

	//UserCandidate findByEmailid(String emailid);
	@Query("select a from UserCandidate a where a.Emailid=?1" )
	List<UserCandidate> findByEmailid(String emailid);

}
