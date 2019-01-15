package rest.DishOfTheDay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.DishOfTheDay.domain.Restaurant;
import rest.DishOfTheDay.repository.RestaurantRepository;
import rest.DishOfTheDay.util.exception.NotFoundEntityException;

import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository repository;

    @Autowired
    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    public List<Restaurant> getAll() {
        return repository.getAllOrderByName();
    }

    public Restaurant get(Integer id) {
        Restaurant restaurant = repository.get(id);
        if(restaurant == null) {
            throw new NotFoundEntityException(Restaurant.class);
        }
        return restaurant;
    }
}
