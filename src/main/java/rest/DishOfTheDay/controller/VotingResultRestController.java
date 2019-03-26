package rest.DishOfTheDay.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import rest.DishOfTheDay.domain.dto.VotingResult;
import rest.DishOfTheDay.service.VotingResultService;

import java.time.LocalDate;

@RestController
@RequestMapping(value = VotingResultRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VotingResultRestController {

    static final String REST_URL = "/api/dishoftheday";

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final VotingResultService service;

    @Autowired
    public VotingResultRestController(VotingResultService service) {
        this.service = service;
    }

    @GetMapping
    public VotingResult getResult() {
        return service.getResult(LocalDate.now());
    }

    @GetMapping("/{date}")
    public VotingResult getResultByDate(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return service.getResult(date);
    }
}
