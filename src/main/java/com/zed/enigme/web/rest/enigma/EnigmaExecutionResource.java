package com.zed.enigme.web.rest.enigma;

import com.codahale.metrics.annotation.Timed;
import com.zed.enigme.domain.enigma.Enigma;
import com.zed.enigme.enumeration.EnigmaExecutionResult;
import com.zed.enigme.enumeration.EnigmaState;
import com.zed.enigme.service.enigma.EnigmaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api")
public class EnigmaExecutionResource {

    private final Logger log = LoggerFactory.getLogger(EnigmaExecutionResource.class);

    @Inject
    private EnigmaService enigmaService;

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
    @Transactional(readOnly = true)
    public ResponseEntity<EnigmaExecutionResult> saveEnigmaExecution(@RequestBody Enigma enigmaToCheck) throws URISyntaxException {

        log.debug("[saveEnigmaExecution] REST request to check Enigma : {}", enigmaToCheck);

        EnigmaExecutionResult enigmaExecutionResult = enigmaService.saveEnigmaExecution(enigmaToCheck);

        if (enigmaExecutionResult == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(enigmaExecutionResult, HttpStatus.OK);
        }
    }
}
