package rest.DishOfTheDay.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import rest.DishOfTheDay.domain.dto.PollReqDTO;
import rest.DishOfTheDay.domain.dto.PollRespDTO;
import rest.DishOfTheDay.service.PollService;

import java.time.LocalDate;

@RestController
@RequestMapping(value = PollRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class PollRestController {

    static final String REST_URL = "/api/polls";

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final PollService service;

    @Autowired
    public PollRestController(PollService service) {
        this.service = service;
    }

    // ADMIN-role's allowed endpoints

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> create(@Validated({PollReqDTO.New.class}) @RequestBody PollReqDTO pollDTO) {
        log.info("Create poll {}", pollDTO);
        return new ResponseEntity<>(service.create(pollDTO), HttpStatus.CREATED);
    }

    // USER-role's allowed endpoints
    @GetMapping("/{id}")
    public PollRespDTO get(@PathVariable("id") LocalDate id) {
        return service.get(id);
    }
}
