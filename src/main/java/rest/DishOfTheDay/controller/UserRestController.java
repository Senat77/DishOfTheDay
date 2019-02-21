package rest.DishOfTheDay.controller;

import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import rest.DishOfTheDay.config.AuthUser;
import rest.DishOfTheDay.domain.dto.UserReqDTO;
import rest.DishOfTheDay.domain.dto.UserRespDTO;
import rest.DishOfTheDay.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = UserRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestController {

    static final String REST_URL = "/api/users";

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final UserService service;

    @Autowired
    public UserRestController(UserService service) {
        this.service = service;
    }

    // All allowed entry(registration) endpoint

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create (@Validated({UserReqDTO.New.class}) @RequestBody UserReqDTO userDTO) {
        log.info("Create user {}", userDTO);
        return new ResponseEntity<> (service.create(userDTO), HttpStatus.CREATED);
    }

    // ADMIN-role's allowed endpoints

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UserRespDTO> getAll() {
        log.info("Get all users");
        return service.getAll();
    }

    @DeleteMapping(value = "/{id}/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable ("id") Integer id) {
        log.info("Delete User id = {}", id);
        service.delete(id);
    }

    // USER-role's allowed endpoints

    @GetMapping("/profile")
    public UserRespDTO getMe(@NonNull final Authentication authentication) {
        Integer userId = ((AuthUser) authentication.getPrincipal()).getUserId();
        return service.get(userId);
    }
}
