package com.technoelevate.ess.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.technoelevate.ess.dto.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
	// void deleteByContactNo(Long contactNo);
	 Person findByContactNo(Long contactNo);

	// Person findByEmail(String email);

	Person findByPersonId(Integer id);

	void deleteByPersonId(Integer id);
}
