package com.zed.enigme.service.enigma;

import com.zed.enigme.domain.User;
import com.zed.enigme.domain.enigma.Enigma;
import com.zed.enigme.domain.enigma.EnigmaExecution;
import com.zed.enigme.repository.UserRepository;
import com.zed.enigme.repository.enigma.EnigmaExecutionRepository;
import com.zed.enigme.repository.enigma.EnigmaRepository;
import com.zed.enigme.web.rest.dto.UserStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service class for managing enigmas.
 */
@Service
@Transactional
public class EnigmaExecutionService {

    private final Logger log = LoggerFactory.getLogger(EnigmaExecutionService.class);
    @Inject
    private EnigmaExecutionRepository enigmaExecutionRepository;
    @Inject
    private EnigmaRepository enigmaRepository;
    @Inject
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public Map<User, UserStatistics> getTryNumberForEachUser() {

        log.debug("[getTryNumberForEachUser]");

        List<Enigma> enigmas = enigmaRepository.findAll();

        Map<User, UserStatistics> userMap = new HashMap<>();

        userRepository.findAll().forEach(user -> {

            long totalNumberOfTry = enigmaExecutionRepository.countByUser_Id(user.getId());
            long totalDuration = 0;
            long currentEnigma = 0;

            List<EnigmaExecution> enigmaExecution = enigmaExecutionRepository.findByUser_Id(user.getId());

            // enigmaExecution has been found
            if (enigmaExecution != null && !enigmaExecution.isEmpty()) {
                Enigma enigma = enigmaExecution.stream().max(Comparator.comparing(e -> e.getEnigma().getNumber())).get().getEnigma();
                currentEnigma = enigma.getId();

                totalDuration = enigmaExecution.stream().mapToLong(EnigmaExecution::getDuration).sum();
            }

            UserStatistics userStatistics = new UserStatistics(user, totalNumberOfTry, totalDuration, currentEnigma);

            userMap.put(user, userStatistics);
        });

        return userMap;
    }


}
