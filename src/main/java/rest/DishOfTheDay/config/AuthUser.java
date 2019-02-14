package rest.DishOfTheDay.config;

import lombok.*;
import rest.DishOfTheDay.domain.User;
import java.util.Set;

@Value
@EqualsAndHashCode(callSuper = false)
public class AuthUser extends org.springframework.security.core.userdetails.User {

    private String email;

    public AuthUser(final String username, final String password, final String email, final Set<User.Role> roles) {
        super(username, password, roles);
        this.email = email;
    }
}
