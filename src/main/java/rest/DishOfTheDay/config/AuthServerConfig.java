package rest.DishOfTheDay.config;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;

public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    private final AuthenticationManager authenticationManager;

    public AuthServerConfig(final AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clientDetailsService) throws Exception {
        clientDetailsService.inMemory()
                .withClient("client")
                .secret("{noop}")
                .scopes("*")
                .authorizedGrantTypes("password", "refresh_token")
                .accessTokenValiditySeconds(60 * 2) // 2 min
                .refreshTokenValiditySeconds(60 * 60); // 60 min
    }
}
