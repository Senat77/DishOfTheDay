package rest.DishOfTheDay.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import rest.DishOfTheDay.domain.*;
import rest.DishOfTheDay.repository.*;
import java.util.Set;

@Profile("prod")
@Component
public class ProdData implements AbstractData {

    private final UserRepository userRepository;

    @Autowired
    public ProdData(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    public final User ADMIN = new User("admin", passwordEncoder.encode("admin"), "admin1@site.com", Set.of(User.Role.ROLE_ADMIN));

    public void populate() {
            userRepository.save(ADMIN);
    }
}
