package rest.DishOfTheDay.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import rest.DishOfTheDay.domain.User;
import rest.DishOfTheDay.domain.dto.UserDTO;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "password", ignore = true)
    UserDTO fromUser (User user);

    User toUser (UserDTO userDTO);

    List<UserDTO> fromUsers (List<User> users);

    List<User> toUsers (List<UserDTO> userDTOS);
}
