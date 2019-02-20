package rest.DishOfTheDay.service.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import rest.DishOfTheDay.domain.User;
import rest.DishOfTheDay.domain.dto.UserReqDTO;

public abstract class UserMapperDecorator implements UserMapper{

    @Autowired
    @Qualifier("delegate")
    private UserMapper delegate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User toUser(UserReqDTO userDTO) {
        User user = delegate.toUser(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return user;
    }
}
