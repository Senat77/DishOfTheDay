package rest.DishOfTheDay.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rest.DishOfTheDay.domain.dto.PollReqDTO;
import rest.DishOfTheDay.domain.dto.PollRespDTO;
import rest.DishOfTheDay.service.PollService;
import rest.DishOfTheDay.service.VotingResultService;
import rest.DishOfTheDay.util.exception.EntityNotFoundException;
import rest.DishOfTheDay.util.exception.IllegalMenuSetOfPollException;
import java.time.LocalDate;


@RestController
@RequestMapping(value = PollRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class PollRestController {

    static final String REST_URL = "/api/polls";

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final PollService service;

    @Autowired
    public PollRestController(PollService service, VotingResultService votingResultService) {
        this.service = service;
    }

    // ADMIN-role's allowed endpoints

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> create(@RequestBody PollReqDTO pollDTO) throws IllegalMenuSetOfPollException {
        log.info("Create poll {}", pollDTO);
        PollRespDTO pollRespDTO = service.create(pollDTO);
        return new ResponseEntity<>(pollRespDTO, HttpStatus.CREATED);
    }

    // USER-role's allowed endpoints

    @GetMapping("/{id}")
    public PollRespDTO get(@PathVariable("id") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) throws EntityNotFoundException {
        return service.get(date);
    }
}
