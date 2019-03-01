package rest.DishOfTheDay.service.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import rest.DishOfTheDay.domain.Poll;
import rest.DishOfTheDay.domain.dto.PollReqDTO;
import rest.DishOfTheDay.domain.dto.PollRespDTO;

@Mapper
//@DecoratedWith(PollMapperDecorator.class)
public interface PollMapper {

    Poll toPoll(PollReqDTO pollDTO);

    PollRespDTO fromPoll(Poll poll);
}
