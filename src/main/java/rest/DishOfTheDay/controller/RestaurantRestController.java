package rest.DishOfTheDay.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import rest.DishOfTheDay.domain.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import rest.DishOfTheDay.service.RestaurantService;
import rest.DishOfTheDay.util.ValidationUtil;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = RestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRestController {

    static final String REST_URL = "/api/admin/restaurants";

    protected final Logger log = LoggerFactory.getLogger(getClass());

    private final RestaurantService service;

    @Autowired
    public RestaurantRestController(RestaurantService service) {
        this.service = service;
    }

    @GetMapping
    public List<Restaurant> getAll() {
        log.info("get all restaurants");
        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    public Restaurant getRestaurant(@PathVariable("id") Integer id) {
        log.info("get restaurant with id = {}", id);
        return service.get(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Restaurant> create (@RequestBody Restaurant restaurant) {
        log.info("Create restaurant {}", restaurant);
        ValidationUtil.checkNew(restaurant);
        Restaurant created = service.create(restaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    /*
    @DeleteMapping("/{id}/delete")
    public void delete(@PathVariable Integer id) {
        if(repository.findById(id).isPresent())
            repository.deleteById(id);
    }

    @PostMapping("/{id}/vote")
    public Vote addVote(@PathVariable Integer id) {
        return null;
    }
    */
}
