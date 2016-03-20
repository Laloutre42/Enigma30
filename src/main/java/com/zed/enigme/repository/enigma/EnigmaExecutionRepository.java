package com.zed.enigme.repository.enigma;

import com.zed.enigme.domain.enigma.EnigmaExecution;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Spring Data JPA repository for the Enigma execution entity.
 */
public interface EnigmaExecutionRepository extends JpaRepository<EnigmaExecution, Long> {

    public List<EnigmaExecution> findByUser_IdAndIsOkTrue(Long id);

    public List<EnigmaExecution> findByUser_Id(Long id);

}
