package rest.DishOfTheDay.service.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import rest.DishOfTheDay.domain.Vote;
import rest.DishOfTheDay.domain.dto.VoteReqDTO;
import rest.DishOfTheDay.domain.dto.VoteRespDTO;

@Mapper
@DecoratedWith(VoteMapperDecorator.class)
public interface VoteMapper {

    @Mapping(source = "vote.poll.id", target = "date")
    @Mapping(source = "vote.menu.id", target = "menu_id")
    VoteRespDTO fromVote(Vote vote);

    Vote toVote(VoteReqDTO voteDTO);
}
