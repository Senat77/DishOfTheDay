package rest.DishOfTheDay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import rest.DishOfTheDay.domain.Restaurant;
import rest.DishOfTheDay.repository.RestaurantRepository;
import rest.DishOfTheDay.util.exception.NotFoundException;

import java.util.List;

import static rest.DishOfTheDay.util.ValidationUtil.checkNotFoundWithId;

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
        return checkNotFoundWithId(repository.get(id), id, Restaurant.class);
    }

    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return repository.save(restaurant);
    }

    public void delete (int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id, Restaurant.class);
    }

    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        repository.save(restaurant);
    }
}
