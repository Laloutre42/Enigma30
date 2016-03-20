package com.zed.enigme.service.enigma;

import com.zed.enigme.domain.User;
import com.zed.enigme.domain.enigma.Enigma;
import com.zed.enigme.domain.enigma.EnigmaExecution;
import com.zed.enigme.enumeration.EnigmaExecutionResult;
import com.zed.enigme.enumeration.EnigmaState;
import com.zed.enigme.repository.UserRepository;
import com.zed.enigme.repository.enigma.EnigmaExecutionRepository;
import com.zed.enigme.repository.enigma.EnigmaRepository;
import com.zed.enigme.security.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing enigmas.
 */
@Service
@Transactional
public class EnigmaService {

    private final Logger log = LoggerFactory.getLogger(EnigmaService.class);
    @Inject
    private EnigmaExecutionRepository enigmaExecutionRepository;
    @Inject
    private EnigmaRepository enigmaRepository;
    @Inject
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public Enigma getLastEnigmaFoundForCurrentUser() {

        log.debug("[getLastEnigmaFoundForCurrentUser] Get current enigma for user login {}", SecurityUtils.getCurrentUser().getUsername());

        Optional<User> user = userRepository.findOneByLogin(SecurityUtils.getCurrentUser().getUsername());

        // No user has been found
        if (!user.isPresent()) {
            log.error("[getLastEnigmaFoundForCurrentUser] No user has been found");
            return null;
        }

        List<EnigmaExecution> enigmaExecution = enigmaExecutionRepository.findByUser_IdAndIsOkTrue(user.get().getId());

        // No enigmaExecution has been found
        if (enigmaExecution == null || enigmaExecution.isEmpty()) {

            log.info("[getLastEnigmaFoundForCurrentUser] No current enigma for user, return first One");
            Optional<Enigma> firstEnigma = enigmaRepository.findOneByNumber(1);

            // No enigma has been found
            if (!firstEnigma.isPresent()) {
                log.error("[getLastEnigmaFoundForCurrentUser] No enigma has been found");
                return null;
            }
            return firstEnigma.get();
        }

        Enigma enigma = enigmaExecution.stream().max(Comparator.comparing(e -> e.getEnigma().getNumber())).get().getEnigma();

        // Next enigma
        Optional<Enigma> nextEnigma = nextEnigma(enigma);
        if (!nextEnigma.isPresent()) {
            return null;
        }
        return nextEnigma.get();
    }

    @Transactional(readOnly = true)
    public EnigmaState getCurrentEnigmaStateForUser() {

        log.debug("[getCurrentEnigmaStateForUser] Get current enigma for user login {}", SecurityUtils.getCurrentUser().getUsername());

        Optional<User> user = userRepository.findOneByLogin(SecurityUtils.getCurrentUser().getUsername());

        // No user has been found
        if (!user.isPresent()) {
            log.error("[getCurrentEnigmaStateForUser] No user has been found");
            return null;
        }

        List<EnigmaExecution> enigmaExecution = enigmaExecutionRepository.findByUser_Id(user.get().getId());

        // No enigmas has been done
        if (enigmaExecution == null || enigmaExecution.isEmpty()) {
            return EnigmaState.BEGINNING;
        }

        // enigmaExecution has been found
        Enigma enigma = enigmaExecution.stream().max(Comparator.comparing(e -> e.getEnigma().getNumber())).get().getEnigma();
        Optional<Enigma> nextEnigma = nextEnigma(enigma);

        // No enigma has been found
        if (!nextEnigma.isPresent()) {
            return EnigmaState.END;
        }

        return EnigmaState.BEGINNING;
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

        // Wrong answer
        if (!hasBeenFound) {
            return EnigmaExecutionResult.NOT_FOUND;
        }

        // Game finished
        if (hasBeenFound && isLastEnigma(enigmaInDb.get().getNumber())) {
            return EnigmaExecutionResult.FINISHED;
        }

        // Right answer

        return EnigmaExecutionResult.FOUND;

    }

    @Transactional(readOnly = true)
    public boolean isLastEnigma(long currentEnigmaNumber) {
        return (currentEnigmaNumber >= enigmaRepository.getMaxNumber());
    }

    @Transactional(readOnly = true)
    public Optional<Enigma> nextEnigma(Enigma enigma) {
        return enigmaRepository.findOneByNumber(enigma.getNumber() + 1);
    }

}
