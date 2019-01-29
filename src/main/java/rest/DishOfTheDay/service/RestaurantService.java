package rest.DishOfTheDay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import rest.DishOfTheDay.domain.Restaurant;
import rest.DishOfTheDay.repository.RestaurantRepository;
import rest.DishOfTheDay.util.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

import static rest.DishOfTheDay.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantService {

    private final RestaurantRepository repository;

    @Autowired
    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    public List<Restaurant> getAll() {
        return repository.findAll(new Sort(Sort.Direction.ASC, "name"));
    }

    public Optional<Restaurant> get(Integer id) {
        return repository.findById(id);
        //return checkNotFoundWithId(repository.findById(id).get(), id, Restaurant.class);
    }

    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return repository.save(restaurant);
    }

    public void delete (int id) throws NotFoundException {
        checkNotFoundWithId(repository.existsById(id), id, Restaurant.class);
        repository.deleteById(id);
    }

    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        repository.save(restaurant);
    }
}
