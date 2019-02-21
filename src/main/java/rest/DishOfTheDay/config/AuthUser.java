package rest.DishOfTheDay.config;

import lombok.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import rest.DishOfTheDay.domain.User;

import java.util.List;

@Value
@EqualsAndHashCode(callSuper = false)
public class AuthUser extends org.springframework.security.core.userdetails.User {

    private String email;

    private Integer userId;

    public AuthUser(User user) {
        super(
                user.getEmail(),
                user.getPassword(),
                user.getRoles().contains(User.Role.ROLE_ADMIN) ?
                        List.of(new SimpleGrantedAuthority("ROLE_USER"), new SimpleGrantedAuthority("ROLE_ADMIN")) :
                        List.of(new SimpleGrantedAuthority("ROLE_USER"))
        );
        userId = user.getId();
        email = user.getEmail();
    }
}
