package com.zed.enigme.service.enigma;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zed.enigme.domain.User;
import com.zed.enigme.domain.enigma.Enigma;
import com.zed.enigme.domain.enigma.EnigmaExecution;
import com.zed.enigme.enumeration.EnigmaExecutionResult;
import com.zed.enigme.repository.UserRepository;
import com.zed.enigme.repository.enigma.EnigmaExecutionRepository;
import com.zed.enigme.repository.enigma.EnigmaRepository;
import com.zed.enigme.security.SecurityUtils;

/**
 * Service class for managing enigmas.
 */
@Service
@Transactional
public class EnigmaService {

	@Inject
	private EnigmaExecutionRepository enigmaExecutionRepository;

	@Inject
	private EnigmaRepository enigmaRepository;

	@Inject
	private UserRepository userRepository;

	private final Logger log = LoggerFactory.getLogger(EnigmaService.class);

	@Transactional(readOnly = true)
	public Enigma getCurrentEnigmaForUser() {

		log.debug("[getCurrentEnigmaForUser] Get current enigma for user login {}", SecurityUtils.getCurrentUser().getUsername());

		Optional<User> user = userRepository.findOneByLogin(SecurityUtils.getCurrentUser().getUsername());

		// No user has been found
		if (!user.isPresent()) {
			log.error("[getCurrentEnigmaForUser] No user has been found");
			return null;
		}

		List<EnigmaExecution> enigmaExecution = enigmaExecutionRepository.findByUser_IdAndIsOkTrue(user.get().getId());

		// enigmaExecution has been found
		if (enigmaExecution != null && !enigmaExecution.isEmpty()) {

			Enigma enigma = enigmaExecution.stream().max(Comparator.comparing(e -> e.getEnigma().getNumber())).get().getEnigma();
			return enigma;
		}

		log.info("[getCurrentEnigmaForUser] No current enigma for user, return first One");

		Optional<Enigma> firstEnigma = enigmaRepository.findOneByNumber(1);

		// No enigma has been found
		if (!firstEnigma.isPresent()) {
			log.error("[getCurrentEnigmaForUser] No enigma has been found");
			return null;
		}

		return firstEnigma.get();
	}

	@Transactional(readOnly = true)
	public EnigmaExecutionResult saveEnigmaExecution(Enigma enigmaToCheck) {

		log.debug("[saveEnigmaExecution] REST request to check Enigma : {}", enigmaToCheck);

		Optional<Enigma> enigmaInDb = enigmaRepository.findOneByNumber(enigmaToCheck.getNumber());

		if (!enigmaInDb.isPresent()) {
			log.error("[saveEnigmaExecution] No enigma has been found");
			return null;
		}

		log.debug("[saveEnigmaExecution] Enigma answer to check : -{}-", enigmaToCheck.getAnswer());
		log.debug("[saveEnigmaExecution] Enigma answer in db : -{}-", enigmaInDb.get().getAnswer());

		// Check if answer match proposition
		boolean hasBeenFound = enigmaInDb.get().getAnswer().trim().replaceAll("/\\s/", "").equalsIgnoreCase(enigmaToCheck.getAnswer().trim().replaceAll("/\\s/", ""));

		Optional<User> user = userRepository.findOneByLogin(SecurityUtils.getCurrentUser().getUsername());

		// No user has been found
		if (!user.isPresent()) {
			log.error("[saveEnigmaExecution] No user has been found");
			return null;
		}

		EnigmaExecution enigmaExecution = new EnigmaExecution(user.get(), enigmaInDb.get(), 2l, enigmaToCheck.getAnswer(), hasBeenFound);
		enigmaExecutionRepository.save(enigmaExecution);

		if (!hasBeenFound) {
			return EnigmaExecutionResult.NOT_FOUND;
		}

		if (hasBeenFound && hasFinishEnigma(enigmaInDb.get().getNumber())) {
			return EnigmaExecutionResult.FINISHED;
		}
		return EnigmaExecutionResult.FOUND;

	}

	@Transactional(readOnly = true)
	public boolean hasFinishEnigma(long currentEnigmaNumber) {
		return (currentEnigmaNumber >= enigmaRepository.getMaxNumber());
	}

}
