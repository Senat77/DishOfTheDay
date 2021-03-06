package rest.DishOfTheDay.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import rest.DishOfTheDay.domain.Menu;
import rest.DishOfTheDay.domain.Poll;
import rest.DishOfTheDay.domain.Restaurant;
import rest.DishOfTheDay.domain.dto.PollReqDTO;
import rest.DishOfTheDay.domain.dto.PollRespDTO;
import rest.DishOfTheDay.repository.PollRepository;
import rest.DishOfTheDay.repository.VotingHistoryRepository;
import rest.DishOfTheDay.service.mapper.PollMapper;
import rest.DishOfTheDay.util.exception.IllegalMenuSetOfPollException;
import rest.DishOfTheDay.util.exception.EntityNotFoundException;

import java.time.LocalDate;
import java.util.*;

@Service
@Transactional(readOnly = true)
public class PollService {

    private final Logger log  = LoggerFactory.getLogger(getClass());

    private final PollRepository repository;

    private final VotingHistoryRepository history;

    private final PollMapper mapper;

    @Autowired
    public PollService(PollRepository repository, VotingHistoryRepository history, PollMapper mapper) {
        this.repository = repository;
        this.history = history;
        this.mapper = mapper;
    }

    @Transactional
    public PollRespDTO create(PollReqDTO pollDTO) throws IllegalMenuSetOfPollException {
        Assert.notNull(pollDTO, "Poll must not be null");
        Poll poll = mapper.toPoll(pollDTO);
        if(checkSetOfMenus(poll.getMenus()))
            throw new IllegalMenuSetOfPollException();
        repository.save(poll);
        log.info("Poll created : {}", poll);
        return mapper.fromPoll(poll);
    }

    public PollRespDTO get(LocalDate id) throws EntityNotFoundException {
        Optional<Poll> oPoll = repository.findById(id);
        if(oPoll.isPresent())
            return mapper.fromPoll(oPoll.get());
        else
            throw new EntityNotFoundException();
    }

    // Check of unique menu for restaurant in poll menu's set
    private boolean checkSetOfMenus(Set<Menu> menus) {
        Map<Restaurant, Long> map = new HashMap<>();
        for (Iterator<Menu> it = menus.iterator(); it.hasNext(); ) {
            Menu m = it.next();
            Restaurant r = m.getRestaurant();
            if(map.containsKey(r)) {
                map.replace(r, map.get(r) + 1);
            }
            else {
                map.put(r, 1L);
            }
        }
        for(Map.Entry<Restaurant, Long> e : map.entrySet()) {
            if(e.getValue() > 1L)
                return true;
        }
        return false;
    }
}
