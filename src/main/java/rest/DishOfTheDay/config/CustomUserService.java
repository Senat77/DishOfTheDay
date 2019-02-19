package rest.DishOfTheDay.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import rest.DishOfTheDay.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class CustomUserService implements UserDetailsService {

    private final UserRepository userRepository;

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
