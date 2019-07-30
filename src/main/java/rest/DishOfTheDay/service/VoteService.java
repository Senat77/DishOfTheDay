package rest.DishOfTheDay.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rest.DishOfTheDay.domain.Vote;
import rest.DishOfTheDay.domain.dto.VoteReqDTO;
import rest.DishOfTheDay.domain.dto.VoteRespDTO;
import rest.DishOfTheDay.repository.PollRepository;
import rest.DishOfTheDay.repository.UserRepository;
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

    private final UserRepository userRepository;

    private VoteMapper mapper;

    public static LocalTime END_OF_POLL_TIME = LocalTime.of(11,0,0);

    @Autowired
    public VoteService(VoteRepository repository, PollRepository pollRepository, UserRepository userRepository, VoteMapper mapper) {
        this.repository = repository;
        this.pollRepository = pollRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    public VoteRespDTO getVote(Integer userId, LocalDate date) throws EntityNotFoundException {
        Vote vote = repository.findByUserAndPoll(userRepository.getOne(userId), pollRepository.getOne(date));
        if(vote != null)
            return mapper.fromVote(vote);
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
            Vote vote = repository.findByUserAndPoll(userRepository.getOne(userId), pollRepository.getOne(LocalDate.now()));
            if (vote != null)
                repository.delete(vote);
            else
                throw new EntityNotFoundException();
        }
    }

    @Transactional
    public VoteRespDTO update(int userId, VoteReqDTO voteDTO) throws EntityNotFoundException, PollNotActiveException {
        if(ifVotesEnabled()) {
            Vote vote = repository.findByUserAndPoll(userRepository.getOne(userId), pollRepository.getOne(LocalDate.now()));
            if (vote == null)
                throw new EntityNotFoundException();
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
        //System.out.println("==========" + LocalTime.now(clock) + " ==========");
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
