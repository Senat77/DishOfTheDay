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
import rest.DishOfTheDay.util.exception.IllegalRequestDataException;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = RestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRestController {

    static final String REST_URL = "/api/admin/restaurants";

    private final Logger log = LoggerFactory.getLogger(getClass());

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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create (@RequestBody Restaurant restaurant) {
        log.info("Create restaurant {}", restaurant);
        ValidationUtil.checkNew(restaurant);
        Restaurant created = service.save(restaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping(value = "/{id}/delete")
    public void delete(@PathVariable ("id") Integer id) {
        log.info("Delete Restaurant id = {}", id);
        service.delete(id);
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant update (@RequestBody Restaurant restaurant, @PathVariable("id") Integer id) {
        Restaurant updated = service.get(id);
        log.info("Patch restaurant {}. New values {}", updated, restaurant);
        if(!updated.getId().equals(restaurant.getId()))
            throw new IllegalRequestDataException("Id's do not match!");
        return service.save(restaurant);
    }
}
