package rest.DishOfTheDay.service.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import rest.DishOfTheDay.domain.User;
import rest.DishOfTheDay.domain.dto.UserReqDTO;
import rest.DishOfTheDay.domain.dto.UserRespDTO;

import java.util.List;

@Component
@Mapper
@DecoratedWith(UserMapperDecorator.class)
public interface UserMapper {

    UserRespDTO fromUser (User user);

    User toUser (UserReqDTO userDTO);

    List<UserRespDTO> fromUsers (List<User> users);

    List<User> toUsers (List<UserReqDTO> userDTOS);
}
