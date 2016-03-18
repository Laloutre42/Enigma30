package com.zed.enigme.repository.enigma;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.zed.enigme.domain.enigma.Enigma;

/**
 * Spring Data JPA repository for the Enigma entity.
 */
public interface EnigmaRepository extends JpaRepository<Enigma, Long> {

	public Optional<Enigma> findOneByNumber(int number);

	@Query("SELECT MAX(e.number) FROM Enigma e")
    public Long getMaxNumber();

}
