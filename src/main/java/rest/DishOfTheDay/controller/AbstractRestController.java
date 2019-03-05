package rest.DishOfTheDay.controller;

import org.springframework.security.core.Authentication;
import rest.DishOfTheDay.config.AuthUser;

public class AbstractRestController {

    protected int getAuthUserId (Authentication authentication) {
        return ((AuthUser) authentication.getPrincipal()).getUserId();
    }
}
