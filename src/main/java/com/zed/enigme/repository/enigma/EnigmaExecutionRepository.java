package com.zed.enigme.repository.enigma;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zed.enigme.domain.enigma.EnigmaExecution;

/**
 * Spring Data JPA repository for the Enigma execution entity.
 */
public interface EnigmaExecutionRepository extends JpaRepository<EnigmaExecution, Long> {

	public List<EnigmaExecution> findByUser_IdAndIsOkTrue(Long id);

}
