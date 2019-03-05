package rest.DishOfTheDay.service.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper
@DecoratedWith(VoteMapperDecorator.class)
public interface VoteMapper {
}
