package rest.DishOfTheDay.service.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import rest.DishOfTheDay.domain.Poll;
import rest.DishOfTheDay.domain.dto.PollReqDTO;
import rest.DishOfTheDay.repository.MenuRepository;

import static java.util.stream.Collectors.toSet;

public abstract class PollMapperDecorator implements PollMapper {

    @Autowired
    @Qualifier("delegate")
    private PollMapper delegate_pollMapper;

    @Autowired
    private MenuRepository repository;

    @Override
    public Poll toPoll(PollReqDTO pollDTO) {
        Poll poll = delegate_pollMapper.toPoll(pollDTO);
        poll.setMenus(pollDTO.getMenu_id().stream().map(id -> repository.getOne(id)).collect(toSet()));
        return poll;
    }
}
