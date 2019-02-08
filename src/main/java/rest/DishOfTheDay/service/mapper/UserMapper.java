package rest.DishOfTheDay.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import rest.DishOfTheDay.domain.User;
import rest.DishOfTheDay.domain.dto.UserDTO;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO fromUser (User user);

    User toUser (UserDTO userDTO);

    List<UserDTO> fromRestaurants (List<User> users);

    List<User> toRestaurants (List<UserDTO> userDTOS);
}
