package rest.DishOfTheDay.service.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import rest.DishOfTheDay.domain.User;
import rest.DishOfTheDay.domain.dto.UserReqDTO;
import rest.DishOfTheDay.domain.dto.UserRespDTO;

import java.util.List;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValueMappingStrategy.RETURN_DEFAULT;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(
        nullValueCheckStrategy = ALWAYS,
        nullValueMappingStrategy = RETURN_DEFAULT,
        nullValuePropertyMappingStrategy = IGNORE
)
@DecoratedWith(UserMapperDecorator.class)
public interface UserMapper {

    UserRespDTO fromUser (User user);

    User toUser (UserReqDTO userDTO);

    User toUpdate(@MappingTarget User user, UserReqDTO userReqDTO);

    List<UserRespDTO> fromUsers (List<User> users);

    List<User> toUsers (List<UserReqDTO> userDTOS);
}
