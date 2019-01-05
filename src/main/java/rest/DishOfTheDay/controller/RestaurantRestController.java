package rest.DishOfTheDay.controller;

import rest.DishOfTheDay.model.Restaurant;
import rest.DishOfTheDay.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = RestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRestController {

    static final String REST_URL = "/api/restaurants";

    @Autowired
    private RestaurantRepository repository;

    @GetMapping
    public List<Restaurant> getAll() {
        return (List<Restaurant>) repository.findAll();
    }

    @GetMapping("/{id}")
    public Restaurant getById(@PathVariable Integer id) {
        Optional restaurant = repository.findById(id);
        return restaurant.isPresent() ? (Restaurant) restaurant.get() : null;
    }

    @PostMapping
    public Restaurant add (@RequestParam String name) {
        if(repository.findByName(name) != null)
            return null;
        else
            return repository.save(new Restaurant(name));
    }
}
