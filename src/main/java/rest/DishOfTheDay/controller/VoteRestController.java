package rest.DishOfTheDay.controller;

import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import rest.DishOfTheDay.domain.dto.VoteReqDTO;
import rest.DishOfTheDay.domain.dto.VoteRespDTO;
import rest.DishOfTheDay.service.VoteService;
import rest.DishOfTheDay.util.exception.EntityNotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;

@RestController
@RequestMapping(value = VoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController extends AbstractRestController {

    static final String REST_URL = "/api/votes";

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final VoteService service;

    @Autowired
    public VoteRestController(VoteService service) {
        this.service = service;
    }

    @GetMapping
    public VoteRespDTO getMyVote(@NonNull final Authentication authentication) throws EntityNotFoundException {
        return service.getVote(getAuthUserId(authentication), LocalDate.now());
    }

    @GetMapping("/getbydate/{date}")
    public VoteRespDTO getMyVoteByDate(@NonNull final Authentication authentication, @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date)
            throws EntityNotFoundException {
        return service.getVote(getAuthUserId(authentication), date);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@NonNull final Authentication authentication,
                                    @Validated({VoteReqDTO.New.class}) @RequestBody VoteReqDTO voteDTO) {
        log.info("Create vote {}", voteDTO);
        return new ResponseEntity<>(service.create(getAuthUserId(authentication),voteDTO), HttpStatus.CREATED);
    }

    @DeleteMapping
    public void delete(@NonNull final Authentication authentication) throws EntityNotFoundException {
        service.delete(getAuthUserId(authentication));
    }

    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public VoteRespDTO update(@NonNull final Authentication authentication,
                              @Validated({VoteReqDTO.New.class}) @RequestBody VoteReqDTO voteDTO) throws EntityNotFoundException {
        return service.update(getAuthUserId(authentication), voteDTO);
    }
}
