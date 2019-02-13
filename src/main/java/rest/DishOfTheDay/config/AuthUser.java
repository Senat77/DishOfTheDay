package rest.DishOfTheDay.config;

import lombok.*;
import rest.DishOfTheDay.domain.User;

import java.util.Collections;
import java.util.Set;

@Value
@EqualsAndHashCode(callSuper = false)
public class AuthUser extends org.springframework.security.core.userdetails.User {

    private String email;

    public AuthUser(final String username, final String password, final String email, final User.Role ... roles) {
        super(username, password, Set.of(roles));
        this.email = email;
    }
}
