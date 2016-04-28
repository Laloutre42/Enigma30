package com.zed.enigme.web.rest.enigma;

import com.codahale.metrics.annotation.Timed;
import com.zed.enigme.domain.User;
import com.zed.enigme.domain.enigma.Enigma;
import com.zed.enigme.domain.enigma.EnigmaExecutionAnswerResult;
import com.zed.enigme.enumeration.EnigmaState;
import com.zed.enigme.service.enigma.EnigmaExecutionService;
import com.zed.enigme.service.enigma.EnigmaService;
import com.zed.enigme.web.rest.dto.UserStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URISyntaxException;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EnigmaExecutionResource {

    private final Logger log = LoggerFactory.getLogger(EnigmaExecutionResource.class);

    @Inject
    private EnigmaService enigmaService;

    @Inject
    private EnigmaExecutionService enigmaExecutionService;

    /**
     * GET /enigmaExecution -> get current enigma for current user
     */
    @RequestMapping(value = "/enigmaExecution", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public ResponseEntity<Enigma> getCurrentEnigmaForUser() throws URISyntaxException {

        log.debug("[getEnigmaForUser] REST request to get current enigma for user");

        Enigma enigma = enigmaService.getLastEnigmaFoundForCurrentUser();
        if (enigma == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        enigma.setAnswer("");

        return new ResponseEntity<>(enigma, HttpStatus.OK);
    }

    /**
     * GET /enigmaExecution/state -> get current enigma STATE for current user
     */
    @RequestMapping(value = "/enigmaExecution/state", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public ResponseEntity<EnigmaState> getCurrentEnigmaStateForUser() throws URISyntaxException {

        log.debug("[getEnigmaForUser] REST request to get current enigma STATE for user");

        EnigmaState enigmaState = enigmaService.getCurrentEnigmaStateForUser();
        if (enigmaState == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(enigmaState, HttpStatus.OK);
    }

    /**
     * POST /enigmaExecution -> Save enigmaExecution
     */
    @RequestMapping(value = "/enigmaExecution", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = false)
    public ResponseEntity<EnigmaExecutionAnswerResult> saveEnigmaExecution(@RequestBody Enigma enigmaToCheck, @RequestHeader(value = "time") Long time) throws URISyntaxException {

        log.debug("[saveEnigmaExecution] REST request to check Enigma : {} with time {}", enigmaToCheck, time);

        EnigmaExecutionAnswerResult enigmaExecutionAnswerResult = enigmaService.saveEnigmaExecution(enigmaToCheck, time);

        if (enigmaExecutionAnswerResult == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(enigmaExecutionAnswerResult, HttpStatus.OK);
        }
    }

    /**
     * GET /enigmaExecution/state -> get current enigma STATE for current user
     */
    @RequestMapping(value = "/enigmaExecution/getTryNumberForEachUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public ResponseEntity<Map<User, UserStatistics>> getTryNumberForEachUser() throws URISyntaxException {

        log.debug("[getEnigmaForUser] REST request to get current enigma STATE for user");

        Map<User, UserStatistics> enigmaExecutions = enigmaExecutionService.getTryNumberForEachUser();
        if (enigmaExecutions == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Map<User, UserStatistics>>(enigmaExecutions, HttpStatus.OK);
    }
}
