package rest.DishOfTheDay.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import rest.DishOfTheDay.domain.User;

import java.util.concurrent.ThreadLocalRandom;

// https://stackoverflow.com/questions/54149737/how-to-generate-a-jwt-access-token-with-some-custom-claims-in-it/54245750#54245750

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();         //PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(username -> new AuthUser(
                username,
                "{noop}" + username,
                username + "@mail.com",
                User.Role.values()[ThreadLocalRandom.current().nextInt(2)]));
    }
}

