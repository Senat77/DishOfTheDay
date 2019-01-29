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

    public Restaurant get(Integer id) {
        Optional<Restaurant> restaurant = repository.findById(id);
        if(restaurant.isPresent())
            return restaurant.get();
        else
            throw new NotFoundException(Restaurant.class);
    }

    public Restaurant save(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return repository.save(restaurant);
    }

    public void delete (int id) {
        if (repository.existsById(id))
            repository.deleteById(id);
        else
            throw new NotFoundException(Restaurant.class);
    }
}
