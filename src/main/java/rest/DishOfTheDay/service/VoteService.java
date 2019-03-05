package rest.DishOfTheDay.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rest.DishOfTheDay.domain.Vote;
import rest.DishOfTheDay.domain.dto.VoteRespDTO;
import rest.DishOfTheDay.repository.VoteRepository;
import rest.DishOfTheDay.service.mapper.VoteMapper;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class VoteService {

    private final Logger log  = LoggerFactory.getLogger(getClass());

    private final VoteRepository repository;

    private VoteMapper mapper;

    public VoteService(VoteRepository repository) {
        this.repository = repository;
    }

    public VoteRespDTO getVote(Integer userId, LocalDate date) {
        Optional<Vote> oVote = repository.findByUserIdAndPollId(userId, date);
    }
}
