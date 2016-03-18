package com.zed.enigme.web.rest.enigma;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.zed.enigme.domain.enigma.Enigma;
import com.zed.enigme.repository.enigma.EnigmaRepository;
import com.zed.enigme.web.rest.util.PaginationUtil;

@RestController
@RequestMapping("/api")
public class EnigmaResource {

	private final Logger log = LoggerFactory.getLogger(EnigmaResource.class);

	@Inject
	private EnigmaRepository enigmaRepository;

	/**
	 * GET /enigma -> get all enigma.
	 */
	@RequestMapping(value = "/enigma", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	@Transactional(readOnly = true)
	public ResponseEntity<List<Enigma>> getAllUsers(Pageable pageable) throws URISyntaxException {

		log.debug("REST request to get enigmas");

		Page<Enigma> page = enigmaRepository.findAll(pageable);
		List<Enigma> enigmas = page.getContent().stream().collect(Collectors.toList());
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/enigma");
		return new ResponseEntity<>(enigmas, headers, HttpStatus.OK);
	}

	/**
	 * POST /enigma/check -> Check enigma
	 */
	@RequestMapping(value = "/enigma/check", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	@Transactional(readOnly = true)
	public ResponseEntity<?> checkEnigma(@RequestBody Enigma enigmaToCheck, HttpServletRequest request) throws URISyntaxException {

		log.debug("REST request to check Enigma : {}", enigmaToCheck);

		Optional<Enigma> optionalEnigma = enigmaRepository.findOneByNumber(enigmaToCheck.getNumber());

		if (!optionalEnigma.isPresent()) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

		log.debug("Enigma answer to check : -{}-", enigmaToCheck.getAnswer());
		log.debug("Enigma answer in db : -{}-", optionalEnigma.get().getAnswer());

		boolean hasBeenFound = optionalEnigma.get().getAnswer().trim().replaceAll("/\\s/", "").equalsIgnoreCase(enigmaToCheck.getAnswer().trim().replaceAll("/\\s/", ""));
		if (hasBeenFound) {
			return ResponseEntity.ok(true);
		}
		return ResponseEntity.ok(false);
	}

}
