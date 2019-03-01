package rest.DishOfTheDay.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import rest.DishOfTheDay.domain.Poll;
import rest.DishOfTheDay.domain.dto.PollReqDTO;
import rest.DishOfTheDay.domain.dto.PollRespDTO;
import rest.DishOfTheDay.repository.PollRepository;
import rest.DishOfTheDay.service.mapper.PollMapper;
import rest.DishOfTheDay.util.exception.NotFoundException;

import java.time.LocalDate;

@Service
@Transactional(readOnly = true)
public class PollService {

    private final Logger log  = LoggerFactory.getLogger(getClass());

    private final PollRepository repository;

    private final PollMapper mapper;

    @Autowired
    public PollService(PollRepository repository, PollMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public PollRespDTO create(PollReqDTO pollDTO) {
        Assert.notNull(pollDTO, "Poll must not be null");
        Poll poll = mapper.toPoll(pollDTO);
        repository.save(poll);
        log.info("Poll created : {}", poll);
        return mapper.fromPoll(poll);
    }

    public PollRespDTO get(LocalDate id) {
        Poll poll = repository.getOne(id);
        if(poll == null)
            throw new NotFoundException(Poll.class);
        else {
            return mapper.fromPoll(poll);
        }
    }
}
