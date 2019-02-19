package rest.DishOfTheDay.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import rest.DishOfTheDay.domain.User;
import rest.DishOfTheDay.domain.dto.UserRequestDTO;
import rest.DishOfTheDay.domain.dto.UserResponseDTO;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponseDTO fromUser (User user);

    User toUser (UserRequestDTO userDTO);

    List<UserResponseDTO> fromUsers (List<User> users);

    List<User> toUsers (List<UserRequestDTO> userDTOS);
}
