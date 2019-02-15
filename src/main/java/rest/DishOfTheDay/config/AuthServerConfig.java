package rest.DishOfTheDay.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@Configuration
@EnableAuthorizationServer
@EnableResourceServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    private final AuthenticationManager authenticationManager;

    public AuthServerConfig(final AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clientDetailsService) throws Exception {
        clientDetailsService.inMemory()
                .withClient("DOTDclient")
                .secret("{noop}dotdclient")
                .scopes("*")
                .authorizedGrantTypes("password", "refresh_token")
                .accessTokenValiditySeconds(60 * 2) // 2 min
                .refreshTokenValiditySeconds(60 * 60); // 60 min
    }
}
