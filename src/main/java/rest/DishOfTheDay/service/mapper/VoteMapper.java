package rest.DishOfTheDay.service.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import rest.DishOfTheDay.domain.Vote;
import rest.DishOfTheDay.domain.dto.VoteReqDTO;
import rest.DishOfTheDay.domain.dto.VoteRespDTO;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValueMappingStrategy.RETURN_DEFAULT;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(
        nullValueCheckStrategy = ALWAYS,
        nullValueMappingStrategy = RETURN_DEFAULT,
        nullValuePropertyMappingStrategy = IGNORE
)
@DecoratedWith(VoteMapperDecorator.class)
public interface VoteMapper {

    @Mapping(source = "vote.poll.id", target = "date")
    @Mapping(source = "vote.menu.id", target = "menu_id")
    VoteRespDTO fromVote(Vote vote);

    Vote toVote(VoteReqDTO voteDTO);

    Vote toUpdate(@MappingTarget Vote vote, VoteReqDTO voteReqDTO);
}
