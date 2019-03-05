package rest.DishOfTheDay.controller;

import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rest.DishOfTheDay.domain.dto.VoteRespDTO;
import rest.DishOfTheDay.service.VoteService;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    public VoteRespDTO getMyVote(@NonNull final Authentication authentication) {
        return service.getVote(getAuthUserId(authentication), LocalDate.now());
    }
}
