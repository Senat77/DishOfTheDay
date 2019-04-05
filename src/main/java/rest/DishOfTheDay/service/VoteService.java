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
import rest.DishOfTheDay.repository.PollRepository;
import rest.DishOfTheDay.repository.VoteRepository;
import rest.DishOfTheDay.service.mapper.VoteMapper;
import rest.DishOfTheDay.util.exception.EntityNotFoundException;
import rest.DishOfTheDay.util.exception.PollNotActiveException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class VoteService {

    private final Logger log  = LoggerFactory.getLogger(getClass());

    private final VoteRepository repository;

    private final PollRepository pollRepository;

    private VoteMapper mapper;

    public static LocalTime END_OF_POLL_TIME = LocalTime.of(11,0,0);

    @Autowired
    public VoteService(VoteRepository repository, PollRepository pollRepository, VoteMapper mapper) {
        this.repository = repository;
        this.pollRepository = pollRepository;
        this.mapper = mapper;
    }

    public VoteRespDTO getVote(Integer userId, LocalDate date) throws EntityNotFoundException {
        Optional<Vote> oVote = repository.findByUserIdAndPollId(userId, date);
        if(oVote.isPresent())
            return mapper.fromVote(oVote.get());
        else
            throw new EntityNotFoundException();
    }

    @Transactional
    public VoteRespDTO create(int userId, VoteReqDTO voteDTO) throws PollNotActiveException, EntityNotFoundException {
        if(ifVotesEnabled()) {
            voteDTO.setUser_id(userId);
            Vote vote = mapper.toVote(voteDTO);
            repository.save(vote);
            log.info("Vote created : {}", vote);
            return mapper.fromVote(vote);
        }
        return null;
    }

    @Transactional
    public void delete(int userId) throws EntityNotFoundException, PollNotActiveException {
        if(ifVotesEnabled()) {
            Optional<Vote> oVote = repository.findByUserIdAndPollId(userId, LocalDate.now());
            if (oVote.isPresent())
                repository.delete(oVote.get());
            else
                throw new EntityNotFoundException();
        }
    }

    @Transactional
    public VoteRespDTO update(int userId, VoteReqDTO voteDTO) throws EntityNotFoundException, PollNotActiveException {
        if(ifVotesEnabled()) {
            Optional<Vote> oVote = repository.findByUserIdAndPollId(userId, LocalDate.now());
            Vote vote;
            if (oVote.isPresent()) {
                vote = oVote.get();
            } else {
                throw new EntityNotFoundException();
            }
            mapper.toUpdate(vote, voteDTO);
            log.debug("[i] Vote with id={} updated : {}", vote.getId(), vote);
            return mapper.fromVote(vote);
        }
        return null;
    }

    private Vote getById(Integer id) throws EntityNotFoundException {
        Optional<Vote> oVote = repository.findById(id);
        if(oVote.isPresent())
            return oVote.get();
        else
            throw new EntityNotFoundException();
    }

    private boolean ifPollExist() {
        return (pollRepository.getOne(LocalDate.now()) != null);
    }

    private boolean ifPollActive() {
        return (LocalTime.now().isBefore(END_OF_POLL_TIME));
    }

    private boolean ifVotesEnabled() throws EntityNotFoundException, PollNotActiveException {
        if(!ifPollExist())
            throw new EntityNotFoundException();
        if(!ifPollActive())
            throw new PollNotActiveException();
        return true;
    }
}
