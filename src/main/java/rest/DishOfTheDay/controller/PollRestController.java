package rest.DishOfTheDay.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import rest.DishOfTheDay.domain.dto.PollReqDTO;
import rest.DishOfTheDay.domain.dto.PollRespDTO;
import rest.DishOfTheDay.service.PollService;
import rest.DishOfTheDay.service.VotingResultService;

import java.time.LocalDate;
import java.time.LocalTime;

@RestController
@RequestMapping(value = PollRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class PollRestController {

    static final String REST_URL = "/api/polls";

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final PollService service;

    //private VotingResultService votingResultService;

    @Autowired
    public PollRestController(PollService service, VotingResultService votingResultService) {
        this.service = service;
        //this.votingResultService = votingResultService;
    }

    // ADMIN-role's allowed endpoints

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> create(@RequestBody PollReqDTO pollDTO) {
        log.info("Create poll {}", pollDTO);
        PollRespDTO pollRespDTO = service.create(pollDTO);
        return new ResponseEntity<>(pollRespDTO, HttpStatus.CREATED);
    }

    // USER-role's allowed endpoints

    @GetMapping("/{id}")
    public PollRespDTO get(@PathVariable("id") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return service.get(date);
    }
}
