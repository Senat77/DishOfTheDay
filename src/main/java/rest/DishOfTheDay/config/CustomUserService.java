package rest.DishOfTheDay.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import rest.DishOfTheDay.repository.UserRepository;

@Component
public class CustomUserService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return userRepository.findByName(name)
                .map(user -> new AuthUser(
                        user.getName(),
                        user.getPassword(),
                        user.getEmail(),
                        user.getRoles()
                        ))
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }
}
