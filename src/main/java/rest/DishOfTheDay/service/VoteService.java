package rest.DishOfTheDay.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import rest.DishOfTheDay.domain.Vote;
import rest.DishOfTheDay.domain.dto.VoteReqDTO;
import rest.DishOfTheDay.domain.dto.VoteRespDTO;
import rest.DishOfTheDay.repository.VoteRepository;
import rest.DishOfTheDay.service.mapper.VoteMapper;
import rest.DishOfTheDay.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class VoteService {

    private final Logger log  = LoggerFactory.getLogger(getClass());

    private final VoteRepository repository;

    private VoteMapper mapper;

    @Autowired
    public VoteService(VoteRepository repository, VoteMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public VoteRespDTO getVote(Integer userId, LocalDate date) {
        Optional<Vote> oVote = repository.findByUserIdAndPollId(userId, date);
        if(oVote.isPresent())
            return mapper.fromVote(oVote.get());
        else
            throw new NotFoundException(Vote.class);
    }

    @Transactional
    public VoteRespDTO create(int userId, VoteReqDTO voteDTO) {
        Assert.notNull(voteDTO, "Vote must not be null");
        voteDTO.setUser_id(userId);
        Vote vote = mapper.toVote(voteDTO);
        repository.save(vote);
        log.info("Vote created : {}", vote);
        return mapper.fromVote(vote);
    }

    @Transactional
    public void delete(int userId) {
        Optional<Vote> oVote = repository.findByUserIdAndPollId(userId, LocalDate.now());
        if(oVote.isPresent())
            repository.delete(oVote.get());
        else
            throw new NotFoundException(Vote.class);
    }

    @Transactional
    public VoteRespDTO update(int userId, VoteReqDTO voteDTO) {
        Assert.notNull(voteDTO, "Vote must not be null");
        Optional<Vote> oVote = repository.findByUserIdAndPollId(userId, LocalDate.now());
        Vote vote;
        if (oVote.isPresent()) {
            vote = oVote.get();
        }
        else {
            throw new NotFoundException(Vote.class);
        }
        mapper.toUpdate(vote, voteDTO);
        log.debug("[i] Vote with id={} updated : {}", vote.getId(), vote);
        return mapper.fromVote(vote);
    }

    private Vote getById(Integer id) {
        Optional<Vote> oVote = repository.findById(id);
        if(oVote.isPresent())
            return oVote.get();
        else
            throw new NotFoundException(Vote.class);
    }
}
