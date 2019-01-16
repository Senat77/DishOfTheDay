package rest.DishOfTheDay.controller;

import rest.DishOfTheDay.domain.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import rest.DishOfTheDay.service.RestaurantService;

import java.util.List;

@RestController
@RequestMapping(value = RestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRestController {

    static final String REST_URL = "/api/admin/restaurants";

    private final RestaurantService service;

    @Autowired
    public RestaurantRestController(RestaurantService service) {
        this.service = service;
    }

    @GetMapping
    public List<Restaurant> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    public Restaurant getRestaurant(@PathVariable("id") Integer id) {
        return service.get(id);
    }

    /*
    @PostMapping
    public Restaurant add (@RequestParam String name) {
        if(repository.findByName(name) != null)
            return null;
        else
            return repository.save(new Restaurant(name));
    }

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
