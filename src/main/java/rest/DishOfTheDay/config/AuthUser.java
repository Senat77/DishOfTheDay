package rest.DishOfTheDay.config;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;

@Value
@EqualsAndHashCode(callSuper = false)
public class AuthUser extends org.springframework.security.core.userdetails.User {

    public AuthUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}
